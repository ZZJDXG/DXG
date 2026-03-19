# DXG Codespaces Guide

This project is ready to run in GitHub Codespaces with backend, frontend, MySQL, and Redis.

## Services and ports

- Frontend (`Glass-Bridge-Front-main`): `5174`
- Backend (`Glass-Bridge-Back-main`): `8080`
- MySQL (`oa` database): `3306`
- Redis: `6379`

## First-time setup

1. Create a Codespace from the repository root.
2. Wait for post-create setup to finish (`scripts/codespaces-post-create.sh`).
3. Start backend in terminal A:

   ```bash
   cd /workspace
   bash scripts/run-backend.sh
   ```

4. Start frontend in terminal B:

   ```bash
   cd /workspace
   bash scripts/run-frontend.sh
   ```

5. Open frontend at forwarded port `5174`.

## Quick health checks

Run these commands in a third terminal:

```bash
cd /workspace
ss -ltnp | grep -E ':5174|:8080'
```

Optional API check:

```bash
cd /workspace
curl -sS -X POST 'http://127.0.0.1:5174/api/users/login' \
  -H 'Content-Type: application/json' \
  -d '{"tel":"13800138000","password":"123456"}'
```

If successful, response should include `"code":200`.

## Demo account

- Phone: `13800138000`
- Password: `123456`

## Common issues

### 1) MySQL auth error

Symptom:

```text
Access denied for user 'root'@'...'
```

Fix:

- Use the project script `bash scripts/run-backend.sh` from `/workspace`.
- The script applies the correct local defaults for Codespaces.

### 2) Port 8080 already in use

Symptom:

```text
The Tomcat connector configured to listen on port 8080 failed to start
```

Fix:

```bash
cd /workspace
pkill -f 'spring-boot:run' || true
pkill -f 'SpringbootTestApplication' || true
bash scripts/run-backend.sh
```

### 3) Push blocked by Git LFS

Symptom:

```text
This repository is configured for Git LFS but 'git-lfs' was not found
```

Fix:

```bash
sudo apt-get update
sudo apt-get install -y git-lfs
git lfs install
git lfs update --force
```

## Internal service hosts

- MySQL: `mysql:3306`
- Redis: `redis:6379`
