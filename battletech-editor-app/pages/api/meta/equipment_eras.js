import db from '../../../lib/db'; // Changed import

export default function handler(req, res) { // Removed async
  try {
    // NOTE: The 'equipment' table in the current schema does not have a direct 'era' column.
    // Era information, if available, would be within the 'data' JSON field.
    // This API will return an empty array as per the current schema.
    // If equipment eras are needed, the data model or this query needs revision
    // to extract data from the JSON 'data' field.
    console.warn("API Warning: Attempted to fetch 'era' from 'equipment' table, but 'era' column does not exist. Returning empty array.");

    // Example of how it might be queried if 'era' was in the JSON data:
    // const stmt = db.prepare("SELECT DISTINCT json_extract(data, '$.era') as era FROM equipment WHERE json_extract(data, '$.era') IS NOT NULL AND TRIM(json_extract(data, '$.era')) <> '' ORDER BY era ASC");
    // const rows = stmt.all();
    // const values = rows.map(row => row.era).filter(era => era !== null);
    // res.status(200).json(values);

    res.status(200).json([]); // Return empty array
  } catch (error) {
    console.error('Error fetching distinct equipment eras:', error);
    res.status(500).json({
      message: 'Error fetching distinct equipment eras',
      error: error.message,
      stack: process.env.NODE_ENV === 'development' ? error.stack : undefined,
    });
  }
  // No client.release() needed
}
