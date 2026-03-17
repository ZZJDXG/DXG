#!/usr/bin/env bash
set -euo pipefail

echo "[codespaces] installing frontend dependencies"
cd /workspace/Glass-Bridge-Front-main
npm install

echo "[codespaces] compiling backend"
cd /workspace/Glass-Bridge-Back-main
mvn -q -DskipTests compile
