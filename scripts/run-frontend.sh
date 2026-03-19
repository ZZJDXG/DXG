#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"

cd "${REPO_ROOT}/Glass-Bridge-Front-main"
export VITE_FRONTEND_PORT="${VITE_FRONTEND_PORT:-5174}"
npm run dev
