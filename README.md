# Order Management System (OMS)

Система управления заказами интернет-магазина с использованием Spring Boot, JPA, PostgreSQL, MapStruct, Flyway и Validation.

## Технологии

- **Java 17**
- **Spring Boot 3.2.1**
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway** (миграции БД)
- **MapStruct** (маппинг DTO ↔ Entity)
- **Bean Validation**
- **Lombok**
- **Maven**

## Структура БД

### Сущности и связи

#### Customer (Покупатель)
- `id` - уникальный идентификатор
- `email` - email (unique, @Email, @NotBlank)
- `name` - имя покупателя
- **Связь**: 1-N с Order

#### Order (Заказ)
- `id` - уникальный идентификатор
- `created_at` - дата создания
- `status` - статус заказа (PENDING, CONFIRMED, PROCESSING, SHIPPED, DELIVERED, CANCELLED)
- `customer_id` → FK на Customer
- **Связи**: 
  - N-1 с Customer
  - 1-N с OrderItem

#### OrderItem (Позиция заказа)
- `id` - уникальный идентификатор
- `quantity` - количество (@Min(1))
- `price` - цена на момент заказа
- `order_id` → FK на Order
- `product_id` → FK на Product
- **Связи**:
  - N-1 с Order
  - N-1 с Product

#### Product (Продукт)
- `id` - уникальный идентификатор
- `name` - название продукта
- `price` - цена продукта
- **Связь**: 1-N с OrderItem

## Миграции Flyway

### V1__create_tables.sql
Создание всех таблиц с Foreign Key и индексами

### V2__add_email_unique_constraint.sql
Добавление уникального ограничения на email в таблице customers

## Запуск проекта

### 1. Запуск PostgreSQL через Docker Compose

```bash
docker-compose up -d
```

### 2. Компиляция проекта

```bash
mvn clean install -DskipTests
```

### 3. Запуск приложения

```bash
mvn spring-boot:run
```

Приложение будет доступно на `http://localhost:8080`

## API Endpoints

### Customers (Покупатели)

```bash
# Создать покупателя
POST /api/customers
Content-Type: application/json
{
  "email": "user@example.com",
  "name": "Иван Иванов"
}

# Получить покупателя по ID
GET /api/customers/{id}

# Получить всех покупателей
GET /api/customers

# Получить покупателя по email
GET /api/customers/by-email?email=user@example.com

# Удалить покупателя
DELETE /api/customers/{id}
```

### Products (Продукты)

```bash
# Создать продукт
POST /api/products
Content-Type: application/json
{
  "name": "Ноутбук Lenovo",
  "price": 45000.00
}

# Получить продукт по ID
GET /api/products/{id}

# Получить все продукты
GET /api/products

# Получить продукт по названию
GET /api/products/by-name?name=Ноутбук Lenovo

# Обновить продукт
PUT /api/products/{id}
Content-Type: application/json
{
  "name": "Ноутбук Lenovo Updated",
  "price": 50000.00
}

# Удалить продукт
DELETE /api/products/{id}
```

### Orders (Заказы)

```bash
# Создать заказ
POST /api/orders
Content-Type: application/json
{
  "customerId": 1,
  "items": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 2,
      "quantity": 1
    }
  ]
}

# Получить заказ по ID
GET /api/orders/{id}

# Получить все заказы
GET /api/orders

# Получить заказы по покупателю
GET /api/orders?customerId=1

# Получить заказы по статусу
GET /api/orders?status=PENDING

# Обновить статус заказа
PATCH /api/orders/{id}/status?status=CONFIRMED

# Удалить заказ
DELETE /api/orders/{id}
```

## Примеры использования

### 1. Создание покупателя

```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "email": "ivan@example.com",
    "name": "Иван Петров"
  }'
```

### 2. Создание продукта

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "iPhone 15 Pro",
    "price": 89990.00
  }'
```

### 3. Создание заказа

```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": 1,
    "items": [
      {
        "productId": 1,
        "quantity": 1
      }
    ]
  }'
```

## Валидация

Все DTO имеют валидацию:
- `@NotBlank` - поле не может быть пустым
- `@Email` - валидация формата email
- `@Min(1)` - минимальное значение
- `@DecimalMin` - минимальное значение для BigDecimal
- `@NotEmpty` - коллекция не может быть пустой
- `@Valid` - валидация вложенных объектов

## MapStruct

Используются маппера для преобразования Entity ↔ DTO:
- `CustomerMapper` - Customer ↔ CustomerDto
- `ProductMapper` - Product ↔ ProductDto
- `OrderMapper` - Order ↔ OrderResponseDto

## Требования выполнены

✅ **JPA**: @ManyToOne, @OneToMany, @JoinColumn  
✅ **PostgreSQL**: база данных  
✅ **Validation**: на всех DTO  
✅ **Flyway**: V1 и V2 миграции  
✅ **MapStruct**: Order → OrderResponseDto, CreateOrderRequest → Order  
✅ **Связи по FK**: Customer 1-N Order, Order 1-N OrderItem, Product 1-N OrderItem  

