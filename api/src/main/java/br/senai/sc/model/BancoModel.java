package br.senai.sc.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.senai.sc.domain.Banco;
import br.senai.sc.enums.EnumTipoBanco;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder

@NoArgsConstructor
@Getter
public class BancoModel {
    
    private UUID id;

    @NotNull
    private Integer codigo;

    @NotBlank
    @NotNull
    @Length(min = 2, max = 256)
    private String nome;

    @NotNull
    private EnumTipoBanco tipo;
    
    public BancoModel(final Banco entity) {
        this();
        this.id = entity.getId();
        if (Hibernate.isInitialized(entity)) {
            this.codigo = entity.getCodigo();
            this.nome = entity.getNome();
            this.tipo = entity.getTipo();
        }
    }
}
