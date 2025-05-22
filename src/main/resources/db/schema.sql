DROP TABLE IF EXISTS operaciones;

CREATE TABLE operaciones (
    id UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    num1 NUMERIC(20, 4) NOT NULL,
    num2 NUMERIC(20, 4) NOT NULL,
    porcentaje_aplicado NUMERIC(20, 4) NOT NULL,
    usando_cache BOOLEAN NOT NULL,
    resultado NUMERIC(30, 4) NOT NULL,
    fecha TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
