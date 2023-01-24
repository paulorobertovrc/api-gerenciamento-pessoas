package br.dev.pauloroberto.gerenciamentopessoas.api.model;

import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Endereco;
import org.jetbrains.annotations.NotNull;

public record EnderecoDto(
    String logradouro,
    String numero,
    String cep,
    String cidade,
    String estado
) {
    public EnderecoDto(@NotNull String logradouro,
                       String numero,
                       String cep,
                       String cidade,
                       String estado) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public EnderecoDto(@NotNull Endereco endereco) {
        this(
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getEstado());
    }
}
