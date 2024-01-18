CREATE TABLE CART (
    id int ,
    customerId int,
    cartDetailId int,
    PRIMARY KEY (id),
    CONSTRAINT FK_CustomerId FOREIGN KEY (customerId)
    REFERENCES CUSTOMERS(id),
    CONSTRAINT FK_CartDetailId FOREIGN KEY (cartDetailId)
    REFERENCES CartDetail(id)
);
CREATE TABLE CUSTOMERS (
    id int,
    userId int NOT NULL,
    userName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    orderId int,
    PRIMARY KEY (id),
    CONSTRAINT FK_OrderId FOREIGN KEY (OrderId)
    REFERENCES Orders(id)
);

CREATE TABLE CartDetail (
    id int,
    cartId int,
    itemId int,
    quantity int,
    dateAdded int,
    PRIMARY KEY (id),
    CONSTRAINT FK_CartId FOREIGN KEY (cartId)
    REFERENCES Cart(id),
    CONSTRAINT FK_ItemId FOREIGN KEY (itemId)
    REFERENCES Items(id)
);
CREATE TABLE GOODS (
    id int,
    orderDetailId int,
    name VARCHAR(255),
    price double,
    PRIMARY KEY (id),
    CONSTRAINT FK_OrderDetailId FOREIGN KEY (orderDetailId)
    REFERENCES OrderDetail(id)
);
CREATE TABLE OrderDetail (
    id int,
    orderId int,
    itemId int,
    quantity int,
    PRIMARY KEY (id),
    CONSTRAINT FK_OrderId FOREIGN KEY (orderId)
    REFERENCES Orders(id),
    CONSTRAINT FK_ItemId FOREIGN KEY (itemId)
    REFERENCES Items(id)
);
CREATE TABLE Orders (
    id int,
    orderDetailId int,
    orderDate DATE,
    PRIMARY KEY (id),
    CONSTRAINT FK_OrderDetailId FOREIGN KEY (orderDetailId)
    REFERENCES OrderDetail(id)
);