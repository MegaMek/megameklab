# Stage 1: Build stage
FROM node:18-alpine AS builder

# Set working directory
WORKDIR /usr/src/app

# Copy package.json and package-lock.json (or yarn.lock if you use Yarn)
COPY package*.json ./

# Install dependencies
# If you use yarn, replace with:
# COPY yarn.lock ./
# RUN yarn install --frozen-lockfile
RUN apk add --no-cache ca-certificates
RUN npm install

# Copy the rest of the application source code
COPY . .

# Build the Next.js application
# Ensure your package.json has a "build" script e.g., "next build"
RUN npm run build

# Stage 2: Production stage
FROM node:18-alpine

WORKDIR /usr/src/app

# Copy built assets from the builder stage
COPY --from=builder /usr/src/app/.next ./.next
COPY --from=builder /usr/src/app/public ./public
COPY --from=builder /usr/src/app/package.json ./package.json
COPY --from=builder /usr/src/app/node_modules ./node_modules # Or run npm install --production if you prefer smaller image but longer startup

# Expose port 3000
EXPOSE 3000

# Set the command to start the Next.js application
# Ensure your package.json has a "start" script e.g., "next start"
CMD ["npm", "start"]
