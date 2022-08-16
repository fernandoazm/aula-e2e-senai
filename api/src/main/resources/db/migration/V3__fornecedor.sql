CREATE TABLE IF NOT EXISTS fornecedor (
    id UUID NOT NULL,
    nome_fantasia VARCHAR(200) NOT NULL,
    razao_social VARCHAR(200) NOT NULL,
    cnpj VARCHAR(18) NOT NULL,
    fone VARCHAR(16) NOT NULL,
    email VARCHAR(200) NOT NULL,
    situacao_cadastro VARCHAR(1) NOT NULL,
    data_cadastro TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    data_atualizacao TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),    
    CONSTRAINT fornecedor_pkey PRIMARY KEY (id)
    );
------------------------------------------------------------------------------------------------------------------------
DO $$
BEGIN
    DROP TRIGGER IF EXISTS fornecedor_data_atualizacao_trigger ON fornecedor;
    CREATE TRIGGER fornecedor_data_atualizacao_trigger
        BEFORE UPDATE
        ON fornecedor
        FOR EACH ROW
        EXECUTE PROCEDURE data_atualizacao_trigger_function();
END$$;
------------------------------------------------------------------------------------------------------------------------