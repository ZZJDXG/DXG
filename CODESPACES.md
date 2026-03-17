# DXG Codespaces

This repository is configured for Codespaces with:

- `Glass-Bridge-Front-main` on port `5173`
- `Glass-Bridge-Back-main` on port `8080`
- `mysql` service with database `oa`
- `redis` service for future cache and session features

## First run

1. Create a Codespace from the `DXG` repository root.
2. Wait for the post-create script to finish.
3. Start the backend:

   ```bash
   bash scripts/run-backend.sh
   ```

4. Open a second terminal and start the frontend:

   ```bash
   bash scripts/run-frontend.sh
   ```

5. Open the forwarded frontend port `5173`.

## Demo account

- Phone: `13800138000`
- Password: `123456`

## Internal service hosts

- MySQL: `mysql:3306`
- Redis: `redis:6379`
