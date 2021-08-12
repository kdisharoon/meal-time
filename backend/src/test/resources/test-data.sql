TRUNCATE users, recipes, ingredients, recipe_ingredients, user_recipes, meal_plan_user_recipes, user_meal_plan CASCADE;


insert into users (user_id,username, password_hash,  role)
values  (1, 'user1', 'test1', 'ROLE_USER'),
        (2, 'user2', 'test2', 'ROLE_USER');

INSERT INTO recipes (recipe_id , preparation, recipe_name, prep_time, cook_time, recipe_type, recipe_img)
VALUES (1, 'prep1', 'test1', 10, 10, 'entree', 'someUrl'),
        (2, 'prep2', 'test2', 10, 10, 'entree', 'someUrl'),
        (3, 'prep3', 'test3', 10, 10, 'entree', 'someUrl'),
        (4, 'prep4', 'test4', 10, 10, 'entree', 'someUrl');

insert into ingredients (ingredient_id, ingredient_name)
values  (1, 'food1'),
        (2, 'food2'),
        (3, 'food3'),
        (4, 'food4'),
        (5, 'food5'),
        (6, 'food6');

insert into recipe_ingredients (recipe_id, ingredient_id, measurement_amount, measurement_unit)
values (1, 1, 1, 'cup'),
        (1, 2, 1, 'cup'),
        (2, 3, 1, 'cup'),
        (2, 5, 1, 'cup'),
        (3, 1, 1, 'cup'),
        (3, 3, 1, 'cup'),
        (3, 6, 1, 'cup'),
        (4, 2, 1, 'cup'),
        (4, 4, 1, 'cup'),
        (4, 5, 1, 'cup');

insert into user_recipes (user_id, recipe_id)
values  (1,2),
        (1,3),
        (1,4),
        (2,1);

insert into user_meal_plan (meal_plan_id, user_id, meal_plan_name)
values (1, 1, 'testing');

insert into meal_plan_user_recipes (meal_plan_id,recipe_id, day, meal)
values (1, 2, 'testday', 'testmeal'),
       (1, 4, 'testday2', 'testmeal2');




