package br.senai.sc.repository.impl;

import br.senai.sc.domain.Banco;
import br.senai.sc.domain.QBanco;
import br.senai.sc.repository.customized.BancoRepositoryCustomized;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

public class BancoRepositoryImpl implements BancoRepositoryCustomized {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Banco> findByTermoBusca(final String termoBusca) {

        final var banco = QBanco.banco;
        final var where = new BooleanBuilder();

        if (Objects.nonNull(termoBusca) && !termoBusca.isEmpty()) {
            where.and(banco.nome.containsIgnoreCase(termoBusca));
        }

        final var query = new JPAQueryFactory(em)
                .select(banco)
                .from(banco)
                .where(where)
                .orderBy(banco.codigo.desc());

        return query.fetch();
        }
}
