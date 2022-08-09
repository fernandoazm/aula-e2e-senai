package br.senai.sc.service;

import br.senai.sc.enums.EnumTipoBanco;
import br.senai.sc.model.BancoModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(BaseTests.ConfigTest.class)
class BancoServiceImplTests extends BaseTests {

    @Autowired
    private BancoService bancoService;

    @Test
    @Sql({"classpath:test_db/sql/banco_service/banco.sql"})
    void testFindById() {
        final var id = UUID.fromString("0d6f2d22-5f38-4da7-a473-55b763c607f7");
        var banco = bancoService.findById(id);

        assertThat(banco).isNotNull();
        assertThat(banco.getId()).isNotNull();
        assertThat(banco.getId()).isInstanceOf(UUID.class);
        assertThat(banco.getCodigo()).isEqualTo(1);
        assertThat(banco.getNome()).isEqualTo("Banco do Brasil");
        assertThat(banco.getTipo()).isEqualTo(EnumTipoBanco.MULTIPLO);
        assertThat(banco.getData_cadastro()).isInstanceOf(ZonedDateTime.class);
        assertThat(banco.getData_atualizacao()).isInstanceOf(ZonedDateTime.class);

        final var idNotExist = UUID.randomUUID();
        var exception = assertThrows(Exception.class, () -> bancoService.findById(idNotExist));
        assertThat(exception.getMessage()).isEqualTo("Registro %s não identificado".formatted(idNotExist));
    }

    @Test
    void testConsultar() {
        var bancos = bancoService.consultar("");
        assertThat(bancos).hasSize(8);
    }

    @Test
    void testCadastrar() {
        final var model = BancoModel.builder()
                .codigo(1)
                .nome("Banco")
                .tipo(EnumTipoBanco.DESENVOLVIMENTO)
                .build();

        var banco = bancoService.cadastrar(model);

        assertThat(banco).isNotNull();
        assertThat(banco.getId()).isNotNull();
        assertThat(banco.getId()).isInstanceOf(UUID.class);
        assertThat(banco.getCodigo()).isEqualTo(1);
        assertThat(banco.getNome()).isEqualTo("Banco");
        assertThat(banco.getTipo()).isEqualTo(EnumTipoBanco.DESENVOLVIMENTO);
    }

    @Test
    @Sql({"classpath:test_db/sql/banco_service/banco.sql"})
    void testAlterar() {
        final var id = UUID.fromString("0d6f2d22-5f38-4da7-a473-55b763c607f7");
        final var model = BancoModel.builder()
                .codigo(123456)
                .nome("Banco teste X")
                .tipo(EnumTipoBanco.INVESTIMENTO)
                .build();
        var banco = bancoService.alterar(id, model);
        assertThat(banco).isNotNull();
        assertThat(banco.getId()).isEqualTo(id);
        assertThat(banco.getCodigo()).isEqualTo(123456);
        assertThat(banco.getNome()).isEqualTo("Banco teste X");
        assertThat(banco.getTipo()).isEqualTo(EnumTipoBanco.INVESTIMENTO);
    }

    @Test
    @Sql({"classpath:test_db/sql/banco_service/banco.sql"})
    void testExcluir() {

        var bancos = bancoService.consultar("");
        assertThat(bancos).hasSize(9);

        bancoService.excluir(UUID.fromString("0d6f2d22-5f38-4da7-a473-55b763c607f7"));

        bancos = bancoService.consultar("");
        assertThat(bancos).hasSize(8);
    }
}
