# Use a base image for Node.js
FROM node:16 as build
# Set the working directory
WORKDIR /app
# Copy package.json and install dependencies
COPY package.json ./
RUN npm install
# Copy the rest of the project files
COPY . .
# Build the React app
RUN npm run build

# Use Nginx to serve the frontend
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
# Nginx starts automatically when the container starts
