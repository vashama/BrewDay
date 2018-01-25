SELECT *
FROM users
WHERE name LIKE 'A%'

SELECT *
FROM users
WHERE name LIKE 'H%' 
OR name LIKE 'R%'

SELECT *
FROM users
WHERE name SIMILAR TO '(A|H|R)%'
