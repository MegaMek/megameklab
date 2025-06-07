# Stage 1: Build stage
FROM node:18-alpine AS builder

WORKDIR /usr/src/app

# Install Python, pip, and SQLite for database population
RUN apk add --no-cache python3 py3-pip sqlite sqlite-dev build-base

# Copy scripts and data needed for DB population from project root
# (Adjust paths if these are located differently relative to the Dockerfile's context)
COPY ../schema_sqlite.sql ./
COPY ../populate_db.py ./
COPY ../megameklab_converted_output ./megameklab_converted_output

# Run populate_db.py to create and populate the SQLite database
# This assumes populate_db.py looks for schema_sqlite.sql and megameklab_converted_output in its current directory
# and creates battletech_dev.sqlite in its current directory.
RUN python3 populate_db.py

# Now proceed with Node.js app setup
COPY package*.json ./
RUN npm install

COPY . .

# Ensure Next.js build does not accidentally overwrite the DB or other copied files
# if it cleans the output directory. Usually, .next and public are the main concerns.
RUN npm run build

# Stage 2: Production stage
FROM node:18-alpine

WORKDIR /usr/src/app

# Install SQLite runtime libraries
RUN apk add --no-cache sqlite

# Set NODE_ENV to production (good practice)
ENV NODE_ENV production

# Copy built assets from the builder stage
COPY --from=builder /usr/src/app/.next ./.next
COPY --from=builder /usr/src/app/public ./public
COPY --from=builder /usr/src/app/package.json ./package.json
# Instead of copying node_modules, install production dependencies
# This can make the image smaller if devDependencies are numerous.
# However, for simplicity and to match original, we can keep copying them.
# If issues arise with native modules, installing them here might be better.
COPY --from=builder /usr/src/app/node_modules ./node_modules
# RUN npm ci --only=production # Alternative to copying node_modules

# Copy the populated SQLite database from the builder stage
COPY --from=builder /usr/src/app/battletech_dev.sqlite ./battletech_dev.sqlite

# Expose port 3000
EXPOSE 3000

# Set the command to start the Next.js application
CMD ["npm", "start"]
