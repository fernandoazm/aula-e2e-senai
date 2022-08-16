package br.senai.sc.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senai.sc.domain.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {
}
