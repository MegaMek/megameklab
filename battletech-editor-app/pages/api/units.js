import sqlite3 from 'sqlite3';
import { open } from 'sqlite';

const SQLITE_DB_FILE = "battletech_dev.sqlite";

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

  let db;
  let mainQueryStringForLog = ''; // For logging on error
  let finalQueryParamsForLog = []; // For logging on error

  try {
    db = await open({
      filename: SQLITE_DB_FILE,
      driver: sqlite3.Database
    });

    if (id) {
      const result = await db.get(
        'SELECT id, chassis, model, mass, tech_base, rules_level, era, source, data, type FROM units WHERE id = ?',
        [id]
      );
      if (!result) {
        return res.status(404).json({ message: 'Unit not found' });
      }
      // Parse the data field if it's a string
      if (result.data && typeof result.data === 'string') {
        result.data = JSON.parse(result.data);
      }
      return res.status(200).json(result);
    } else {
      let mainQueryFrom = 'FROM units';
      let whereConditions = [];
      let queryParams = [];

      if (q) {
        // SQLite LIKE is case-insensitive by default for ASCII. For broader Unicode, use LOWER()
        whereConditions.push(`(LOWER(chassis) LIKE LOWER(?) OR LOWER(model) LIKE LOWER(?))`);
        queryParams.push(`%${q}%`, `%${q}%`);
      }
      if (unit_type) {
        whereConditions.push(`type = ?`);
        queryParams.push(unit_type);
      }
      if (tech_base_array) {
        const techBases = Array.isArray(tech_base_array) ? tech_base_array : tech_base_array.split(',');
        if (techBases.length > 0) {
          const placeholders = techBases.map(() => `?`).join(', ');
          whereConditions.push(`tech_base IN (${placeholders})`);
          queryParams.push(...techBases);
        }
      }
      if (mass_gte) {
        whereConditions.push(`mass >= ?`);
        queryParams.push(parseInt(mass_gte, 10));
      }
      if (mass_lte) {
        whereConditions.push(`mass <= ?`);
        queryParams.push(parseInt(mass_lte, 10));
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
          whereConditions.push(`mass >= ?`);
          queryParams.push(selectedWeightClass.gte);
          whereConditions.push(`mass <= ?`);
          queryParams.push(selectedWeightClass.lte);
        }
      }

      // Quirk filter will be applied after fetching initial data if present
      const applyQuirkFilterLater = !!has_quirk;
      let queryParamsForCount = [...queryParams]; // Params for count query before quirk filter
      let whereClauseForCount = whereConditions.length > 0 ? ' WHERE ' + whereConditions.join(' AND ') : '';

      // If not applying quirk filter later, use these params for main query too
      let queryParamsForMain = [...queryParams];
      let whereClauseForMain = whereClauseForCount;

      // Count query (potentially without quirk filter if applied later)
      const countQueryString = `SELECT COUNT(*) AS total ${mainQueryFrom}${whereClauseForCount}`;
      mainQueryStringForLog = countQueryString; // Log this version
      finalQueryParamsForLog = queryParamsForCount;
      const totalResult = await db.get(countQueryString, queryParamsForCount);
      let totalItems = parseInt(totalResult.total, 10);

      // Main query construction
      let mainQueryString = `SELECT id, chassis, model, mass, tech_base, rules_level, era, source, data, type ${mainQueryFrom}${whereClauseForMain}`;
      const validSortColumns = ['id', 'chassis', 'model', 'mass', 'tech_base', 'rules_level', 'era', 'type'];
      const effectiveSortBy = validSortColumns.includes(sortBy) ? sortBy : 'id';
      const effectiveSortOrder = sortOrder.toUpperCase() === 'DESC' ? 'DESC' : 'ASC';
      mainQueryString += ` ORDER BY "${effectiveSortBy}" ${effectiveSortOrder}`;

      // If NOT applying quirk filter later, apply pagination via SQL
      // If applying quirk filter later, fetch ALL results matching other criteria first
      let items;
      const limitValue = parseInt(limit, 10);
      const pageValue = parseInt(page, 10);
      let offsetValue;

      if (!applyQuirkFilterLater) {
        offsetValue = (pageValue - 1) * limitValue;
        mainQueryString += ` LIMIT ? OFFSET ?`;
        queryParamsForMain.push(limitValue, offsetValue);
        mainQueryStringForLog = mainQueryString; // Log this version
        finalQueryParamsForLog = queryParamsForMain;
        items = await db.all(mainQueryString, queryParamsForMain);
      } else {
        // Fetch all matching other criteria, then filter by quirk in JS
        mainQueryStringForLog = mainQueryString; // Log this version (without LIMIT/OFFSET yet)
        finalQueryParamsForLog = queryParamsForMain;
        items = await db.all(mainQueryString, queryParamsForMain);
      }

      // Parse data field and apply quirk filter if needed
      items = items.map(row => {
        if (row.data && typeof row.data === 'string') {
          row.data = JSON.parse(row.data);
        }
        return row;
      });

      if (applyQuirkFilterLater && has_quirk) {
        const quirkSearchTerm = has_quirk.toLowerCase();
        items = items.filter(unit => {
          if (unit.data && Array.isArray(unit.data.Quirks)) {
            return unit.data.Quirks.some(quirk =>
              typeof quirk.Name === 'string' && quirk.Name.toLowerCase().includes(quirkSearchTerm)
            );
          }
          return false;
        });
        totalItems = items.length; // Recalculate totalItems after JS filtering
        // Apply pagination now after JS filtering
        offsetValue = (pageValue - 1) * limitValue;
        items = items.slice(offsetValue, offsetValue + limitValue);
      }

      const totalPages = Math.ceil(totalItems / limitValue);

      return res.status(200).json({
        items,
        totalItems,
        totalPages,
        currentPage: pageValue,
        sortBy: effectiveSortBy,
        sortOrder: effectiveSortOrder
      });
    }
  } catch (error) {
    console.error('Error fetching units from SQLite database:', error);
    if (process.env.NODE_ENV === 'development') {
       console.error('Failing Query String (approximate for SQLite):', mainQueryStringForLog);
       console.error('Query Params (approximate for SQLite):', finalQueryParamsForLog);
    }
    return res.status(500).json({
      message: 'Error fetching units',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  } finally {
    if (db) {
      await db.close();
    }
  }
}
