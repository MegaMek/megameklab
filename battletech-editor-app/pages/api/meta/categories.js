import { Pool } from 'pg';

const pool = new Pool({
  user: 'battletech_user',
  host: 'db', // Assuming docker-compose service name
  database: 'battletech_editor',
  password: 'password',
  port: 5432,
});

export default async function handler(req, res) {
  let client;

  try {
    client = await pool.connect();
    // Fetches distinct 'type' values from the 'units' table.
    // This 'type' column is assumed to store the unit categories like "BattleMech", "Vehicle", etc.
    const result = await client.query("SELECT DISTINCT type FROM units WHERE type IS NOT NULL AND type <> '' ORDER BY type ASC");

    // Transform the result into an array of strings
    const categories = result.rows.map(row => row.type);

    res.status(200).json(categories);
  } catch (error) {
    console.error('Error fetching unit categories from database:', error);
    res.status(500).json({
      message: 'Error fetching unit categories',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  } finally {
    if (client) {
      client.release();
    }
  }
}
