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
    // The 'era' column in 'equipment' table stores introduction_year or era names.
    // It's assumed to be of a text-compatible type.
    const result = await client.query("SELECT DISTINCT era FROM equipment WHERE era IS NOT NULL AND TRIM(era::text) <> '' ORDER BY era ASC");
    const values = result.rows.map(row => row.era);
    res.status(200).json(values);
  } catch (error) {
    console.error('Error fetching distinct equipment eras:', error);
    res.status(500).json({
      message: 'Error fetching distinct equipment eras',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  } finally {
    if (client) {
      client.release();
    }
  }
}
