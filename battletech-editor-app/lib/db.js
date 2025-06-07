const Database = require('better-sqlite3');
const path = require('path');

// Assuming the Next.js app's CWD is 'battletech-editor-app'
// and the SQLite DB file will be in this directory.
const dbPath = process.env.SQLITE_DB_PATH || path.join(process.cwd(), 'battletech_editor.sqlite');

let db;
try {
    db = new Database(dbPath); // { verbose: console.log } can be added for debugging
    console.log(`Connected to SQLite database at ${dbPath}`);
    db.pragma('journal_mode = WAL');
} catch (error) {
    console.error('Failed to connect to SQLite database:', dbPath, error);
    throw error;
}

module.exports = db;
