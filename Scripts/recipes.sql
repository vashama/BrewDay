CREATE TABLE recipes (
    id INT NOT NULL,
    created_date DATE NOT NULL,
    batch_size VARCHAR (255) NOT NULL,
    ABV DOUBLE precision,
    instructions VARCHAR (255),
    boil_time TIME NOT NULL,
    brew_type VARCHAR (255),
    PRIMARY KEY (ID)
);

INSERT INTO recipes VALUES 
(0001, '2017-08-31', '3', 1.7, null, '01:00:00', null), 
(0002, '2017-08-31', '2', 2.1, null, '01:15:00', null), 
(0003, '2017-08-31', '1', 1.9, null, '00:30:00', null)
