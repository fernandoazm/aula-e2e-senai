------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS banco (
    id UUID NOT NULL,
    codigo INTEGER NOT NULL,
    nome VARCHAR(256) NOT NULL,
    tipo INTEGER NOT NULL,
    data_cadastro TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    data_atualizacao TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    CONSTRAINT banco_pkey PRIMARY KEY (id)
);
------------------------------------------------------------------------------------------------------------------------
