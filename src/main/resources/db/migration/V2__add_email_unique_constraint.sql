-- Добавление уникального ограничения на email в таблице customers
ALTER TABLE customers ADD CONSTRAINT uk_customers_email UNIQUE (email);