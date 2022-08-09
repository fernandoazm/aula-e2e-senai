------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION data_atualizacao_trigger_function() RETURNS trigger AS $BODY$
BEGIN
    NEW.data_atualizacao := 'now()';
    RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;
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
DO $$
BEGIN
    DROP TRIGGER IF EXISTS tabela_data_atualizacao_trigger ON banco;
    CREATE TRIGGER banco_data_atualizacao_trigger
        BEFORE UPDATE
        ON banco
        FOR EACH ROW
        EXECUTE PROCEDURE data_atualizacao_trigger_function();
END$$;
------------------------------------------------------------------------------------------------------------------------