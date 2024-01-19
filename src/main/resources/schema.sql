CREATE TABLE CARTS (
    id number,
    customer_id number,
    PRIMARY KEY (id)
);

CREATE TABLE CUSTOMERS (
    id number,
    userName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    type varchar(50),
    PRIMARY KEY (id)
);

CREATE TABLE CART_DETAILS (
    id number,
    cart_id number,
    item_id number,
    quantity number,
    date_added number,
    PRIMARY KEY (id)
);

CREATE TABLE ITEMS (
    id number,
    name VARCHAR(255),
    price number,
    PRIMARY KEY (id)
);

CREATE TABLE ORDER_DETAILS (
    id number,
    order_id number,
    item_id number,
    quantity number,
    PRIMARY KEY (id)
);

CREATE TABLE Orders (
    id number,
    customer_id number,
    order_date DATE,
    is_complete number(1),
    PRIMARY KEY (id)
);

ALTER TABLE Orders
    ADD CONSTRAINT FK_CustomerOrder
    FOREIGN KEY (customer_id) REFERENCES CUSTOMERS(id);
ALTER TABLE CART_DETAILS
    ADD CONSTRAINT FK_CartId
    FOREIGN KEY (cart_id) REFERENCES CARTS(id);
ALTER TABLE CART_DETAILS
    ADD CONSTRAINT FK_ItemId
    FOREIGN KEY (item_id) REFERENCES ITEMS(id);
ALTER TABLE Carts
    ADD CONSTRAINT FK_CustomerId
    FOREIGN KEY (customer_id) REFERENCES CUSTOMERS(id);
ALTER TABLE ORDER_DETAILS
    ADD CONSTRAINT FK_ItemsId
    FOREIGN KEY (item_id) REFERENCES ITEMS(id);
ALTER TABLE ORDER_DETAILS
    ADD CONSTRAINT FK_OrderId
    FOREIGN KEY (order_id) REFERENCES Orders(id);