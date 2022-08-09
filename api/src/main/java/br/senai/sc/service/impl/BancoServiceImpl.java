package br.senai.sc.service.impl;

import br.senai.sc.domain.Banco;
import br.senai.sc.exception.RegistroNaoIdentificadoException;
import br.senai.sc.model.BancoModel;
import br.senai.sc.repository.BancoRepository;
import br.senai.sc.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BancoServiceImpl implements BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    @Override
    public Banco findById(final UUID id) {
        return bancoRepository.findById(id).orElseThrow(() -> new RegistroNaoIdentificadoException(id));
    }

    @Override
    public List<Banco> consultar(final String termoBusca) {
        return bancoRepository.findByTermoBusca(termoBusca);
    }

    @Transactional
    @Override
    public Banco cadastrar(final BancoModel dto) {
        return bancoRepository.save(new Banco(dto));
    }

    @Transactional
    @Override
    public Banco alterar(final UUID id, BancoModel dto) {
        return this.findById(id).editar(dto);
    }

    @Transactional
    @Override
    public void excluir(final UUID id) {
        bancoRepository.deleteById(id);
    }
}
