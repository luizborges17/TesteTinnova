package com.tinnova.veiculos.interfaces.controller;

import com.tinnova.veiculos.application.service.VeiculoService;
import com.tinnova.veiculos.domain.enums.Marca;
import com.tinnova.veiculos.domain.model.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class VeiculoControllerTest {

    @Mock
    private VeiculoService veiculoService;

    @InjectMocks
    private VeiculoController veiculoController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(veiculoController).build();
    }

    @Test
    public void testBuscarVeiculos() throws Exception {
        Veiculo veiculo1 = new Veiculo(1L,"Civic", Marca.HONDA, 2020, "Carro novo top", false, "Preto");
        Veiculo veiculo2 = new Veiculo(2L,"Civic", Marca.HONDA, 2019, "Carro novo top", true, "Preto");
        List<Veiculo> veiculos = Arrays.asList(veiculo1, veiculo2);

        when(veiculoService.listarTodosVeiculos()).thenReturn(veiculos);

        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].marca").value("HONDA"))
                .andExpect(jsonPath("$[1].marca").value("HONDA"));

        verify(veiculoService, times(1)).listarTodosVeiculos();
    }

    @Test
    public void testBuscarVeiculosComFiltroMarca() throws Exception {
        Veiculo veiculo1 = new Veiculo(1L,"Civic", Marca.HONDA, 2020, "Carro novo top", false, "Cinza");
        List<Veiculo> veiculos = Arrays.asList(veiculo1);

        when(veiculoService.buscarPorFiltros("HONDA", null, null)).thenReturn(veiculos);

        mockMvc.perform(get("/veiculos")
                        .param("marca", "HONDA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].marca").value("HONDA"));

        verify(veiculoService, times(1)).buscarPorFiltros("HONDA", null, null);
    }

    @Test
    public void testBuscarVeiculosComFiltroAno() throws Exception {
        Veiculo veiculo1 = new Veiculo(1L,"Civic", Marca.HONDA, 2020, "Carro novo top", false, "Cinza");
        List<Veiculo> veiculos = Arrays.asList(veiculo1);

        when(veiculoService.buscarPorFiltros(null, 2020, null)).thenReturn(veiculos);

        mockMvc.perform(get("/veiculos")
                        .param("ano", "2020"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ano").value(2020));

        verify(veiculoService, times(1)).buscarPorFiltros(null, 2020, null);
    }

    @Test
    public void testBuscarVeiculosComFiltroCor() throws Exception {
        Veiculo veiculo1 = new Veiculo(1L,"Civic", Marca.HONDA, 2020, "Carro novo top", false, "Cinza");
        List<Veiculo> veiculos = Arrays.asList(veiculo1);

        when(veiculoService.buscarPorFiltros(null, null, "Cinza")).thenReturn(veiculos);

        mockMvc.perform(get("/veiculos")
                        .param("cor", "Cinza"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cor").value("Cinza"));

        verify(veiculoService, times(1)).buscarPorFiltros(null, null, "Cinza");
    }

    @Test
    public void testDetalhesVeiculo() throws Exception {
        Veiculo veiculo = new Veiculo(1L,"Civic", Marca.HONDA, 2020, "Carro novo top", false, "Preto");

        when(veiculoService.detalhesVeiculo(1L)).thenReturn(veiculo);

        mockMvc.perform(get("/veiculos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("HONDA"));

        verify(veiculoService, times(1)).detalhesVeiculo(1L);
    }

    @Test
    public void testCadastrarVeiculo() throws Exception {
        Veiculo veiculo = new Veiculo(1L,"Civic", Marca.HONDA, 2020, "Carro novo top", false, "Preto");

        when(veiculoService.cadastrarVeiculo(any(Veiculo.class))).thenReturn(veiculo);

        mockMvc.perform(post("/veiculos")
                        .contentType("application/json")
                        .content("{\"marca\": \"HONDA\", \"ano\": 2020, \"cor\": \"Cinza\", \"vendido\": false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("HONDA"));

        verify(veiculoService, times(1)).cadastrarVeiculo(any(Veiculo.class));
    }

    @Test
    public void testAtualizarVeiculo() throws Exception {
        Veiculo veiculo = new Veiculo(1L,"Civic", Marca.HONDA, 2020, "Carro novo top", false, "Preto");

        when(veiculoService.atualizarVeiculo(eq(1L), any(Veiculo.class))).thenReturn(veiculo);

        mockMvc.perform(put("/veiculos/1")
                        .contentType("application/json")
                        .content("{\"marca\": \"HONDA\", \"ano\": 2020, \"cor\": \"Cinza\", \"vendido\": false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("HONDA"));

        verify(veiculoService, times(1)).atualizarVeiculo(eq(1L), any(Veiculo.class));
    }

    @Test
    public void testAtualizarAtributos() throws Exception {

        Veiculo veiculoExistente = new Veiculo(1L,"Civic", Marca.HONDA, 2020, "Carro novo top", false, "Cinza");

        Map<String, Object> updates = new HashMap<>();
        updates.put("marca", "Toyota");
        updates.put("ano", 2021);

        Veiculo veiculoAtualizado = new Veiculo(1L,"Toyota", Marca.TOYOTA, 2021, "Carro novo top", false, "Cinza");
        when(veiculoService.atualizarAtributos(1L, updates)).thenReturn(veiculoAtualizado);

        mockMvc.perform(patch("/veiculos/{id}", 1L)
                        .contentType("application/json")
                        .content("{\"marca\":\"Toyota\", \"ano\":2021}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("TOYOTA"))
                .andExpect(jsonPath("$.ano").value(2021));

        verify(veiculoService, times(1)).atualizarAtributos(1L, updates);
    }

    @Test
    public void testExcluirVeiculo() throws Exception {
        doNothing().when(veiculoService).excluirVeiculo(1L);

        mockMvc.perform(delete("/veiculos/1"))
                .andExpect(status().isNoContent());

        verify(veiculoService, times(1)).excluirVeiculo(1L);
    }

    @Test
    public void testContarVeiculosNaoVendidos() throws Exception {
        Map<String, Long> quantidade = new HashMap<>();
        quantidade.put("nao_vendidos", 5L);

        when(veiculoService.contarVeiculosNaoVendidos()).thenReturn(quantidade);

        mockMvc.perform(get("/veiculos/nao-vendidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nao_vendidos").value(5));

        verify(veiculoService, times(1)).contarVeiculosNaoVendidos();
    }

    @Test
    public void testContarVeiculosPorDecada() throws Exception {
        Map<String, Long> contagem = new HashMap<>();
        contagem.put("decada_1990", 2L);

        when(veiculoService.contarVeiculosPorDecada()).thenReturn(contagem);

        mockMvc.perform(get("/veiculos/contagem-decadas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.decada_1990").value(2));

        verify(veiculoService, times(1)).contarVeiculosPorDecada();
    }

    @Test
    public void testContarVeiculosPorMarca() throws Exception {
        Map<String, Long> contagem = new HashMap<>();
        contagem.put("Honda", 3L);

        when(veiculoService.contarVeiculosPorMarca()).thenReturn(contagem);

        mockMvc.perform(get("/veiculos/contagem-marcas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Honda").value(3));

        verify(veiculoService, times(1)).contarVeiculosPorMarca();
    }

    @Test
    public void testListarVeiculosUltimaSemana() throws Exception {
        Veiculo veiculo = new Veiculo(1L,"Civic", Marca.HONDA, 2020, "Carro novo top", false, "Preto");
        List<Veiculo> veiculos = Arrays.asList(veiculo);

        when(veiculoService.buscarVeiculosNaUltimaSemana()).thenReturn(veiculos);

        mockMvc.perform(get("/veiculos/ultima-semana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].marca").value("HONDA"));

        verify(veiculoService, times(1)).buscarVeiculosNaUltimaSemana();
    }
}
