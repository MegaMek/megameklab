// battletech-editor-app/pages/api/meta/categories.js
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

    // Fetches distinct 'type' values from the 'units' table.
    // This 'type' column is assumed to store the unit categories like "BattleMech", "Vehicle", etc.
    const result = await db.all("SELECT DISTINCT type FROM units WHERE type IS NOT NULL AND TRIM(type) <> '' ORDER BY type ASC");
    // The `sqlite` wrapper's .all() method returns rows directly as an array of objects
    const categories = result.map(row => row.type);

    res.status(200).json(categories);
  } catch (error) {
    console.error('Error fetching unit categories from SQLite:', error);
    res.status(500).json({
      message: 'Error fetching unit categories',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  } finally {
    if (db) {
      await db.close();
    }
  }
}
