import db from '../../../lib/db'; // Changed import

export default function handler(req, res) { // Removed async
  try {
    const stmt = db.prepare("SELECT DISTINCT tech_base FROM units WHERE tech_base IS NOT NULL AND TRIM(tech_base) <> '' ORDER BY tech_base ASC");
    const rows = stmt.all();
    const values = rows.map(row => row.tech_base);
    res.status(200).json(values);
  } catch (error) {
    console.error('Error fetching distinct unit tech bases:', error);
    res.status(500).json({
      message: 'Error fetching distinct unit tech bases',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  }
  // No client.release() needed
}
