package br.senai.sc.service;

import br.senai.sc.service.impl.BancoServiceImpl;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest(showSql = false)
@TestPropertySource(properties = "spring.flyway.locations=classpath:/test_db/migration/")
public abstract class BaseTests {

    @TestConfiguration
    static class ConfigTest {

        @Bean
        public BancoService bancoService() {
            return new BancoServiceImpl();
        }
    }
}
