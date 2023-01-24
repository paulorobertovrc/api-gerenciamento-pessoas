package br.dev.pauloroberto.gerenciamentopessoas.api.model;

import org.springframework.web.bind.annotation.RequestParam;

public record EnderecoUpdateDto(
    @RequestParam(required = false, defaultValue = "")
    String logradouro,
    @RequestParam(required = false, defaultValue = "")
    String numero,
    @RequestParam(required = false, defaultValue = "")
    String cep,
    @RequestParam(required = false, defaultValue = "")
    String cidade,
    @RequestParam(required = false, defaultValue = "")
    String estado
) {
}
