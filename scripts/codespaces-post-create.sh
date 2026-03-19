#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"

echo "[codespaces] installing frontend dependencies"
cd "${REPO_ROOT}/Glass-Bridge-Front-main"
npm install

echo "[codespaces] compiling backend"
cd "${REPO_ROOT}/Glass-Bridge-Back-main"
mvn -q -DskipTests compile
