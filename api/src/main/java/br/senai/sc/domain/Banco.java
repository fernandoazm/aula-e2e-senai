package br.senai.sc.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.senai.sc.enums.EnumTipoBanco;
import br.senai.sc.model.BancoModel;
import lombok.Getter;

@Getter

@Entity
@Table(name = "banco")
public class Banco {

    @Id
    private UUID id;

    @NotNull
    @Column(name = "codigo")
    private Integer codigo;

    @NotNull
    @Column(name = "nome", length = 256)
    private String nome;

    @NotNull
    @Convert(converter = EnumTipoBanco.Converter.class)
    @Column(name = "tipo")
    private EnumTipoBanco tipo;

    @NotNull
    @Column(name = "data_cadastro", updatable = false)
    private ZonedDateTime data_cadastro;

    @NotNull
    @Column(name = "data_atualizacao", updatable = false)
    private ZonedDateTime data_atualizacao;

    protected Banco() {
        this.id = UUID.randomUUID();
        this.data_cadastro = this.data_atualizacao = ZonedDateTime.now();
    }

    public Banco(final BancoModel model) {
        this();
        this.editar(model);
    }

    public Banco editar(final BancoModel model) {
        this.codigo = model.getCodigo();
        this.nome = model.getNome().trim();
        this.tipo = model.getTipo();
        return this;
    }
}
