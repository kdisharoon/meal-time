-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER mealtime_owner
WITH PASSWORD 'techelevator1';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO mealtime_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO mealtime_owner;

CREATE USER mealtime_appuser
WITH PASSWORD 'techelevator1';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO mealtime_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO mealtime_appuser;
