# his

The Patient Management Service within the Hospital Information System (HIS)  is a microservice that efficiently handles patient records, including personal information and medical history. This service integrates seamlessly with other modules like billing and inventory, providing a scalable and maintainable solution that enhances the overall management of patient data.


## Local Development

### PostgreSQL

The local PostgreSQL instance is managed using Docker Compose.

#### Prerequisites

- Docker Engine
- Docker Compose

#### 1. Configure environment variables

Create a `.env` file in `infrastructure/docker/postgres/` based on `.env.example`.

Set the following variable:

```dotenv
PATIENT_MANAGEMENT_SERVICE_DB_PASSWORD=your_local_password
```

Do not commit `.env` or any file containing real credentials.

#### 2. Start PostgreSQL

Run the following command from the project root:

```bash
docker compose \
  --env-file infrastructure/docker/postgres/.env \
  -f infrastructure/docker/postgres/compose.yaml \
  up -d
```

#### 3. Verify the container

```bash
docker compose \
  --env-file infrastructure/docker/postgres/.env \
  -f infrastructure/docker/postgres/compose.yaml \
  ps
```

#### 4. Run the application

Configure the `dev` Spring profile and set `PATIENT_MANAGEMENT_SERVICE_DB_PASSWORD` in the application run configuration.

Start `patient-management-service`. Liquibase applies the database schema migrations automatically.

#### 5. Stop PostgreSQL

```bash
docker compose \
  --env-file infrastructure/docker/postgres/.env \
  -f infrastructure/docker/postgres/compose.yaml \
  down
```

The named Docker volume is preserved, so database data remains available after restarting PostgreSQL.

**Warning:** `docker compose down -v` removes the Compose-managed volume and deletes its stored database data. Use it only when a full local database reset is intended.