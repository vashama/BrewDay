SELECT beer.id, beer.name
FROM beer, recipes
WHERE beer.last_brewed_date = recipes.created_date

SELECT beer.id, beer.name
FROM beer
INNER JOIN recipes
ON beer.last_brewed_date = recipes.created_date

SELECT beer.id, beer.name
FROM beer
LEFT JOIN recipes
ON beer.last_brewed_date = recipes.created_date

SELECT beer.id, beer.name, recipes.id
FROM beer
RIGHT JOIN recipes
ON beer.last_brewed_date = recipes.created_date

SELECT beer.id, beer.name, recipes.id
FROM beer
FULL JOIN recipes
ON beer.last_brewed_date = recipes.created_date
