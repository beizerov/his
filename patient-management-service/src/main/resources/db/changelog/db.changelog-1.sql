CREATE TABLE patient (
    id UUID PRIMARY KEY DEFAULT uuidv7(),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20) UNIQUE NOT NULL,
    address VARCHAR(100) NOT NULL
);