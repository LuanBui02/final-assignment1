CREATE TABLE carts (
       id number,
       customer_id number,
       PRIMARY KEY (id)
);
CREATE TABLE customers (
    id number,
    username varchar(255) not null,
    password varchar(255) not null,
    type varchar(50),
    PRIMARY KEY (id)
);

CREATE TABLE cart_details (
    id number,
    cart_id number,
    item_id number,
    quantity number,
    date_added date,
    PRIMARY KEY (id)
);

CREATE TABLE items (
    id number,
    name varchar(255),
    price number,
    PRIMARY KEY (id)
);

CREATE TABLE order_details (
    id number,
    order_id number,
    item_id number,
    quantity number,
    PRIMARY KEY (id)
);

CREATE TABLE orders (
    id number,
    customer_id number,
    order_date date,
    is_complete number(1),
    PRIMARY KEY (id)
);

ALTER TABLE orders
    ADD CONSTRAINT FK_CustomerOrder
    FOREIGN KEY (customer_id) REFERENCES customers(id);
ALTER TABLE cart_details
    ADD CONSTRAINT FK_CartId
    FOREIGN KEY (cart_id) REFERENCES carts(id);
ALTER TABLE cart_details
    ADD CONSTRAINT FK_ItemId
    FOREIGN KEY (item_id) REFERENCES items(id);
ALTER TABLE carts
    ADD CONSTRAINT FK_CustomerId
    FOREIGN KEY (customer_id) REFERENCES customers(id);
ALTER TABLE order_details
    ADD CONSTRAINT FK_ItemsId
    FOREIGN KEY (item_id) REFERENCES items(id);
ALTER TABLE order_details
    ADD CONSTRAINT FK_OrderId
    FOREIGN KEY (order_id) REFERENCES orders(id);
