#!/bin/bash
export JAVA_OPTS="-Xms64m -Xmx256m -XX:MaxMetaspaceSize=48m -XX:ReservedCodeCacheSize=32m -Xss256k -XX:+UseSerialGC -Dquarkus.jmx.enabled=false"

# Base
export APP_TIMEZONE=America/Mexico_City

# DB
export DB_HOST=localhost
export DB_PORT=5432

export DB_USER=postgres;
export DB_PASSWORD=password;
export DB_NAME=delicias_orders

# Keycloak
export REALM=delicias-app
export AUTH_SERVER_URL=http://localhost:9001
export AUTH_CLIENT_ID=deliciasapp-auth-client

export ADMIN_CLIENT_ID=admin-cli
export ADMIN_CLIENT_SECRET=PP8mW3FCVO2vbC0dsiJFU6A8czcBokvx


# Supabase
export SUPABASE_URL=https://dooexrpqhljvevkhqbjr.supabase.co
export SUPABASE_KEY=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRvb2V4cnBxaGxqdmV2a2hxYmpyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjE4MjI5MTEsImV4cCI6MjA3NzM5ODkxMX0.fhAKkxbiyf2ElTtHHCS75swvgVG5S1FlIb1eLishg-Y
export SUPABASE_BUCKET=delicias

# Client API`s
export CLIENT_API_USERS_URL=http://localhost:3000
export CLIENT_API_ZONES_URL=http://localhost:3001
export CLIENT_API_RESTAURANTS_URL=http://localhost:3002
export CLIENT_API_PRODUCTS_URL=http://localhost:3003;
export CLIENT_API_SHOPPINGCART_URL=http://localhost:3004;


java -Dquarkus.profile=dev -jar "./target/quarkus-app/quarkus-run.jar" > "batch.log" 2>&1 &
