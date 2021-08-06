TRUNCATE recipes, ingredients, recipe_ingredients, user_recipes, meal_plan_user_recipes, user_meal_plan CASCADE;


INSERT INTO recipes (preparation, recipe_name, prep_time, cook_time, recipe_type, recipe_img) VALUES ('You will need fresh basil, fresh mozz, and fresh tomato on a plate. Layer the cheese, basil, and tomato. Drizzle balsamic vinegar and Enjoy!', 'Caprese Salad', 10, null, 'Salad', 'https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2019/07/Caprese-Salad-4.jpg');
INSERT INTO recipes (preparation, recipe_name, prep_time, cook_time, recipe_type, recipe_img) VALUES ('Prepare the tortilini, drain. Cook the meat, then add the sauce and cream cheese. Put the tortellini into the backing dish and pour the meat/cheese/sauce mixture on top. Sprinkle the parmesan cheese over top. Bake for 20 min.', 'Cheesey Tortellini Pasta Bake', 15, 20, 'Entree', 'https://images-gmi-pmc.edge-generalmills.com/de2a3f47-4a5a-4254-baa2-4e1e8cbc3ada.jpg');
INSERT INTO recipes (preparation, recipe_name, prep_time, cook_time, recipe_type, recipe_img) VALUES('Thaw tater tots, add them to greased muffin pan, dividing evenly. Smash them, fill each cup with cooked meat and chopped veggies(you can use onions, mushrooms, and/or peppers, to name a few). Scramble eggs, add seasoning of your choosing, then pour eggs into each cup, evenly. Top with cheese. Bake at 350 degrees for 18-20 minutes.', 'Mini Omelets', 10, 20, 'Breakfast', 'https://www.skinnytaste.com/wp-content/uploads/2016/02/loadedbakedeggomeletmuffins-7-550x825.jpg');
INSERT INTO recipes (preparation, recipe_name, prep_time, cook_time, recipe_type, recipe_img) VALUES('Place raw chicken in the slow cooker.  Layer raw potatoes and carrots on top. In a small sauce pan, melt butter.  Add garlic, salt, pepper, dried thyme, and dried parsley. Pour mixture over the chicken and potato/veggies. Cook on high for 4 hours.', 'Slow Cooker Garlic Butter Chicken', 5, 240, 'Entree', 'https://www.themagicalslowcooker.com/wp-content/uploads/2017/02/garlic-butter-chicken-and-veggies-2-2.jpg.webp');
INSERT INTO recipes (preparation, recipe_name, prep_time, cook_time, recipe_type, recipe_img) VALUES('Add ingredients to the blender. Blend for 45 seconds and Enjoy!', 'Skinny Wendys Frosty', 4, null, 'Dessert', 'http://4.bp.blogspot.com/-rnSN27O0AEg/UcjFUQjzZUI/AAAAAAAAAdA/IapK8R-AYtk/s640/IMG_2084_edited-1.jpg');
INSERT INTO recipes (preparation, recipe_name, prep_time, cook_time, recipe_type, recipe_img) VALUES('Cut the cinnamon rolls into quarters, and toss them with the apple pie filling. Spread the mixture onto the bottom of a small greased baking dish and bake for 25 minutes in a 375 degree oven.', 'Easy Cinnamon Rolle Apple Cobbler', 3, 25, 'Dessert', 'https://www.thelazydish.com/wp-content/uploads/2020/09/easy-apple-cobbler-recipe.jpg');

INSERT INTO ingredients (ingredient_name) VALUES ('Mozzerella Cheese');
INSERT INTO ingredients (ingredient_name) VALUES ('Basil');
INSERT INTO ingredients (ingredient_name) VALUES ('Tomato');
INSERT INTO ingredients (ingredient_name) VALUES ('Balsamic Vinegar');

INSERT INTO ingredients (ingredient_name) VALUES ('Ground Beef');
INSERT INTO ingredients (ingredient_name) VALUES ('Pasta Sauce');
INSERT INTO ingredients (ingredient_name) VALUES ('Cheese Tortellini');
INSERT INTO ingredients (ingredient_name) VALUES ('Cream Cheese');
INSERT INTO ingredients (ingredient_name) VALUES ('Parmesean Cheese');

INSERT INTO ingredients (ingredient_name) VALUES ('Chopped Vegetables');
INSERT INTO ingredients (ingredient_name) VALUES ('Cooked Sausage');
INSERT INTO ingredients (ingredient_name) VALUES ('Frozen Tater Tots');
INSERT INTO ingredients (ingredient_name) VALUES ('Eggs');
INSERT INTO ingredients (ingredient_name) VALUES ('Shredded Cheese');

INSERT INTO ingredients (ingredient_name) VALUES ('Boneless Chicken Breasts');
INSERT INTO ingredients (ingredient_name) VALUES ('Baby Carrots');
INSERT INTO ingredients (ingredient_name) VALUES ('Yukon Gold Potatoes');
INSERT INTO ingredients (ingredient_name) VALUES ('Salted Butter');
INSERT INTO ingredients (ingredient_name) VALUES ('Minced Garlic');
INSERT INTO ingredients (ingredient_name) VALUES ('Dried Thyme');
INSERT INTO ingredients (ingredient_name) VALUES ('Dried Parsley');

INSERT INTO ingredients (ingredient_name) VALUES ('Almond Milk');
INSERT INTO ingredients (ingredient_name) VALUES ('Ice Cubes');
INSERT INTO ingredients (ingredient_name) VALUES ('Vanilla');
INSERT INTO ingredients (ingredient_name) VALUES ('Unsweetened Cocoa Powder');
INSERT INTO ingredients (ingredient_name) VALUES ('Banana');

INSERT INTO ingredients (ingredient_name) VALUES ('Can of Apple Pie Filling');
INSERT INTO ingredients (ingredient_name) VALUES ('Pillsbury Cinnamon Rolls');

INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES (1, 1, 'cup', 1);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES (2,  1, 'tablespoon', 3);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES (3,  1, 'cup', 1);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES (4,  1, 'tablespoon', 2);

INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(5, 2, 'lb', 1);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(6, 2, 'oz', 24);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(7, 2, 'oz', 20);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(8, 2, 'oz', 8);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(1, 2, 'cup', 1);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(9, 2, 'cup', 1);

INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(10, 3, 'cup', 2);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(11, 3, 'oz', 3);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(12, 3, 'bag', 1);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(13, 3, 'egg', 10);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(14, 3, 'cup', 2);

INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(15, 4, 'lb', 1);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(16, 4, 'lb', 1);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(17, 4, 'lb', 2);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(18, 4, 'cup', 1);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(19, 4, 'tablespoon', 1);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(20, 4, 'teaspoon', 1);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(21, 4, 'teaspoon', 1);

INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(22, 5, 'cup', 1);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(23, 5, 'ice cubes', 15);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(24, 5, 'teaspoon', 1);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(25, 5, 'tablespoon', 1);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(26, 5, 'banana', 1);

INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(27, 6, 'oz', 20);
INSERT INTO recipe_ingredients (ingredient_id, recipe_id, measurement_unit, measurement_amount) VALUES(28, 6, 'tube', 1);


INSERT INTO user_meal_plan (meal_plan_name) VALUES ('Healthy');
INSERT INTO user_meal_plan (meal_plan_name) VALUES ('Dinner');