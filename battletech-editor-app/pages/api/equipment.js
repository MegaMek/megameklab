import db from '../../lib/db'; // Changed import

export default function handler(req, res) { // Removed async
  const {
    id,
    page = 1,
    limit = 10,
    type_array, // For filtering by equipment type
    q, // For text search on name
    tech_base_array, // For filtering by tech_base
    era_array, // For filtering by era
    sortBy,
    sortOrder = 'ASC' // Default to ascending order
  } = req.query;

  // For logging query on error
  let queryStringForLog = '';
  let queryParamsForLog = [];

  try {
    if (id) {
      queryStringForLog = 'SELECT id, name, type, tech_base, era, source, data FROM equipment WHERE id = ?';
      queryParamsForLog = [id];
      const stmt = db.prepare(queryStringForLog);
      const row = stmt.get(id);

      if (!row) {
        return res.status(404).json({ message: 'Equipment not found' });
      }
      if (row.data) {
        row.data = JSON.parse(row.data);
      }
      return res.status(200).json(row);
    } else {
      let mainQueryFrom = 'FROM equipment';
      let whereConditions = [];
      let queryParams = [];

      if (q) {
        whereConditions.push(`name LIKE ?`); // Changed ILIKE to LIKE
        queryParams.push(`%${q}%`);
      }

      if (type_array) {
        const types = Array.isArray(type_array) ? type_array : type_array.split(',');
        if (types.length > 0) {
          whereConditions.push(`type IN (${types.map(() => '?').join(', ')})`);
          queryParams.push(...types);
        }
      }

      if (tech_base_array) {
        const techBases = Array.isArray(tech_base_array) ? tech_base_array : tech_base_array.split(',');
        if (techBases.length > 0) {
          whereConditions.push(`tech_base IN (${techBases.map(() => '?').join(', ')})`);
          queryParams.push(...techBases);
        }
      }

      if (era_array) {
        const eras = Array.isArray(era_array) ? era_array : era_array.split(',');
        if (eras.length > 0) {
          whereConditions.push(`era IN (${eras.map(() => '?').join(', ')})`);
          queryParams.push(...eras);
        }
      }

      let whereClause = '';
      if (whereConditions.length > 0) {
        whereClause = ' WHERE ' + whereConditions.join(' AND ');
      }

      const countQueryString = `SELECT COUNT(*) AS total ${mainQueryFrom}${whereClause}`;
      queryStringForLog = countQueryString; // For logging
      queryParamsForLog = queryParams; // For logging

      const countStmt = db.prepare(countQueryString);
      const totalResult = countStmt.get(...queryParams); // Spread queryParams for .get
      const totalItems = parseInt(totalResult.total, 10);

      let mainQueryString = `SELECT id, name, type, tech_base, era, source, data ${mainQueryFrom}${whereClause}`;

      const validSortColumns = ['id', 'name', 'type', 'tech_base', 'era'];
      const effectiveSortBy = validSortColumns.includes(sortBy) ? sortBy : 'name';
      const effectiveSortOrder = sortOrder.toUpperCase() === 'DESC' ? 'DESC' : 'ASC';

      // For SQLite, identifiers usually don't need quotes unless they are keywords or contain special chars.
      // Since effectiveSortBy is validated, direct interpolation is safer here.
      mainQueryString += ` ORDER BY ${effectiveSortBy} ${effectiveSortOrder}`;

      const limitValue = parseInt(limit, 10);
      const offsetValue = (parseInt(page, 10) - 1) * limitValue;
      mainQueryString += ` LIMIT ? OFFSET ?`;

      const finalQueryParams = [...queryParams, limitValue, offsetValue];
      queryStringForLog = mainQueryString; // For logging
      queryParamsForLog = finalQueryParams; // For logging

      const mainStmt = db.prepare(mainQueryString);
      const rows = mainStmt.all(...finalQueryParams); // Spread finalQueryParams for .all

      const items = rows.map(row => {
        if (row.data) {
          try {
            row.data = JSON.parse(row.data);
          } catch (parseError) {
            console.error(`Failed to parse data for equipment ID ${row.id}:`, parseError);
            // Keep data as string or set to null/error object if parsing fails
          }
        }
        return row;
      });

      return res.status(200).json({
        items,
        totalItems,
        totalPages: Math.ceil(totalItems / limitValue),
        currentPage: parseInt(page, 10),
        sortBy: effectiveSortBy,
        sortOrder: effectiveSortOrder,
      });
    }
  } catch (error) {
    console.error('Error fetching equipment from database:', error);
    if (process.env.NODE_ENV === 'development') {
        console.error('Failing Query:', queryStringForLog); // Log the query string
        console.error('Query Params:', queryParamsForLog); // Log the parameters
    }
    return res.status(500).json({
      message: 'Error fetching equipment',
      error: error.message, // Provide error message
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  }
  // No client.release() needed for better-sqlite3
}
