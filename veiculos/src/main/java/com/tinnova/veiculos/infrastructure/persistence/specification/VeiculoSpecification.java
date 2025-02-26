package com.tinnova.veiculos.infrastructure.persistence.specification;

import com.tinnova.veiculos.domain.enums.Marca;
import com.tinnova.veiculos.domain.model.Veiculo;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe responsável por construir a especificação para a busca de veículos com base nos filtros fornecidos.
 *
 * <p>
 * A classe utiliza a API de Specification do Spring Data JPA para criar consultas dinâmicas e flexíveis para o repositório
 * de veículos.
 * </p>
 *
 * <p>
 * Caso algum dos parâmetros seja {@code null} ou vazio, o respectivo filtro será ignorado na consulta.
 * </p>
 */
public class VeiculoSpecification {
    public static Specification<Veiculo> filtrar(Marca marca, Integer ano, String cor) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (marca != null) {
                predicates.add(cb.equal(root.get("marca"), marca));
            }

            if (ano != null) {
                predicates.add(cb.equal(root.get("ano"), ano));
            }

            if (cor != null && !cor.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("cor")), "%" + cor.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
