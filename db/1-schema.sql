CREATE TABLE IF NOT EXISTS irm_user (
    email varchar(320) PRIMARY KEY,
    first_name varchar(255),
    last_name varchar(255),
    registration_confirmed BOOLEAN DEFAULT FALSE,
    registration_time TIMESTAMPTZ DEFAULT NOW()
);
