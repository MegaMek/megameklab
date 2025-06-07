import { Pool } from 'pg';

const pool = new Pool({
  user: 'battletech_user',
  host: 'db', // Assuming docker-compose service name
  database: 'battletech_editor',
  password: 'password',
  port: 5432,
});

export default async function handler(req, res) {
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

  let client;
  // For logging query on error
  let queryStringForLog = '';
  let queryParamsForLog = [];

  try {
    client = await pool.connect();

    if (id) {
      const result = await client.query(
        'SELECT id, name, type, tech_base, era, source, data FROM equipment WHERE id = $1',
        [id]
      );
      if (result.rows.length === 0) {
        return res.status(404).json({ message: 'Equipment not found' });
      }
      return res.status(200).json(result.rows[0]);
    } else {
      let mainQueryFrom = 'FROM equipment';
      let whereConditions = [];
      let queryParams = [];
      let paramIndex = 1;

      if (q) {
        whereConditions.push(`name ILIKE $${paramIndex}`);
        queryParams.push(`%${q}%`);
        paramIndex++;
      }

      if (type_array) {
        const types = Array.isArray(type_array) ? type_array : type_array.split(',');
        if (types.length > 0) {
          const placeholders = types.map(() => `$${paramIndex++}`).join(', ');
          whereConditions.push(`type IN (${placeholders})`);
          queryParams.push(...types);
        }
      }

      if (tech_base_array) {
        const techBases = Array.isArray(tech_base_array) ? tech_base_array : tech_base_array.split(',');
        if (techBases.length > 0) {
          const placeholders = techBases.map(() => `$${paramIndex++}`).join(', ');
          whereConditions.push(`tech_base IN (${placeholders})`);
          queryParams.push(...techBases);
        }
      }

      if (era_array) {
        const eras = Array.isArray(era_array) ? era_array : era_array.split(',');
        if (eras.length > 0) {
          const placeholders = eras.map(() => `$${paramIndex++}`).join(', ');
          whereConditions.push(`era IN (${placeholders})`);
          // Assuming era values in the DB are stored as strings that can match these.
          // If they are numeric years, the frontend might need to send year numbers.
          queryParams.push(...eras);
        }
      }

      let whereClause = '';
      if (whereConditions.length > 0) {
        whereClause = ' WHERE ' + whereConditions.join(' AND ');
      }

      const countQueryString = `SELECT COUNT(*) AS total ${mainQueryFrom}${whereClause}`;
      const totalResult = await client.query(countQueryString, queryParams);
      const totalItems = parseInt(totalResult.rows[0].total, 10);

      let mainQueryString = `SELECT id, name, type, tech_base, era, source, data ${mainQueryFrom}${whereClause}`;

      const validSortColumns = ['id', 'name', 'type', 'tech_base', 'era'];
      const effectiveSortBy = validSortColumns.includes(sortBy) ? sortBy : 'name';
      const effectiveSortOrder = sortOrder.toUpperCase() === 'DESC' ? 'DESC' : 'ASC';

      mainQueryString += ` ORDER BY "${effectiveSortBy}" ${effectiveSortOrder}`;

      const limitValue = parseInt(limit, 10);
      const offsetValue = (parseInt(page, 10) - 1) * limitValue;
      mainQueryString += ` LIMIT $${paramIndex++} OFFSET $${paramIndex++}`;

      const finalQueryParams = [...queryParams, limitValue, offsetValue];

      queryStringForLog = mainQueryString;
      queryParamsForLog = finalQueryParams;

      const result = await client.query(mainQueryString, finalQueryParams);

      return res.status(200).json({
        items: result.rows,
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
        console.error('Failing Query:', queryStringForLog);
        console.error('Query Params:', queryParamsForLog);
    }
    return res.status(500).json({
      message: 'Error fetching equipment',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  } finally {
    if (client) {
      client.release();
    }
  }
}
