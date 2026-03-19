#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"

if ! command -v git-lfs >/dev/null 2>&1; then
	echo "[codespaces] installing git-lfs"
	if command -v sudo >/dev/null 2>&1; then
		sudo apt-get update
		sudo apt-get install -y git-lfs
	else
		apt-get update
		apt-get install -y git-lfs
	fi
fi

echo "[codespaces] initializing git-lfs"
git -C "${REPO_ROOT}" lfs install
git -C "${REPO_ROOT}" lfs update --force

echo "[codespaces] installing frontend dependencies"
cd "${REPO_ROOT}/Glass-Bridge-Front-main"
npm install

echo "[codespaces] compiling backend"
cd "${REPO_ROOT}/Glass-Bridge-Back-main"
mvn -q -DskipTests compile
