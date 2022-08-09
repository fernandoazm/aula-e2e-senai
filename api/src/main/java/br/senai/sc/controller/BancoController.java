package br.senai.sc.controller;

import br.senai.sc.model.BancoModel;
import br.senai.sc.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/banco")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @GetMapping
    public List<BancoModel> consultar(@RequestParam(value = "termo-busca", required = false) String termoBusca) {
        return bancoService.consultar(termoBusca).stream().map(BancoModel::new).toList();
    }

    @GetMapping("/{id}")
    public BancoModel consultar(@PathVariable UUID id) {
        return new BancoModel(bancoService.findById(id));
    }

    @PostMapping
    public BancoModel cadastrar(@Valid @RequestBody BancoModel dto) {
        return new BancoModel(bancoService.cadastrar(dto));
    }

    @PutMapping("/{id}")
    public BancoModel alterar(@PathVariable UUID id, @Valid @RequestBody BancoModel dto) {
        return new BancoModel(bancoService.alterar(id, dto));
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable UUID id) {
        bancoService.excluir(id);
    }
}
