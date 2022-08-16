package br.senai.sc.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import br.senai.sc.domain.Fornecedor;
import br.senai.sc.enums.EnumSituacaoCadastro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class FornecedorModel {

    private UUID id;

    @NotBlank
    @NotNull
    @Length(min = 2, max = 200)
    private String nomeFantasia;

    @NotBlank
    @NotNull
    @Length(min = 2, max = 200)
    private String razaoSocial;

    @NotBlank
    @NotNull
    @Length(min = 14)
    private String cnpj;

    @NotBlank
    @NotNull
    @Length(min = 11)
    private String fone;

    @NotBlank
    @NotNull
    @Length(min = 2, max = 200)
    private String email;

    @NotNull
    private EnumSituacaoCadastro situacaoCadastro;

    public FornecedorModel(final Fornecedor entity) {
        this();
        this.id = entity.getId();
        if (Hibernate.isInitialized(entity)) {
            this.nomeFantasia = entity.getNomeFantasia();
            this.razaoSocial = entity.getRazaoSocial();
            this.cnpj = entity.getCnpj();
            this.fone = entity.getFone();
            this.email = entity.getEmail();
            this.situacaoCadastro = entity.getSituacaoCadastro();
        }
    }
}
