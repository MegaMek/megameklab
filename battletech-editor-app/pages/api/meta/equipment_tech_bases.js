// battletech-editor-app/pages/api/meta/equipment_tech_bases.js
import sqlite3 from 'sqlite3';
import { open } from 'sqlite';

const SQLITE_DB_FILE = "../../../battletech_dev.sqlite"; // Path relative to pages/api/meta

export default async function handler(req, res) {
  let db;
  try {
    db = await open({
      filename: SQLITE_DB_FILE,
      driver: sqlite3.Database,
      mode: sqlite3.OPEN_READONLY // Open in readonly mode
    });

    const result = await db.all("SELECT DISTINCT tech_base FROM equipment WHERE tech_base IS NOT NULL AND TRIM(tech_base) <> '' ORDER BY tech_base ASC");
    const values = result.map(row => row.tech_base);

    res.status(200).json(values);
  } catch (error) {
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
