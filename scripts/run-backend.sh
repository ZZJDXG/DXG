#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"

cd "${REPO_ROOT}/Glass-Bridge-Back-main"

# Provide safe defaults for containerized local development.
export SERVER_PORT="${SERVER_PORT:-8080}"
export SPRING_DATASOURCE_DRUID_URL="${SPRING_DATASOURCE_DRUID_URL:-jdbc:mysql://mysql:3306/oa?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true}"
export SPRING_DATASOURCE_DRUID_USERNAME="${SPRING_DATASOURCE_DRUID_USERNAME:-root}"
# Some environments inject an outdated password into SPRING_DATASOURCE_DRUID_PASSWORD.
# Use BACKEND_DB_PASSWORD for explicit override; otherwise force the known-good default.
export SPRING_DATASOURCE_DRUID_PASSWORD="${BACKEND_DB_PASSWORD:-20040329hjsfw}"
export SPRING_REDIS_HOST="${SPRING_REDIS_HOST:-redis}"
export SPRING_REDIS_PORT="${SPRING_REDIS_PORT:-6379}"

mvn spring-boot:run
