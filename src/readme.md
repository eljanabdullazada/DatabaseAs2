Assignment2 Below are the steps to use the source code.

Open terminal or command prompt and type: cd desktop (desktop is optional, you can write the path of the folder you want to clone the repository).
Use the following command to clone the repository: git clone https://github.com/eljanabdullazada/DatabaseAs2.git
Open the cloned folder using code editor Intellij.
Replace testcheck with your database name
Replace elcan with you user name
Replace 12345678 with your password.
private static final String URL = "jdbc:postgresql://localhost:5432/testcheck";
private static final String USER = "elcan";
private static final String PASSWORD = "12345678";
You will see the following menu. You can select any of the numbers based on your choice and use it.

Menu:
1. Add Author
2. Add Book
3. Add Customer
4. Display Books
5. Update Book
6. Delete Book
7. Order Book
8. Display Table Names
9. Display Table Details
10. Exit
    Enter your choice: 
Before doing above actions create tables using following sql queries:
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