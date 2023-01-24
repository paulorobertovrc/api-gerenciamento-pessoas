package br.dev.pauloroberto.gerenciamentopessoas.api.model;

import org.springframework.web.bind.annotation.RequestParam;

public record PessoaUpdateDto(
    @RequestParam(required = false, defaultValue = "") String nome,
    @RequestParam(required = false, defaultValue = "") String nascimento
) {
}
