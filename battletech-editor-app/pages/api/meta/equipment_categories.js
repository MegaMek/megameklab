import { Pool } from 'pg';

const pool = new Pool({
  user: 'battletech_user',
  host: 'db',
  database: 'battletech_editor',
  password: 'password',
  port: 5432,
});

export default async function handler(req, res) {
  let client;
  try {
    client = await pool.connect();
    const result = await client.query("SELECT DISTINCT type FROM equipment WHERE type IS NOT NULL AND TRIM(type) <> '' ORDER BY type ASC");
    const values = result.rows.map(row => row.type);
    res.status(200).json(values);
  } catch (error) {
    console.error('Error fetching distinct equipment categories (types):', error);
    res.status(500).json({
      message: 'Error fetching distinct equipment categories (types)',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  } finally {
    if (client) {
      client.release();
    }
  }
}
