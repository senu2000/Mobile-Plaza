CREATE TABLE t_inventory (
                          id BIGINT(20) AUTO_INCREMENT NOT NULL PRIMARY KEY,
                          sku_code VARCHAR(255) DEFAULT NULL,
                          quantity INT(11) DEFAULT NULL
);