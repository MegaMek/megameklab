import type { NextApiRequest, NextApiResponse } from 'next';
import sqlite3 from 'sqlite3';
import { open, Database } from 'sqlite';

const SQLITE_DB_FILE: string = "battletech_dev.sqlite";

interface Unit {
  id: any;
  chassis: any;
  model: any;
  mass: any;
  tech_base: any;
  rules_level: any;
  era: any;
  source: any;
  data: any;
  type: any;
}

interface Quirk {
  Name: string;
  [key: string]: any; // for other properties if any
}

interface UnitData {
  Quirks?: Quirk[];
  [key: string]: any; // for other properties in data
}


export default async function handler(req: NextApiRequest, res: NextApiResponse) {
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
  } = req.query as {
    id?: string | string[];
    page?: string | string[] | number;
    limit?: string | string[] | number;
    q?: string | string[];
    tech_base_array?: string | string[];
    mass_gte?: string | string[];
    mass_lte?: string | string[];
    has_quirk?: string | string[];
    unit_type?: string | string[];
    era?: string | string[];
    weight_class?: string | string[];
    sortBy?: string | string[];
    sortOrder?: string | string[];
  };

  let db: Database<sqlite3.Database, sqlite3.Statement> | undefined;
  let mainQueryStringForLog: string = ''; // For logging on error
  let finalQueryParamsForLog: any[] = []; // For logging on error

  try {
    db = await open({
      filename: SQLITE_DB_FILE,
      driver: sqlite3.Database
    });

    if (id) {
      const result: Unit | undefined = await db.get<Unit>(
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
      let mainQueryFrom: string = 'FROM units';
      let whereConditions: string[] = [];
      let queryParams: any[] = [];

      if (q) {
        // SQLite LIKE is case-insensitive by default for ASCII. For broader Unicode, use LOWER()
        whereConditions.push(`(LOWER(chassis) LIKE LOWER(?) OR LOWER(model) LIKE LOWER(?))`);
        queryParams.push(`%${typeof q === 'string' ? q : ''}%`, `%${typeof q === 'string' ? q : ''}%`);
      }
      if (unit_type) {
        whereConditions.push(`type = ?`);
        queryParams.push(unit_type);
      }
      if (tech_base_array) {
        const techBases: string[] = Array.isArray(tech_base_array) ? tech_base_array : (typeof tech_base_array === 'string' ? tech_base_array.split(',') : []);
        if (techBases.length > 0) {
          const placeholders: string = techBases.map(() => `?`).join(', ');
          whereConditions.push(`tech_base IN (${placeholders})`);
          queryParams.push(...techBases);
        }
      }
      if (mass_gte) {
        whereConditions.push(`mass >= ?`);
        queryParams.push(parseInt(mass_gte as string, 10));
      }
      if (mass_lte) {
        whereConditions.push(`mass <= ?`);
        queryParams.push(parseInt(mass_lte as string, 10));
      }
      if (era) {
        whereConditions.push(`era = ?`);
        queryParams.push(era);
      }
      if (weight_class) {
        const weightClassMap: { [key: string]: { gte: number; lte: number } } = {
          'Light': { gte: 20, lte: 35 },
          'Medium': { gte: 40, lte: 55 },
          'Heavy': { gte: 60, lte: 75 },
          'Assault': { gte: 80, lte: 100 }
        };
        const selectedWeightClass = weightClassMap[weight_class as string];
        if (selectedWeightClass) {
          whereConditions.push(`mass >= ?`);
          queryParams.push(selectedWeightClass.gte);
          whereConditions.push(`mass <= ?`);
          queryParams.push(selectedWeightClass.lte);
        }
      }

      // Quirk filter will be applied after fetching initial data if present
      const applyQuirkFilterLater: boolean = !!has_quirk;
      let queryParamsForCount: any[] = [...queryParams]; // Params for count query before quirk filter
      let whereClauseForCount: string = whereConditions.length > 0 ? ' WHERE ' + whereConditions.join(' AND ') : '';

      // If not applying quirk filter later, use these params for main query too
      let queryParamsForMain: any[] = [...queryParams];
      let whereClauseForMain: string = whereClauseForCount;

      // Count query (potentially without quirk filter if applied later)
      const countQueryString: string = `SELECT COUNT(*) AS total ${mainQueryFrom}${whereClauseForCount}`;
      mainQueryStringForLog = countQueryString; // Log this version
      finalQueryParamsForLog = queryParamsForCount;
      const totalResult: { total: any } | undefined = await db.get(countQueryString, queryParamsForCount);
      let totalItems: number = parseInt(totalResult?.total, 10) || 0;

      // Main query construction
      let mainQueryString: string = `SELECT id, chassis, model, mass, tech_base, rules_level, era, source, data, type ${mainQueryFrom}${whereClauseForMain}`;
      const validSortColumns: string[] = ['id', 'chassis', 'model', 'mass', 'tech_base', 'rules_level', 'era', 'type'];
      const effectiveSortBy: string = validSortColumns.includes(sortBy as string) ? sortBy as string : 'id';
      const effectiveSortOrder: string = (sortOrder as string)?.toUpperCase() === 'DESC' ? 'DESC' : 'ASC';
      mainQueryString += ` ORDER BY "${effectiveSortBy}" ${effectiveSortOrder}`;

      let items: Unit[];
      const limitValue: number = parseInt(limit as string, 10) || 10;
      const pageValue: number = parseInt(page as string, 10) || 1;
      let offsetValue: number;

      if (!applyQuirkFilterLater) {
        offsetValue = (pageValue - 1) * limitValue;
        mainQueryString += ` LIMIT ? OFFSET ?`;
        queryParamsForMain.push(limitValue, offsetValue);
        mainQueryStringForLog = mainQueryString; // Log this version
        finalQueryParamsForLog = queryParamsForMain;
        items = await db.all<Unit[]>(mainQueryString, queryParamsForMain);
      } else {
        mainQueryStringForLog = mainQueryString;
        finalQueryParamsForLog = queryParamsForMain;
        items = await db.all<Unit[]>(mainQueryString, queryParamsForMain);
      }

      items = items.map(row => {
        if (row.data && typeof row.data === 'string') {
          row.data = JSON.parse(row.data) as UnitData;
        }
        return row;
      });

      if (applyQuirkFilterLater && has_quirk) {
        const quirkSearchTerm: string = (has_quirk as string).toLowerCase();
        items = items.filter(unit => {
          const unitData = unit.data as UnitData;
          if (unitData && Array.isArray(unitData.Quirks)) {
            return unitData.Quirks.some(quirk =>
              typeof quirk.Name === 'string' && quirk.Name.toLowerCase().includes(quirkSearchTerm)
            );
          }
          return false;
        });
        totalItems = items.length;
        offsetValue = (pageValue - 1) * limitValue;
        items = items.slice(offsetValue, offsetValue + limitValue);
      }

      const totalPages: number = Math.ceil(totalItems / limitValue);

      return res.status(200).json({
        items,
        totalItems,
        totalPages,
        currentPage: pageValue,
        sortBy: effectiveSortBy,
        sortOrder: effectiveSortOrder
      });
    }
  } catch (error: any) {
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
