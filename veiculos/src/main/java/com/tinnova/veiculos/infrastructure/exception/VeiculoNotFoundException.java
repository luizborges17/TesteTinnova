package com.tinnova.veiculos.infrastructure.exception;

public class VeiculoNotFoundException extends RuntimeException {
    public VeiculoNotFoundException() {
        super("Veículo com não encontrado.");
    }
}
