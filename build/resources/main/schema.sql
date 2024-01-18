CREATE TABLE CART (
    id number ,
    customerId number,
    cartDetailId number,
    PRIMARY KEY (id)

);
CREATE TABLE CUSTOMERS (
    id number,
    userId number NOT NULL,
    userName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    orderId number,
    PRIMARY KEY (id)

);
CREATE TABLE CartDetail (
    id number,
    cartId number,
    itemId number,
    quantity number,
    dateAdded number,
    PRIMARY KEY (id)

);
CREATE TABLE GOODS (
    id number,
    orderDetailId number,
    name VARCHAR(255),
    price number,
    PRIMARY KEY (id)
);
CREATE TABLE OrderDetail (
    id number,
    orderId number,
    itemId number,
    quantity number,
    PRIMARY KEY (id)

);
CREATE TABLE Orders (
    id number,
    orderDetailId number,
    orderDate DATE,
    PRIMARY KEY (id)

);
Alter table CART
ADD FOREIGN KEY (cartDetailId) REFERENCES CartDetail(id);

Alter table CART
ADD FOREIGN KEY (customerId) REFERENCES Customers(id);

Alter table CartDetail
ADD FOREIGN KEY (cartId) REFERENCES Cart(id);

Alter table CartDetail
ADD FOREIGN KEY (itemId) REFERENCES Goods(id);

Alter table Customers
ADD FOREIGN KEY (orderId) REFERENCES Orders(id);

Alter table Goods
ADD FOREIGN KEY (orderDetailId) REFERENCES OrderDetail(id);

Alter table OrderDetail
ADD FOREIGN KEY (orderId) REFERENCES Orders(id);

Alter table OrderDetail
ADD FOREIGN KEY (itemId) REFERENCES Goods(id);

Alter table ORDERS
ADD FOREIGN KEY (orderDetailId) REFERENCES Orders(id);
