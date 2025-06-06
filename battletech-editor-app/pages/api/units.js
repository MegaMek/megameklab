import { Pool } from 'pg';

// Configure the connection pool
// IMPORTANT: In a real application, use environment variables for sensitive data!
const pool = new Pool({
  user: 'battletech_user',
  host: 'localhost',
  database: 'battletech_editor',
  password: 'password',
  port: 5432,
});

export default async function handler(req, res) {
  try {
    const client = await pool.connect();
    const result = await client.query('SELECT id, chassis, model, mass, tech_base, rules_level, era, source, data FROM units LIMIT 10'); // Limiting to 10 for now
    client.release();
    res.status(200).json(result.rows);
  } catch (error) {
    console.error('Error fetching units from database:', error);
    // Send more detailed error in response for debugging, but be cautious in production
    res.status(500).json({
      message: 'Error fetching units',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined
    });
  }
}
