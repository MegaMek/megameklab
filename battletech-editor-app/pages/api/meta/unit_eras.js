import db from '../../../lib/db'; // Changed import

export default function handler(req, res) { // Removed async
  try {
    const stmt = db.prepare("SELECT DISTINCT era FROM units WHERE era IS NOT NULL AND TRIM(era) <> '' ORDER BY era ASC");
    const rows = stmt.all();
    const values = rows.map(row => row.era);
    res.status(200).json(values);
  } catch (error) {
    console.error('Error fetching distinct unit eras:', error);
    res.status(500).json({
      message: 'Error fetching distinct unit eras',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  }
  // No client.release() needed
}
