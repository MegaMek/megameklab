// battletech-editor-app/pages/api/meta/equipment_tech_bases.js
import type { NextApiRequest, NextApiResponse } from 'next';
import sqlite3 from 'sqlite3';
import { open, Database } from 'sqlite';

const SQLITE_DB_FILE: string = "../../../battletech_dev.sqlite"; // Path relative to pages/api/meta

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
  let db: Database<sqlite3.Database, sqlite3.Statement> | undefined;
  try {
    db = await open({
      filename: SQLITE_DB_FILE,
      driver: sqlite3.Database,
      mode: sqlite3.OPEN_READONLY // Open in readonly mode
    });

    const result: { tech_base: string }[] = await db.all("SELECT DISTINCT tech_base FROM equipment WHERE tech_base IS NOT NULL AND TRIM(tech_base) <> '' ORDER BY tech_base ASC");
    const values: string[] = result.map(row => row.tech_base);

    res.status(200).json(values);
  } catch (error: any) {
    console.error('Error fetching distinct equipment tech bases from SQLite:', error);
    res.status(500).json({
      message: 'Error fetching distinct equipment tech bases',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  } finally {
    if (db) {
      await db.close();
    }
  }
}
