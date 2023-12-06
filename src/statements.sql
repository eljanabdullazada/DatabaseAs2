CREATE TABLE Authors (
    author_id INT PRIMARY KEY,
    author_name VARCHAR(255),
    birth_date DATE,
    country VARCHAR(100)
);

CREATE TABLE Books (
    book_id INT PRIMARY KEY,
    title VARCHAR(255),
    author_id INT,
    genre VARCHAR(100),
    price DECIMAL(10, 2),
    stock_quantity INT,
    FOREIGN KEY (author_id) REFERENCES Authors(author_id)
);

CREATE TABLE Customers (
    customer_id INT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(255),
    phone VARCHAR(20)
);

CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    order_date DATE,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);


            +--------------+          +--------------+
            |    Books     |          |    Authors   |
            +--------------+          +--------------+
            | book_id (PK) |          | author_id (PK)|
            | title        |          | author_name   |
            | author_id (FK)|.------->| birth_date    |
            | genre        |          | country       |
            | price        |          +--------------+
            | stock_quantity|
            +--------------+

                  |                |
                  |                |
                  |                |
                  |                |
                  |                |
                  V                V

      +-----------------+           +----------------+
      |    Orders       |          |   Customers    |
      +-----------------+           +----------------+
      | order_id (PK)   |----------| customer_id (PK)|
      | customer_id (FK)|          | first_name     |
      | order_date      |          | last_name      |
      | total_amount    |          | email          |
      +-----------------+          | phone          |
                                   +----------------+
