CREATE TABLE country (
    id SERIAL PRIMARY KEY,
    cca2 VARCHAR(2) NOT NULL,
    cca3 VARCHAR(3) NOT NULL,
    name_common VARCHAR(128) NOT NULL,
    name_official VARCHAR(256),
    capital VARCHAR(128),
    region VARCHAR(64),
    subregion VARCHAR(64),
    lat DOUBLE PRECISION,
    lng DOUBLE PRECISION,
    flag VARCHAR(4)
);