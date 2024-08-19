CREATE TABLE patient (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    second_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20) UNIQUE NOT NULL,
    address VARCHAR(100) NOT NULL
);

INSERT INTO patient (first_name, second_name, email, phone_number, address)
VALUES
('John', 'Doe', 'john.doe@example.com', '123-456-7890', '123 Elm Street'),
('Jane', 'Smith', 'jane.smith@example.com', '234-567-8901', '456 Oak Avenue'),
('Michael', 'Brown', 'michael.brown@example.com', '345-678-9012', '789 Pine Road');