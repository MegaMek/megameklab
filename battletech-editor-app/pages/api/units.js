import db from '../../lib/db'; // Changed import

export default function handler(req, res) { // Removed async
  const {
    id,
    page = 1,
    limit = 10,
    q,
    tech_base_array,
    mass_gte,
    mass_lte,
    has_quirk,
    unit_type,
    era, // Added era
    weight_class, // Added weight_class
    sortBy,
    sortOrder = 'ASC'
  } = req.query;

  // To be populated for error logging if needed
  let mainQueryStringForLog = '';
  let finalQueryParamsForLog = [];

  try {
    if (id) {
      mainQueryStringForLog = 'SELECT id, chassis, model, mass_tons as mass, tech_base, role as rules_level, era, source_book as source, data, unit_type as type FROM units WHERE id = ?';
      // Aliased columns to match expected output structure from original PG query if different
      // e.g. mass_tons as mass, role as rules_level, source_book as source, unit_type as type
      finalQueryParamsForLog = [id];
      const stmt = db.prepare(mainQueryStringForLog);
      const row = stmt.get(id);

      if (!row) {
        return res.status(404).json({ message: 'Unit not found' });
      }
      if (row.data) {
        row.data = JSON.parse(row.data);
      }
      return res.status(200).json(row);
    } else {
      let mainQueryFrom = 'FROM units'; // Table name is units
      let whereConditions = [];
      let queryParams = [];

      if (q) {
        // SQLite LIKE is case-insensitive by default for ASCII. For broader Unicode, ensure ICU or use lower()
        whereConditions.push(`(chassis LIKE ? OR model LIKE ?)`);
        queryParams.push(`%${q}%`, `%${q}%`);
      }
      if (unit_type) {
        whereConditions.push(`unit_type = ?`); // Column name is unit_type
        queryParams.push(unit_type);
      }
      if (tech_base_array) {
        const techBases = Array.isArray(tech_base_array) ? tech_base_array : tech_base_array.split(',');
        if (techBases.length > 0) {
          whereConditions.push(`tech_base IN (${techBases.map(() => '?').join(', ')})`);
          queryParams.push(...techBases);
        }
      }
      if (mass_gte) {
        whereConditions.push(`mass_tons >= ?`); // Column name is mass_tons
        queryParams.push(parseInt(mass_gte, 10));
      }
      if (mass_lte) {
        whereConditions.push(`mass_tons <= ?`); // Column name is mass_tons
        queryParams.push(parseInt(mass_lte, 10));
      }
      if (has_quirk) {
        // Robust check for quirk name within JSON array
        whereConditions.push(`EXISTS (SELECT 1 FROM json_each(data, '$.Quirks') WHERE json_extract(value, '$.Name') LIKE ?)`);
        queryParams.push(`%${has_quirk}%`); // Using LIKE for quirk name search
      }
      if (era) {
        whereConditions.push(`era = ?`);
        queryParams.push(era);
      }
      if (weight_class) {
        const weightClassMap = {
          'Light': { gte: 20, lte: 35 },
          'Medium': { gte: 40, lte: 55 },
          'Heavy': { gte: 60, lte: 75 },
          'Assault': { gte: 80, lte: 100 }
        };
        const selectedWeightClass = weightClassMap[weight_class];
        if (selectedWeightClass) {
          whereConditions.push(`mass_tons >= ?`);
          queryParams.push(selectedWeightClass.gte);
          whereConditions.push(`mass_tons <= ?`);
          queryParams.push(selectedWeightClass.lte);
        }
      }

      let whereClause = '';
      if (whereConditions.length > 0) {
        whereClause = ' WHERE ' + whereConditions.join(' AND ');
      }

      const countQueryString = `SELECT COUNT(*) AS total ${mainQueryFrom}${whereClause}`;
      mainQueryStringForLog = countQueryString; // For logging
      finalQueryParamsForLog = [...queryParams]; // For logging

      const countStmt = db.prepare(countQueryString);
      const totalResult = countStmt.get(...queryParams);
      const totalItems = parseInt(totalResult.total, 10);

      // Ensure selected columns match what the frontend expects, aliasing if necessary
      let mainQueryString = `SELECT id, chassis, model, mass_tons as mass, tech_base, role as rules_level, era, source_book as source, data, unit_type as type ${mainQueryFrom}${whereClause}`;

      const validSortColumns = ['id', 'chassis', 'model', 'mass_tons', 'tech_base', 'role', 'era', 'unit_type'];
      // Map frontend sortBy to actual DB column name if different (e.g., 'mass' to 'mass_tons')
      const sortColumnMapping = { 'mass': 'mass_tons', 'rules_level': 'role', 'source': 'source_book', 'type': 'unit_type' };
      let effectiveSortByDb = sortBy;
      if (sortColumnMapping[sortBy]) {
        effectiveSortByDb = sortColumnMapping[sortBy];
      }
      if (!validSortColumns.includes(effectiveSortByDb)) {
        effectiveSortByDb = 'id'; // Default to 'id'
      }

      const effectiveSortOrder = sortOrder.toUpperCase() === 'DESC' ? 'DESC' : 'ASC';
      mainQueryString += ` ORDER BY ${effectiveSortByDb} ${effectiveSortOrder}`;

      const limitValue = parseInt(limit, 10);
      const offsetValue = (parseInt(page, 10) - 1) * limitValue;
      mainQueryString += ` LIMIT ? OFFSET ?`;

      const finalQueryParamsWithPagination = [...queryParams, limitValue, offsetValue];
      mainQueryStringForLog = mainQueryString; // For logging
      finalQueryParamsForLog = [...finalQueryParamsWithPagination]; // For logging

      const mainStmt = db.prepare(mainQueryString);
      const rows = mainStmt.all(...finalQueryParamsWithPagination);

      const items = rows.map(row => {
        if (row.data) {
          try {
            row.data = JSON.parse(row.data);
          } catch (parseError) {
            console.error(`Failed to parse data for unit ID ${row.id}:`, parseError);
          }
        }
        return row;
      });

      return res.status(200).json({
        items,
        totalItems,
        totalPages: Math.ceil(totalItems / limitValue),
        currentPage: parseInt(page, 10),
        sortBy: sortBy, // Return original sortBy value
        sortOrder: effectiveSortOrder
      });
    }
  } catch (error) {
    console.error('Error fetching units from database:', error);
    if (process.env.NODE_ENV === 'development') {
       console.error('Failing Query:', mainQueryStringForLog);
       console.error('Query Params:', finalQueryParamsForLog);
    }
    return res.status(500).json({
      message: 'Error fetching units',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  }
  // No client.release() needed
}
