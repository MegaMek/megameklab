import db from '../../../lib/db'; // Changed import

export default function handler(req, res) { // Removed async
  try {
    const stmt = db.prepare("SELECT DISTINCT type FROM equipment WHERE type IS NOT NULL AND TRIM(type) <> '' ORDER BY type ASC");
    const rows = stmt.all();
    const values = rows.map(row => row.type);
    res.status(200).json(values);
  } catch (error) {
    console.error('Error fetching distinct equipment categories (types):', error);
    res.status(500).json({
      message: 'Error fetching distinct equipment categories (types)',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  }
  // No client.release() needed
}
