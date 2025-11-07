#!/bin/bash

echo "=================================================="
echo "  🌐 DIGITAL LIBRARY - Frontend Server"
echo "=================================================="

cd frontend

echo ""
echo "Starting HTTP server on port 8080..."
echo ""
echo "Frontend will be available at:"
echo "  👉 http://localhost:8080"
echo ""
echo "Make sure backend is running on:"
echo "  👉 http://localhost:7000"
echo ""
echo "Press Ctrl+C to stop the server"
echo "=================================================="
echo ""

# Try different Python versions
if command -v python3 &> /dev/null; then
    python3 -m http.server 8080
elif command -v python &> /dev/null; then
    python -m SimpleHTTPServer 8080
else
    echo "ERROR: Python not found. Please install Python."
    exit 1
fi
