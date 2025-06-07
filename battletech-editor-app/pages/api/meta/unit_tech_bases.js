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
    const result = await client.query("SELECT DISTINCT tech_base FROM units WHERE tech_base IS NOT NULL AND TRIM(tech_base) <> '' ORDER BY tech_base ASC");
    const values = result.rows.map(row => row.tech_base);
    res.status(200).json(values);
  } catch (error) {
    console.error('Error fetching distinct unit tech bases:', error);
    res.status(500).json({
      message: 'Error fetching distinct unit tech bases',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  } finally {
    if (client) {
      client.release();
    }
  }
}
