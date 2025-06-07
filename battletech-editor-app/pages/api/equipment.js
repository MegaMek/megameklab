import sqlite3 from 'sqlite3';
import { open } from 'sqlite';

const SQLITE_DB_FILE = "battletech_dev.sqlite";

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

  let db;
  let queryStringForLog = '';
  let queryParamsForLog = [];

  try {
    db = await open({
      filename: SQLITE_DB_FILE,
      driver: sqlite3.Database
    });

    if (id) {
      const result = await db.get(
        'SELECT id, name, type, tech_base, era, source, data FROM equipment WHERE id = ?',
        [id]
      );
      if (!result) {
        return res.status(404).json({ message: 'Equipment not found' });
      }
      if (result.data && typeof result.data === 'string') {
        result.data = JSON.parse(result.data);
      }
      return res.status(200).json(result);
    } else {
      let mainQueryFrom = 'FROM equipment';
      let whereConditions = [];
      let queryParams = [];

      if (q) {
        whereConditions.push(`LOWER(name) LIKE LOWER(?)`);
        queryParams.push(`%${q}%`);
      }

      if (type_array) {
        const types = Array.isArray(type_array) ? type_array : type_array.split(',');
        if (types.length > 0) {
          const placeholders = types.map(() => `?`).join(', ');
          whereConditions.push(`type IN (${placeholders})`);
          queryParams.push(...types);
        }
      }

      if (tech_base_array) {
        const techBases = Array.isArray(tech_base_array) ? tech_base_array : tech_base_array.split(',');
        if (techBases.length > 0) {
          const placeholders = techBases.map(() => `?`).join(', ');
          whereConditions.push(`tech_base IN (${placeholders})`);
          queryParams.push(...techBases);
        }
      }

      if (era_array) {
        const eras = Array.isArray(era_array) ? era_array : era_array.split(',');
        if (eras.length > 0) {
          const placeholders = eras.map(() => `?`).join(', ');
          whereConditions.push(`era IN (${placeholders})`);
          queryParams.push(...eras);
        }
      }

      let whereClause = '';
      if (whereConditions.length > 0) {
        whereClause = ' WHERE ' + whereConditions.join(' AND ');
      }

      const countQueryString = `SELECT COUNT(*) AS total ${mainQueryFrom}${whereClause}`;
      queryStringForLog = countQueryString; // Log this version
      queryParamsForLog = [...queryParams]; // Log params for count
      const totalResult = await db.get(countQueryString, queryParams);
      const totalItems = parseInt(totalResult.total, 10);

      let mainQueryString = `SELECT id, name, type, tech_base, era, source, data ${mainQueryFrom}${whereClause}`;
      const validSortColumns = ['id', 'name', 'type', 'tech_base', 'era'];
      const effectiveSortBy = validSortColumns.includes(sortBy) ? sortBy : 'name';
      const effectiveSortOrder = sortOrder.toUpperCase() === 'DESC' ? 'DESC' : 'ASC';
      mainQueryString += ` ORDER BY "${effectiveSortBy}" ${effectiveSortOrder}`;

      const limitValue = parseInt(limit, 10);
      const pageValue = parseInt(page, 10);
      const offsetValue = (pageValue - 1) * limitValue;
      mainQueryString += ` LIMIT ? OFFSET ?`;

      const finalQueryParams = [...queryParams, limitValue, offsetValue];
      queryStringForLog = mainQueryString; // Log this version
      queryParamsForLog = finalQueryParams;

      let items = await db.all(mainQueryString, finalQueryParams);

      items = items.map(row => {
        if (row.data && typeof row.data === 'string') {
          row.data = JSON.parse(row.data);
        }
        return row;
      });

      return res.status(200).json({
        items,
        totalItems,
        totalPages: Math.ceil(totalItems / limitValue),
        currentPage: pageValue,
        sortBy: effectiveSortBy,
        sortOrder: effectiveSortOrder,
      });
    }
  } catch (error) {
    console.error('Error fetching equipment from SQLite database:', error);
    if (process.env.NODE_ENV === 'development') {
        console.error('Failing Query String (approximate for SQLite):', queryStringForLog);
        console.error('Query Params (approximate for SQLite):', queryParamsForLog);
    }
    return res.status(500).json({
      message: 'Error fetching equipment',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  } finally {
    if (db) {
      await db.close();
    }
  }
}
