package br.senai.sc.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.senai.sc.enums.EnumSituacaoCadastro;
import br.senai.sc.model.FornecedorModel;
import lombok.Getter;

@Getter

@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    private UUID id;

    @NotNull
    @Column(name = "nome_fantasia", length = 200)
    private String nomeFantasia;

    @NotNull
    @Column(name = "razao_social", length = 200)
    private String razaoSocial;

    @NotNull
    @Column(name = "cnpj")
    private String cnpj;

    @NotNull
    @Column(name = "fone")
    private String fone;

    @NotNull
    @Column(name = "email", length = 200)
    private String email;

    @NotNull
    @Convert(converter = EnumSituacaoCadastro.Converter.class)
    @Column(name = "situacao_cadastro", length = 1)
    private EnumSituacaoCadastro situacaoCadastro;

    @NotNull
    @Column(name = "data_cadastro", updatable = false)
    private ZonedDateTime data_cadastro;

    @NotNull
    @Column(name = "data_atualizacao", updatable = false)
    private ZonedDateTime data_atualizacao;

    protected Fornecedor() {
        this.id = UUID.randomUUID();
        this.data_cadastro = this.data_atualizacao = ZonedDateTime.now();
    }

    public Fornecedor(final FornecedorModel model) {
        this();
        this.editar(model);
    }

    public Fornecedor editar(final FornecedorModel dto) {
        this.nomeFantasia = dto.getNomeFantasia().trim();
        this.razaoSocial = dto.getRazaoSocial().trim();
        this.cnpj = dto.getCnpj().trim();
        this.fone = dto.getFone().trim();
        this.email = dto.getEmail().trim();
        this.situacaoCadastro = dto.getSituacaoCadastro();
        return this;
    }
}
