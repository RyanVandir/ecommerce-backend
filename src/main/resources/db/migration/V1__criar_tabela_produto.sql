CREATE TABLE tb_products (
                             id UUID PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             description TEXT,
                             sku VARCHAR(100) UNIQUE NOT NULL,
                             price NUMERIC(19, 2) NOT NULL,
                             active BOOLEAN NOT NULL DEFAULT TRUE,
                             created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);