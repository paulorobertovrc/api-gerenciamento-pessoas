package br.dev.pauloroberto.gerenciamentopessoas.api.model;

import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public record PessoaResponseDto(
    Long id,
    String nome,
    String nascimento,
    List<EnderecoResponseDto> endereco
) {
    public PessoaResponseDto(Pessoa pessoa, List<EnderecoResponseDto> enderecos) {
        this(
            pessoa.getId(),
            pessoa.getNome(),
            pessoa.getNascimento().toString(),
            new ArrayList<>()
        );
        enderecos.forEach(endereco -> this.endereco.add(new EnderecoResponseDto(endereco)));
    }

    public PessoaResponseDto(Pessoa pessoa) {
        this(
            pessoa.getId(),
            pessoa.getNome(),
            pessoa.getNascimento().toString(),
            new ArrayList<>()
        );
    }

    public PessoaResponseDto(Pessoa pessoa, EnderecoResponseDto enderecoResponseDto) {
        this(
            pessoa.getId(),
            pessoa.getNome(),
            pessoa.getNascimento().toString(),
            new ArrayList<>()
        );
        this.endereco.add(new EnderecoResponseDto(enderecoResponseDto));
    }
}
