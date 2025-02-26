package com.tinnova.veiculos.interfaces.controller;

import com.tinnova.veiculos.application.service.VeiculoService;
import com.tinnova.veiculos.domain.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }


    @GetMapping
    public ResponseEntity<List<Veiculo>> buscarVeiculos(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) String cor) {

        final List<Veiculo> veiculos;
        if (marca != null || ano != null || cor != null) {
            veiculos = veiculoService.buscarPorFiltros(marca, ano, cor);
        }else{
            veiculos = veiculoService.listarTodosVeiculos();
        }
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> detalhesVeiculo(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.detalhesVeiculo(id);
        return ResponseEntity.ok(veiculo);
    }


    @PostMapping
    public ResponseEntity<Veiculo> cadastrarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo veiculoSalvo = veiculoService.cadastrarVeiculo(veiculo);
        return ResponseEntity.ok(veiculoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        Veiculo veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculo);
        return ResponseEntity.ok(veiculoAtualizado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarAtributos(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Veiculo veiculoAtualizado = veiculoService.atualizarAtributos(id, updates);
        return ResponseEntity.ok(veiculoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
        veiculoService.excluirVeiculo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nao-vendidos")
    public ResponseEntity<Map<String, Long>> contarVeiculosNaoVendidos() {
        Map<String, Long> quantidade = veiculoService.contarVeiculosNaoVendidos();
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/contagem-decadas")
    public ResponseEntity<Map<String, Long>> contarVeiculosPorDecada() {
        Map<String, Long> contagemDecadas = veiculoService.contarVeiculosPorDecada();
        return ResponseEntity.ok(contagemDecadas);
    }

    @GetMapping("/contagem-marcas")
    public ResponseEntity<Map<String, Long>> contarVeiculosPorMarca() {
        Map<String, Long> contagemPorMarca = veiculoService.contarVeiculosPorMarca();
        return ResponseEntity.ok(contagemPorMarca);
    }

    @GetMapping("/ultima-semana")
    public ResponseEntity<List<Veiculo>> listarVeiculosUltimaSemana() {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosNaUltimaSemana();
        return ResponseEntity.ok(veiculos);
    }
}
