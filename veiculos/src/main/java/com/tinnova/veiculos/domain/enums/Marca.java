package com.tinnova.veiculos.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.tinnova.veiculos.infrastructure.exception.MarcaInvalidaException;

import java.util.stream.Stream;


/**
 * Enum que representa as marcas de veículos permitidas no sistema.
 *
 * <p>
 * É possível adicionar novas marcas à medida que o sistema evolui, bastando incluir novos valores neste Enum.
 * </p>
 *
 * <h3>Função {@link #fromString(String)}</h3>
 * <p>
 * A função {@code fromString} converte uma String fornecida (ignorando maiúsculas/minúsculas)
 * para o respectivo valor do Enum. Se a String não corresponder a nenhuma marca válida, uma exceção será lançada.
 * </p>
 */
public enum Marca {
    TOYOTA,
    HONDA,
    FORD,
    VOLKSWAGEN,
    CHEVROLET;

    @JsonCreator
    public static Marca fromString(String value) {
        return Stream.of(Marca.values())
                .filter(marca -> marca.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(MarcaInvalidaException::new);
    }
}