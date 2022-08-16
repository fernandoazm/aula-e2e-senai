package br.senai.sc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.senai.sc.model.FornecedorModel;
import br.senai.sc.service.FornecedorService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping("/{id}")
    public FornecedorModel consultar(@PathVariable UUID id) {
        return new FornecedorModel(fornecedorService.findById(id));
    }

    @GetMapping
    public List<FornecedorModel> consultarTodos() {
        return fornecedorService.findAll().stream().map(FornecedorModel::new).toList();
    }

    @PostMapping
    public FornecedorModel cadastrar(@Valid @RequestBody FornecedorModel dto) {
        return new FornecedorModel(fornecedorService.cadastrar(dto));
    }

    @PutMapping("/{id}")
    public FornecedorModel alterar(@PathVariable UUID id, @Valid @RequestBody FornecedorModel dto) {
        return new FornecedorModel(fornecedorService.alterar(id, dto));
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable UUID id) {
        fornecedorService.excluir(id);
    }
}
