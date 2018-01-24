CREATE TABLE ingredient (
    id INT NOT NULL,
    name VARCHAR (255) NOT NULL,
    type VARCHAR (255),
    last_purchase_date DATE,
    mount DOUBLE precision,
    balance_quality DOUBLE precision,
    PRIMARY KEY (ID)
);
