package br.senai.sc.enums;

import br.senai.sc.enums.converter.AbstractEnumConverter;
import br.senai.sc.enums.converter.PersistableEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumTipoBanco implements PersistableEnum<Integer> {

    COMERCIAL(1),
    INVESTIMENTO(2),
    DESENVOLVIMENTO(3),
    MULTIPLO(4);

    private final Integer value;

    public static class Converter extends AbstractEnumConverter<EnumTipoBanco, Integer> {
        public Converter() {
            super(EnumTipoBanco.class);
        }
    }
}
