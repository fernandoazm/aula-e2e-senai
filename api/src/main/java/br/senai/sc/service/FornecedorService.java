package br.senai.sc.service;

import java.util.List;
import java.util.UUID;

import br.senai.sc.domain.Fornecedor;
import br.senai.sc.model.FornecedorModel;

public interface FornecedorService {

    Fornecedor findById(UUID id);
    List<Fornecedor> findAll();
    Fornecedor cadastrar(FornecedorModel dto);
    Fornecedor alterar(UUID id, FornecedorModel dto);
    void excluir(UUID id);
}
