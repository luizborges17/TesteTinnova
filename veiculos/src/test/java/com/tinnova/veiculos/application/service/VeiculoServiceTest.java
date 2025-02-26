package com.tinnova.veiculos.application.service;

import com.tinnova.veiculos.domain.enums.Marca;
import com.tinnova.veiculos.domain.model.Veiculo;
import com.tinnova.veiculos.infrastructure.exception.VeiculoNotFoundException;
import com.tinnova.veiculos.infrastructure.persistence.VeiculoRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VeiculoServiceTest {

    @Mock
    private VeiculoRepositoryImpl veiculoRepository;

    @InjectMocks
    private VeiculoService veiculoService;

    private Veiculo veiculo;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo(1L,"Civic", Marca.HONDA, 2020, "Carro novo top", false, "Cinza");
    }

    @Test
    void testCadastrarVeiculo() {
        when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

        Veiculo veiculoSalvo = veiculoService.cadastrarVeiculo(veiculo);

        assertNotNull(veiculoSalvo);
        assertEquals(Marca.HONDA, veiculoSalvo.getMarca());
        verify(veiculoRepository, times(1)).save(veiculo);
    }

    @Test
    void testListarTodosVeiculos() {
        List<Veiculo> veiculos = Arrays.asList(veiculo);
        when(veiculoRepository.findAll()).thenReturn(veiculos);

        List<Veiculo> resultado = veiculoService.listarTodosVeiculos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(veiculoRepository, times(1)).findAll();
    }

    @Test
    void testDetalhesVeiculoEncontrado() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        Veiculo veiculoEncontrado = veiculoService.detalhesVeiculo(1L);

        assertNotNull(veiculoEncontrado);
        assertEquals(1L, veiculoEncontrado.getId());
        verify(veiculoRepository, times(1)).findById(1L);
    }

    @Test
    void testDetalhesVeiculoNaoEncontrado() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(VeiculoNotFoundException.class, () -> veiculoService.detalhesVeiculo(1L));
        verify(veiculoRepository, times(1)).findById(1L);
    }
    
    @Test
    void testAtualizarVeiculo() {
        Veiculo veiculoExistente = new Veiculo(1L, "Ford", Marca.FORD, 2020, "Carro usado", true, "Branco");
        Veiculo veiculoAtualizado = new Veiculo(1L, "Toyota", Marca.TOYOTA, 2021, "Carro novo top", false, "Preto");

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculoExistente));
        when(veiculoRepository.save(veiculoExistente)).thenReturn(veiculoExistente);

        Veiculo resultado = veiculoService.atualizarVeiculo(1L, veiculoAtualizado);

        assertNotNull(resultado);
        assertEquals(Marca.TOYOTA, resultado.getMarca());
        assertEquals(2021, resultado.getAno());
        verify(veiculoRepository, times(1)).findById(1L);
        verify(veiculoRepository, times(1)).save(veiculoExistente);
    }

    @Test
    void testAtualizarVeiculoVeiculoNaoEncontrado() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        Veiculo veiculoAtualizado = new Veiculo(1L, "Toyota", Marca.TOYOTA, 2021, "Carro novo top", false, "Preto");

        assertThrows(VeiculoNotFoundException.class, () -> veiculoService.atualizarVeiculo(1L, veiculoAtualizado));

        verify(veiculoRepository, never()).save(any(Veiculo.class));
    }

    @Test
    void testAtualizarVeiculoNaoEncontrado() {
        Veiculo veiculoAtualizado = new Veiculo(1L,"Toyota", Marca.TOYOTA, 2021, "Carro novo top", false, "Preto");
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(VeiculoNotFoundException.class, () -> veiculoService.atualizarVeiculo(1L, veiculoAtualizado));
        verify(veiculoRepository, times(1)).findById(1L);
    }

    @Test
    void testAtualizarAtributos() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("marca", "Toyota");
        updates.put("ano", 2021);
        updates.put("veiculo", "Supra");
        updates.put("descricao", "Carro top");
        updates.put("vendido", false);
        updates.put("cor", "Preto");
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

        Veiculo veiculoAtualizado = veiculoService.atualizarAtributos(1L, updates);

        assertNotNull(veiculoAtualizado);
        assertEquals(Marca.TOYOTA, veiculoAtualizado.getMarca());
        assertEquals(2021, veiculoAtualizado.getAno());
        verify(veiculoRepository, times(1)).findById(1L);
        verify(veiculoRepository, times(1)).save(veiculo);
    }

    @Test
    void testAtualizarAtributosVeiculoNaoEncontrado() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("marca", "Toyota");
        updates.put("ano", 2021);
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(VeiculoNotFoundException.class, () -> veiculoService.atualizarAtributos(1L, updates));
        verify(veiculoRepository, times(1)).findById(1L);
    }

    @Test
    void testExcluirVeiculo() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        doNothing().when(veiculoRepository).delete(veiculo);

        veiculoService.excluirVeiculo(1L);

        verify(veiculoRepository, times(1)).findById(1L);
        verify(veiculoRepository, times(1)).delete(veiculo);
    }

    @Test
    void testExcluirVeiculoNaoEncontrado() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(VeiculoNotFoundException.class, () -> veiculoService.excluirVeiculo(1L));
        verify(veiculoRepository, times(1)).findById(1L);
    }

    @Test
    void testContarVeiculosNaoVendidos() {
        when(veiculoRepository.countByVendidoFalse()).thenReturn(10L);

        Map<String, Long> resultado = veiculoService.contarVeiculosNaoVendidos();

        assertNotNull(resultado);
        assertEquals(10L, resultado.get("count"));
        verify(veiculoRepository, times(1)).countByVendidoFalse();
    }

    @Test
    void testContarVeiculosPorDecada() {
        List<Object[]> decadas = Arrays.asList(
                new Object[]{"1990-1999", 5L},
                new Object[]{"2000-2009", 3L}
        );
        when(veiculoRepository.countByDecadaAno()).thenReturn(decadas);

        Map<String, Long> resultado = veiculoService.contarVeiculosPorDecada();

        assertNotNull(resultado);
        assertEquals(5L, resultado.get("1990-1999"));
        assertEquals(3L, resultado.get("2000-2009"));
        verify(veiculoRepository, times(1)).countByDecadaAno();
    }

    @Test
    void testContarVeiculosPorMarca() {
        List<Object[]> resultados = new ArrayList<>();
        resultados.add(new Object[]{Marca.TOYOTA, 10L});
        resultados.add(new Object[]{Marca.FORD, 5L});

        when(veiculoRepository.countByMarca()).thenReturn(resultados);

        Map<String, Long> resultado = veiculoService.contarVeiculosPorMarca();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(10L, resultado.get(Marca.TOYOTA.name()));
        assertEquals(5L, resultado.get(Marca.FORD.name()));

        verify(veiculoRepository, times(1)).countByMarca();
    }

    @Test
    void testBuscarVeiculosNaUltimaSemana() {
        Veiculo veiculo = new Veiculo(1L, "Toyota", Marca.TOYOTA, 2021, "Carro novo", false, "Preto");
        List<Veiculo> veiculos = Collections.singletonList(veiculo);

        when(veiculoRepository.findVeiculosNaUltimaSemana(any(LocalDateTime.class))).thenReturn(veiculos);

        List<Veiculo> resultado = veiculoService.buscarVeiculosNaUltimaSemana();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("Toyota", resultado.get(0).getVeiculo());

        verify(veiculoRepository, times(1)).findVeiculosNaUltimaSemana(any(LocalDateTime.class));
    }


}

