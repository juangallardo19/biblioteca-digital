#!/bin/bash

echo "=================================================="
echo "  📚 DIGITAL LIBRARY SYSTEM - Starting..."
echo "=================================================="

# Colors
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Check Java
echo -e "\n${YELLOW}[1/4]${NC} Checking Java..."
if ! command -v java &> /dev/null; then
    echo -e "${RED}ERROR: Java not found. Please install Java 11 or higher.${NC}"
    exit 1
fi
java -version
echo -e "${GREEN}✓ Java OK${NC}"

# Check Maven
echo -e "\n${YELLOW}[2/4]${NC} Checking Maven..."
if ! command -v mvn &> /dev/null; then
    echo -e "${RED}ERROR: Maven not found. Please install Maven.${NC}"
    exit 1
fi
mvn -v | head -1
echo -e "${GREEN}✓ Maven OK${NC}"

# Compile backend
echo -e "\n${YELLOW}[3/4]${NC} Compiling backend..."
cd backend
mvn clean compile
if [ $? -ne 0 ]; then
    echo -e "${RED}ERROR: Backend compilation failed.${NC}"
    echo -e "${YELLOW}Tip: Make sure you have internet connectivity to download dependencies.${NC}"
    exit 1
fi
echo -e "${GREEN}✓ Backend compiled${NC}"

# Start backend
echo -e "\n${YELLOW}[4/4]${NC} Starting backend server..."
echo -e "${GREEN}✓ Backend starting on http://localhost:7000${NC}"
echo ""
echo "=================================================="
echo "  📚 BACKEND RUNNING"
echo "=================================================="
echo ""
echo "API Endpoints:"
echo "  • http://localhost:7000/api/config"
echo "  • http://localhost:7000/api/books"
echo "  • http://localhost:7000/api/users"
echo ""
echo "To start frontend, open a new terminal and run:"
echo "  cd frontend && python3 -m http.server 8080"
echo ""
echo "Press Ctrl+C to stop the server"
echo "=================================================="
echo ""

mvn exec:java -Dexec.mainClass="biblioteca.Main"
