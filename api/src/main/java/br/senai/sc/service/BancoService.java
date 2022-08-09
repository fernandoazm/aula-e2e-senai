package br.senai.sc.service;

import br.senai.sc.domain.Banco;
import br.senai.sc.model.BancoModel;

import java.util.List;
import java.util.UUID;

public interface BancoService {

    Banco findById(UUID id);
    List<Banco> consultar(String termoBusca);
    Banco cadastrar(BancoModel dto);
    Banco alterar(UUID id, BancoModel dto);
    void excluir(UUID id);
}
