# Stage 1: Build stage
FROM node:18-alpine AS builder

WORKDIR /usr/src/app

# Install build-time dependencies for better-sqlite3 and python scripts
# build-base for native compilation, python3 for running scripts if needed here,
# sqlite for CLI interaction if schema is applied in builder
RUN apk add --no-cache ca-certificates build-base python3 sqlite

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# Stage 2: Production stage
FROM node:18-alpine

WORKDIR /usr/src/app

# Install runtime dependencies: python for populate_db.py, sqlite for CLI (if needed for startup script)
RUN apk add --no-cache python3 sqlite

# Define the database path environment variable, used by Next.js app and population script
ENV SQLITE_DB_PATH=/usr/src/app/battletech_editor.sqlite

# Create a directory for database setup scripts and data
RUN mkdir -p /usr/src/app/db_setup
WORKDIR /usr/src/app/db_setup

# Copy schema definition, population script, and data files from the project root
# These paths are relative to the Docker build context (battletech-editor-app/)
# So, ../schema.sql means schema.sql from the project root.
COPY ../schema.sql ./schema.sql
COPY ../populate_db.py ./populate_db.py
COPY ../megameklab_converted_output ./megameklab_converted_output/

# Run database schema creation and population
# Ensure populate_db.py can find 'megameklab_converted_output' in its CWD
# The populate_db.py script uses SQLITE_DB_PATH environment variable for the db location.
RUN sqlite3 $SQLITE_DB_PATH < ./schema.sql && \
    python3 ./populate_db.py

# Switch back to the main application directory
WORKDIR /usr/src/app

# Copy built assets from the builder stage
COPY --from=builder /usr/src/app/.next ./.next
COPY --from=builder /usr/src/app/public ./public
COPY --from=builder /usr/src/app/package.json ./package.json
# Copy node_modules from builder. If better-sqlite3 was built, its native parts are included.
COPY --from=builder /usr/src/app/node_modules ./node_modules

# Ensure the node user can access the database file created by root
# (sqlite3 and python3 commands above run as root)
RUN chown -R node:node /usr/src/app/battletech_editor.sqlite /usr/src/app/db_setup

# Expose port 3000
EXPOSE 3000

# Set the command to start the Next.js application
CMD ["npm", "start"]
