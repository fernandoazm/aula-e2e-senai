package br.senai.sc.repository.customized;

import br.senai.sc.domain.Banco;

import java.util.List;

public interface BancoRepositoryCustomized {
    List<Banco> findByTermoBusca(String termoBusca);
}
