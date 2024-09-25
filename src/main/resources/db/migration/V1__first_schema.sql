CREATE SEQUENCE users_seq;



CREATE TABLE users (
                       id BIGINT PRIMARY KEY DEFAULT nextval('users_seq'),
                       first_name TEXT,
                       last_name TEXT,
                       email TEXT UNIQUE NOT NULL,
                       password TEXT NOT NULL,
                       activated BOOLEAN NOT NULL DEFAULT FALSE
);

ALTER SEQUENCE users_seq OWNED BY users.id;

CREATE SEQUENCE incomes_seq;
CREATE TABLE incomes (
                         id BIGINT PRIMARY KEY DEFAULT nextval('incomes_seq'),
                         income_amount DOUBLE PRECISION,
                         income_date DATE,
                         income_time TIME,
                         special_note TEXT,
                         user_id BIGINT REFERENCES users(id) ON DELETE CASCADE
);

CREATE SEQUENCE expenses_seq;
CREATE TABLE expenses (
                          id BIGINT PRIMARY KEY DEFAULT nextval('expenses_seq'),
                          expense_amount DOUBLE PRECISION,
                          expense_date DATE,
                          expense_time TIME,
                          special_note TEXT,
                          user_id BIGINT REFERENCES users(id) ON DELETE CASCADE
);

ALTER SEQUENCE incomes_seq OWNED BY incomes.id;
ALTER SEQUENCE expenses_seq OWNED BY expenses.id;