DROP TABLE IF EXISTS product;

CREATE TABLE product (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  product_id VARCHAR(25) NOT NULL,
  title VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  brand VARCHAR(25) NOT NULL,
  price DECIMAL(20, 2) NOT NULL,
  color VARCHAR(25) NOT NULL
);
