CREATE TABLE equipment (
    id INT NOT NULL,
    name VARCHAR (255) NOT NULL,
    type VARCHAR (255),
    capacity DOUBLE precision,
    PRIMARY KEY (ID)
);

INSERT INTO equipment VALUES 
(0001, 'hops', 5.3), 
(0002, 'wheat', 11), 
(0003, 'water', 500), 
(0004, 'yeast', 3.1)
