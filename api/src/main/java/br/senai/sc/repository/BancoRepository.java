package br.senai.sc.repository;

import br.senai.sc.domain.Banco;
import br.senai.sc.repository.customized.BancoRepositoryCustomized;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BancoRepository extends JpaRepository<Banco, UUID>, BancoRepositoryCustomized {
}
