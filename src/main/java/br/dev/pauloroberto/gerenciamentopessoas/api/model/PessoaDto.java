package br.dev.pauloroberto.gerenciamentopessoas.api.model;

import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Pessoa;

public record PessoaDto(
    Long id,
    String nome,
    String nascimento
) {
    public PessoaDto(Pessoa pessoa) {
        this(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getNascimento().toString()
        );
    }
}
