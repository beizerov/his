# HIS

The Patient Management Service within the Hospital Information System (HIS) is a microservice that manages patient records, including personal information and medical history. It integrates with other HIS modules to provide a scalable and maintainable solution for patient data management.

## Local Development

### PostgreSQL

The local PostgreSQL instance is managed using Docker Compose. Database credentials are configured through environment variables, and database data is stored in a persistent Docker volume.

#### Prerequisites

- Docker Engine
- Docker Compose

#### 1. First-time setup

**Configure environment variables**

Create a `.env` file in `infrastructure/docker/postgres/` based on `.env.example`.

Set the database password:

```dotenv
PATIENT_MANAGEMENT_SERVICE_DB_PASSWORD=your_local_password
```

Do not commit `.env` or any file containing real credentials.

**Create and start the PostgreSQL container**

Run the following command from the project root:

```bash
docker compose \
  --env-file infrastructure/docker/postgres/.env \
  -f infrastructure/docker/postgres/compose.yaml \
  up -d
```

Docker Compose creates the container and starts PostgreSQL. On the first initialization, PostgreSQL creates the configured database and user, and initializes the persistent data directory.

**Verify the container status**

```bash
docker compose \
  --env-file infrastructure/docker/postgres/.env \
  -f infrastructure/docker/postgres/compose.yaml \
  ps
```

Check the container logs if necessary:

```bash
docker compose \
  --env-file infrastructure/docker/postgres/.env \
  -f infrastructure/docker/postgres/compose.yaml \
  logs -f postgres
```

#### 2. Run the application

Configure the `dev` Spring profile and set `PATIENT_MANAGEMENT_SERVICE_DB_PASSWORD` in the application's run configuration.

Start `patient-management-service` from the IDE or run:

```bash
PATIENT_MANAGEMENT_SERVICE_DB_DEV_PASSWORD='your_local_password' \
./gradlew :patient-management-service:bootRun \
  --args='--spring.profiles.active=dev'
```

Liquibase applies database schema migrations automatically when the application starts.

#### 3. Stop PostgreSQL

To stop PostgreSQL while preserving the container and database data, run:

```bash
docker compose \
  --env-file infrastructure/docker/postgres/.env \
  -f infrastructure/docker/postgres/compose.yaml \
  stop
```

The container remains on the system, but PostgreSQL stops running.

#### 4. Start PostgreSQL again

To restart the existing container after using `stop`, run:

```bash
docker compose \
  --env-file infrastructure/docker/postgres/.env \
  -f infrastructure/docker/postgres/compose.yaml \
  start
```

The existing database and its data are preserved.

#### 5. Remove the container without deleting database data

To stop PostgreSQL and remove its container and Compose-managed network, run:

```bash
docker compose \
  --env-file infrastructure/docker/postgres/.env \
  -f infrastructure/docker/postgres/compose.yaml \
  down
```

The named Docker volume is preserved, so the database data remains available.

To recreate the container later, run `up -d` again:

```bash
docker compose \
  --env-file infrastructure/docker/postgres/.env \
  -f infrastructure/docker/postgres/compose.yaml \
  up -d
```

PostgreSQL will reuse the existing volume and its database data.

#### 6. Remove the container and delete all database data

**Warning:** This operation permanently deletes the database data stored in the Compose-managed volume.

Run the following command only when a complete local database reset is intended:

```bash
docker compose \
  --env-file infrastructure/docker/postgres/.env \
  -f infrastructure/docker/postgres/compose.yaml \
  down -v
```

This stops and removes the container, removes the Compose-managed network, and deletes the named volume containing the PostgreSQL data.

The next `up -d` will initialize a fresh database using the current Compose configuration and environment variables.

#### Quick reference

| Action | Command |
|---|---|
| First launch or recreate container | `up -d` |
| Stop PostgreSQL, preserve container | `stop` |
| Start existing container | `start` |
| Remove container, preserve data | `down` |
| Remove container and database data | `down -v` |
| View container status | `ps` |
| Follow PostgreSQL logs | `logs -f postgres` |

All commands above use the same Compose file and environment file shown in the examples.