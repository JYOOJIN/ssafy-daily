show databases;

CREATE TABLE Book (
    isbn VARCHAR(255) PRIMARY KEY,
    author VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    description VARCHAR(200),
    img VARCHAR(255)
);