# Stage 1: Build stage
FROM node:18-alpine AS builder

WORKDIR /usr/src/app

# Install Python, pip, and SQLite for database population
RUN apk add --no-cache python3 py3-pip sqlite sqlite-dev build-base

# Copy scripts and data needed for DB population from project root
COPY schema_sqlite.sql ./
COPY populate_db.py ./
COPY megameklab_converted_output ./megameklab_converted_output/

# Run populate_db.py to create and populate the SQLite database
RUN python3 populate_db.py

# Now proceed with Node.js app setup
# Copy app's package.json and package-lock.json
COPY battletech-editor-app/package.json ./
COPY battletech-editor-app/package-lock.json ./
# Copy app's tsconfig.json
COPY battletech-editor-app/tsconfig.json ./
# If next.config.js existed in app, it would be: COPY battletech-editor-app/next.config.js ./

# Install Node.js dependencies using npm ci for reproducible builds
RUN npm ci

# Copy the rest of the application source code from the battletech-editor-app directory
# into the current WORKDIR (/usr/src/app)
COPY battletech-editor-app/. .

# Build the Next.js application
RUN npm run build

# Stage 2: Production stage
FROM node:18-alpine

WORKDIR /usr/src/app

# Install SQLite runtime libraries, crucial for the app to use the SQLite DB
RUN apk add --no-cache sqlite

# Set NODE_ENV to production for Next.js
ENV NODE_ENV production

# Create a non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Copy necessary files from the builder stage
# package.json is needed for Next.js to determine how to run 'next start'
COPY --from=builder /usr/src/app/package.json ./package.json
# Copy .next and public directories
COPY --from=builder /usr/src/app/.next ./.next
COPY --from=builder /usr/src/app/public ./public

# Copy the populated SQLite database from the builder stage
COPY --from=builder --chown=appuser:appgroup /usr/src/app/battletech_dev.sqlite ./battletech_dev.sqlite

# Expose the port Next.js runs on (default 3000)
EXPOSE 3000

# Set the command to start the Next.js application
CMD ["npm", "start"]
