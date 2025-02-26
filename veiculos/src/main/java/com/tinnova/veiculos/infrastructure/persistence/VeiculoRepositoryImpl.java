package com.tinnova.veiculos.infrastructure.persistence;

import com.tinnova.veiculos.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VeiculoRepositoryImpl extends JpaRepository<Veiculo, Long>, JpaSpecificationExecutor<Veiculo> {
    long countByVendidoFalse();

    @Query("SELECT (v.ano / 10) * 10 AS decada, COUNT(v) FROM Veiculo v " +
            "WHERE v.ano IS NOT NULL GROUP BY (v.ano / 10) * 10 ORDER BY decada")
    List<Object[]> countByDecadaAno();

    @Query("SELECT v.marca, COUNT(v) FROM Veiculo v GROUP BY v.marca")
    List<Object[]> countByMarca();


    @Query("SELECT v FROM Veiculo v WHERE v.created >= :oneWeekAgo")
    List<Veiculo> findVeiculosNaUltimaSemana(LocalDateTime oneWeekAgo);
}
