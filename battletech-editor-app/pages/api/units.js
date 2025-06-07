import { Pool } from 'pg';

const pool = new Pool({
  user: 'battletech_user',
  host: 'db',
  database: 'battletech_editor',
  password: 'password',
  port: 5432,
});

export default async function handler(req, res) {
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

  let client;

  // To be populated for error logging if needed
  let mainQueryStringForLog = '';
  let finalQueryParamsForLog = [];

  try {
    client = await pool.connect();

    if (id) {
      // Ensure 'type' column exists or is aliased if actual column name differs
      const result = await client.query(
        'SELECT id, chassis, model, mass, tech_base, rules_level, era, source, data, type FROM units WHERE id = $1',
        [id]
      );
      if (result.rows.length === 0) {
        return res.status(404).json({ message: 'Unit not found' });
      }
      return res.status(200).json(result.rows[0]);
    } else {
      let mainQueryFrom = 'FROM units';
      let whereConditions = [];
      let queryParams = [];
      let paramIndex = 1;

      if (q) {
        whereConditions.push(`(chassis ILIKE $${paramIndex} OR model ILIKE $${paramIndex})`);
        queryParams.push(`%${q}%`);
        paramIndex++;
      }
      if (unit_type) {
        // Assuming 'type' column in DB matches 'unit_type' concept
        whereConditions.push(`type = $${paramIndex}`);
        queryParams.push(unit_type);
        paramIndex++;
      }
      if (tech_base_array) {
        const techBases = Array.isArray(tech_base_array) ? tech_base_array : tech_base_array.split(','); // Also handle comma-separated string
        if (techBases.length > 0) {
          const placeholders = techBases.map(() => `$${paramIndex++}`).join(', ');
          whereConditions.push(`tech_base IN (${placeholders})`);
          queryParams.push(...techBases);
        }
      }
      if (mass_gte) {
        whereConditions.push(`mass >= $${paramIndex}`);
        queryParams.push(parseInt(mass_gte, 10));
        paramIndex++;
      }
      if (mass_lte) {
        whereConditions.push(`mass <= $${paramIndex}`);
        queryParams.push(parseInt(mass_lte, 10));
        paramIndex++;
      }
      if (has_quirk) {
        whereConditions.push(`EXISTS (
          SELECT 1
          FROM jsonb_array_elements(data->'Quirks') AS quirk_element
          WHERE quirk_element->>'Name' ILIKE $${paramIndex}
        )`);
        // Using ILIKE for case-insensitive quirk search
        queryParams.push(has_quirk);
        paramIndex++;
      }
      if (era) {
        whereConditions.push(`era = $${paramIndex}`);
        queryParams.push(era);
        paramIndex++;
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
          whereConditions.push(`mass >= $${paramIndex}`);
          queryParams.push(selectedWeightClass.gte);
          paramIndex++;
          whereConditions.push(`mass <= $${paramIndex}`);
          queryParams.push(selectedWeightClass.lte);
          paramIndex++;
        }
      }

      let whereClause = '';
      if (whereConditions.length > 0) {
        whereClause = ' WHERE ' + whereConditions.join(' AND ');
      }

      const countQueryString = `SELECT COUNT(*) AS total ${mainQueryFrom}${whereClause}`;
      const totalResult = await client.query(countQueryString, queryParams);
      const totalItems = parseInt(totalResult.rows[0].total, 10);

      let mainQueryString = `SELECT id, chassis, model, mass, tech_base, rules_level, era, source, data, type ${mainQueryFrom}${whereClause}`;

      const validSortColumns = ['id', 'chassis', 'model', 'mass', 'tech_base', 'rules_level', 'era', 'type']; // era was already here, no change needed for validSortColumns based on task.
      const effectiveSortBy = validSortColumns.includes(sortBy) ? sortBy : 'id'; // Default to 'id' if sortBy is invalid or missing
      const effectiveSortOrder = sortOrder.toUpperCase() === 'DESC' ? 'DESC' : 'ASC';

      mainQueryString += ` ORDER BY "${effectiveSortBy}" ${effectiveSortOrder}`;


      const limitValue = parseInt(limit, 10);
      const offsetValue = (parseInt(page, 10) - 1) * limitValue;
      mainQueryString += ` LIMIT $${paramIndex++} OFFSET $${paramIndex++}`;

      const finalQueryParams = [...queryParams, limitValue, offsetValue];

      // For potential error logging
      mainQueryStringForLog = mainQueryString;
      finalQueryParamsForLog = finalQueryParams;

      const result = await client.query(mainQueryString, finalQueryParams);

      return res.status(200).json({
        items: result.rows,
        totalItems,
        totalPages: Math.ceil(totalItems / limitValue),
        currentPage: parseInt(page, 10),
        sortBy: effectiveSortBy,
        sortOrder: effectiveSortOrder
      });
    }
  } catch (error) {
    console.error('Error fetching units from database:', error);
    // Optionally log the query and params that caused the error
    // This should be conditional and parameters might need sanitization if logged to a persistent store
    if (process.env.NODE_ENV === 'development') {
       console.error('Failing Query:', mainQueryStringForLog);
       console.error('Query Params:', finalQueryParamsForLog);
    }
    return res.status(500).json({
      message: 'Error fetching units',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  } finally {
    if (client) {
      client.release();
    }
  }
}
