CREATE TABLE beer (
    id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    last_brewed_date DATE,
    last_brewed_name VARCHAR(255),
    brew_note TEXT,
    PRIMARY KEY (ID)
);

INSERT INTO beer VALUES 
(0001, 'Budlight', 'yellow', '2017-08-31', null, null), 
(0002, 'Collis', 'light yellow', '2017-01-31', null, null),
(0003, 'Tighers', 'brown', null, null, 'very bitter'),
(0004, 'Ashahi', 'yellow', '2017-08-31', 'Collis', null)
