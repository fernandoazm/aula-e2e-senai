package br.senai.sc.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sc.domain.Fornecedor;
import br.senai.sc.exception.RegistroNaoIdentificadoException;
import br.senai.sc.model.FornecedorModel;
import br.senai.sc.repository.FornecedorRepository;
import br.senai.sc.service.FornecedorService;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public Fornecedor findById(final UUID id) {
        return fornecedorRepository.findById(id).orElseThrow(() -> new RegistroNaoIdentificadoException(id));
    }

    @Override
    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll();
    }

    @Transactional
    @Override
    public Fornecedor cadastrar(final FornecedorModel dto) {
    	return fornecedorRepository.save(new Fornecedor(dto));
    }

    @Transactional
    @Override
    public Fornecedor alterar(final UUID id, FornecedorModel dto) {
    	return fornecedorRepository.save(this.findById(id).editar(dto));
    }

    @Transactional
    @Override
    public void excluir(final UUID id) {
        fornecedorRepository.deleteById(id);
    }
}
