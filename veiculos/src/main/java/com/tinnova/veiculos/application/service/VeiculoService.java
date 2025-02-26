package com.tinnova.veiculos.application.service;

import com.tinnova.veiculos.domain.enums.Marca;
import com.tinnova.veiculos.domain.model.Veiculo;
import com.tinnova.veiculos.infrastructure.exception.VeiculoNotFoundException;
import com.tinnova.veiculos.infrastructure.persistence.VeiculoRepositoryImpl;
import com.tinnova.veiculos.infrastructure.persistence.specification.VeiculoSpecification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class VeiculoService {

    private final VeiculoRepositoryImpl veiculoRepository;

    public VeiculoService(VeiculoRepositoryImpl veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo cadastrarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarTodosVeiculos(){
        return veiculoRepository.findAll();
    }

    public Veiculo detalhesVeiculo(Long id) {
        Optional<Veiculo> veiculoExistente = veiculoRepository.findById(id);
        if (veiculoExistente.isPresent()) {
            return veiculoExistente.get();
        }
        throw new VeiculoNotFoundException();
    }

    public List<Veiculo> buscarPorFiltros(String marcaStr, Integer ano, String cor) {
        Marca marca = null;

        if (marcaStr != null && !marcaStr.isEmpty()) {
            marca = Marca.fromString(marcaStr);
        }

        return veiculoRepository.findAll(VeiculoSpecification.filtrar(marca, ano, cor));
    }


    public Veiculo atualizarVeiculo(Long id, Veiculo veiculo) {
        Optional<Veiculo> veiculoExistente = veiculoRepository.findById(id);
        if (veiculoExistente.isPresent()) {
            Veiculo veiculoAtualizado = veiculoExistente.get();
            veiculoAtualizado.setVeiculo(veiculo.getVeiculo());
            veiculoAtualizado.setMarca(veiculo.getMarca());
            veiculoAtualizado.setAno(veiculo.getAno());
            veiculoAtualizado.setDescricao(veiculo.getDescricao());
            veiculoAtualizado.setVendido(veiculo.getVendido());
            veiculoAtualizado.setCor(veiculo.getCor());
            veiculoAtualizado.setUpdated(LocalDateTime.now());
            veiculoRepository.save(veiculoAtualizado);
            return veiculoAtualizado;
        }
        throw new VeiculoNotFoundException();
    }

    public Veiculo atualizarAtributos(Long id, Map<String, Object> updates) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(VeiculoNotFoundException::new);

        updates.forEach((campo, valor) -> {
            switch (campo) {
                case "veiculo":
                    veiculo.setVeiculo((String) valor);
                    break;
                case "marca":
                    veiculo.setMarca(Marca.fromString(valor.toString()));
                    break;
                case "ano":
                    veiculo.setAno((Integer) valor);
                    break;
                case "descricao":
                    veiculo.setDescricao((String) valor);
                    break;
                case "vendido":
                    veiculo.setVendido((Boolean) valor);
                    break;
                case "cor":
                    veiculo.setCor((String) valor);
                    break;
            }
        });

        veiculo.setUpdated(LocalDateTime.now());
        return veiculoRepository.save(veiculo);
    }
    public void excluirVeiculo(Long id) {
        Optional<Veiculo> veiculoExistente = veiculoRepository.findById(id);
        if(veiculoExistente.isPresent()) {
            veiculoRepository.delete(veiculoExistente.get());
        }else {
            throw new VeiculoNotFoundException();
        }
    }
    public Map<String, Long> contarVeiculosNaoVendidos(){
        Map<String, Long> map = new HashMap<>();
        map.put("count",veiculoRepository.countByVendidoFalse());
        return map;
    }

    public Map<String, Long> contarVeiculosPorDecada() {
        List<Object[]> resultados = veiculoRepository.countByDecadaAno();
        Map<String, Long> decadas = new HashMap<>();

        for (Object[] resultado : resultados) {
            String decada = resultado[0].toString();
            Long count = (Long) resultado[1];
            decadas.put(decada, count);
        }

        return decadas;
    }

    public Map<String, Long> contarVeiculosPorMarca() {
        List<Object[]> resultados = veiculoRepository.countByMarca();
        Map<String, Long> contagemPorMarca = new LinkedHashMap<>();

        for (Object[] resultado : resultados) {
            Marca marca = (Marca) resultado[0];
            Long quantidade = (Long) resultado[1];
            contagemPorMarca.put(marca.name(), quantidade);
        }

        return contagemPorMarca;
    }

    public List<Veiculo> buscarVeiculosNaUltimaSemana() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        return veiculoRepository.findVeiculosNaUltimaSemana(oneWeekAgo);
    }


}
