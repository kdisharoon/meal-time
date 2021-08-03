BEGIN TRANSACTION;

DROP TABLE IF EXISTS user_meal_plan;
DROP TABLE IF EXISTS meal_plan_user_recipes;
DROP TABLE IF EXISTS user_recipes;
DROP TABLE IF EXISTS recipes;
DROP TABLE IF EXISTS recipe_ingredients;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
  CREATE SEQUENCE seq_ingredient_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
  CREATE SEQUENCE seq_recipe_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
  CREATE SEQUENCE seq_user_meal_plan_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE recipes (
        recipe_id int PRIMARY KEY DEFAULT NEXTVAL('seq_recipe_id'),
        preparation varchar(500) NOT NULL,
        recipe_name varchar(100) NULL,
        prep_time int NULL,
        cook_time int NULL,
        recipe_type varchar(100),
        recipe_img varchar(500) NULL
        );

CREATE TABLE ingredients (
        ingredient_id int PRIMARY KEY DEFAULT NEXTVAL('seq_ingredient_id'),
        ingredient_name varchar(50) NOT NULL
);

CREATE TABLE user_meal_plan (
        meal_plan_id int PRIMARY KEY DEFAULT NEXTVAL('seq_user_meal_plan_id'),
        user_id serial NOT NULL,
        meal_plan_name varchar(100) NOT NULL,
        CONSTRAINT FK_user_meal_plan_users FOREIGN KEY (user_id) REFERENCES users(user_id)
        
);

CREATE TABLE recipe_ingredients (
        ingredient_id serial NOT NULL,
        recipe_id serial NOT NULL,
        measurement_unit varchar(50),
        measurement_amount int NOT NULL,
        
        CONSTRAINT FK_recipe_ingredients_recipes FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id),
        CONSTRAINT FK_recipe_ingredients_ingredients FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id),
        CONSTRAINT pk_recipe_ingredients PRIMARY KEY (ingredient_id, recipe_id, measurement_amount)
);
        
CREATE TABLE user_recipes (
        recipe_id serial NOT NULL,
        user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
        
        CONSTRAINT fk_user_recipes_recipes FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id),
        CONSTRAINT fk_user_recipes_users FOREIGN KEY (user_id) REFERENCES users(user_id),
        CONSTRAINT pk_user_recipes PRIMARY KEY (recipe_id, user_id)
        
);

CREATE TABLE meal_plan_user_recipes (
        meal_plan_id serial NOT NULL,
        recipe_id serial NOT NULL,
        
        CONSTRAINT fk_meal_plan_user_recipe_user_meal_plan FOREIGN KEY (meal_plan_id) REFERENCES user_meal_plan(meal_plan_id),
        CONSTRAINT fk_meal_plan_user_recipe_recipes FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id),
        CONSTRAINT pk_meal_plan_user_recipe PRIMARY KEY (meal_plan_id, recipe_id)
);




INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO recipes (preparation, recipe_name, prep_time, cook_time, recipe_type) VALUES ('You will need fresh basil, fresh mozz, and fresh tomato on a plate. Layer the cheese, basil, and tomato. Drizzle balsamic vinegar and Enjoy!', 'Caprese Salad', 10, null, 'Salad');
INSERT INTO recipes (preparation, recipe_name, prep_time, cook_time, recipe_type) VALUES ('Prepare the tortilini, drain. Cook the meat, then add the sauce and cream cheese. Put the tortellini into the backing dish and pour the meat/cheese/sauce mixture on top. Sprinkle the parmesan cheese over top. Bake for 20 min.', 'Cheesey Tortellini Pasta Bake', 15, 20, 'Entree');
INSERT INTO recipes (preparation, recipe_name, prep_time, cook_time, recipe_type) VALUES('Thaw tater tots, add them to greased muffin pan, dividing evenly. Smash them, fill each cup with cooked meat and chopped veggies(you can use onions, mushrooms, and/or peppers, to name a few). Scramble eggs, add seasoning of your choosing, then pour eggs into each cup, evenly. Top with cheese. Bake at 350 degrees for 18-20 minutes.', 'Mini Omelets', 10, 20, 'Breakfast');

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





INSERT INTO user_meal_plan (meal_plan_name) VALUES ('Healthy');
INSERT INTO user_meal_plan (meal_plan_name) VALUES ('Dinner');
--INSERT INTO user_meal_plan (meal_plan_name) VALUES ('Morning Entree');


COMMIT TRANSACTION;
