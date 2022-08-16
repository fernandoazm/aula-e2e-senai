package br.senai.sc.enums;

import br.senai.sc.enums.converter.AbstractEnumConverter;
import br.senai.sc.enums.converter.PersistableEnum;
import lombok.Getter;

@Getter
public enum EnumSituacaoCadastro implements PersistableEnum<String> {

    ATIVO("A"),
    INATIVO("I");

    private final String value;

    EnumSituacaoCadastro(final String value) {
        this.value = value;
    }

    public static class Converter extends AbstractEnumConverter<EnumSituacaoCadastro, String> {
        public Converter() {
            super(EnumSituacaoCadastro.class);
        }
    }
}