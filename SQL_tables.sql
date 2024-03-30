-- Table for customers
CREATE TABLE customers (
                           id SERIAL PRIMARY KEY,
                           first_name VARCHAR(255) NOT NULL,
                           last_name VARCHAR(255) NOT NULL,
                           document_number VARCHAR(255) NOT NULL UNIQUE,
                           document_type VARCHAR(50),
                           nationality VARCHAR(100),
                           email VARCHAR(255) UNIQUE,
                           phone VARCHAR(100),
                           date_of_birth DATE
);

-- Table for points usage concepts
CREATE TABLE points_usage_concepts (
                                       id SERIAL PRIMARY KEY,
                                       description VARCHAR(255) NOT NULL,
                                       points_required INT NOT NULL
);

-- Table for points assignment rules
CREATE TABLE points_assignment_rules (
                                         id SERIAL PRIMARY KEY,
                                         lower_limit NUMERIC(12, 2) NOT NULL,
                                         upper_limit NUMERIC(12, 2) NOT NULL,
                                         points_per_unit NUMERIC(12, 2) NOT NULL
);

-- Table for points expiry
CREATE TABLE points_expiry (
                               id SERIAL PRIMARY KEY,
                               start_date DATE NOT NULL,
                               end_date DATE NOT NULL,
                               duration_days INT NOT NULL
);

-- Table for points wallet
CREATE TABLE points_wallet (
                               id SERIAL PRIMARY KEY,
                               customer_id INT NOT NULL REFERENCES customers(id),
                               assignment_date DATE NOT NULL,
                               expiry_date DATE NOT NULL,
                               points_assigned INT NOT NULL,
                               points_used INT NOT NULL DEFAULT 0,
                               operation_amount NUMERIC(12, 2) NOT NULL,
                               CHECK (points_assigned >= points_used)
);

-- Table for points usage (transaction logs)
CREATE TABLE points_transactions (
                                     id SERIAL PRIMARY KEY,
                                     customer_id INT NOT NULL REFERENCES customers(id),
                                     points_used INT NOT NULL,
                                     usage_date DATE NOT NULL,
                                     concept_id INT NOT NULL REFERENCES points_usage_concepts(id),
                                     points_wallet_id INT NOT NULL REFERENCES points_wallet(id)
);
