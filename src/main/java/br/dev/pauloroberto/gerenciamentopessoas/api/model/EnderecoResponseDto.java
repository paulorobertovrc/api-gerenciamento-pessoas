package br.dev.pauloroberto.gerenciamentopessoas.api.model;

import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Endereco;
import com.fasterxml.jackson.annotation.JsonInclude;

public record EnderecoResponseDto(

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long id,
    String logradouro,
    String numero,
    String cep,
    String cidade,
    String estado
) {
    public EnderecoResponseDto(EnderecoResponseDto endereco) {
        this(
            endereco.id,
            endereco.logradouro,
            endereco.numero,
            endereco.cep,
            endereco.cidade,
            endereco.estado
        );
    }

    public EnderecoResponseDto(EnderecoDto enderecoDto) {
        this(
                null,
                enderecoDto.logradouro(),
                enderecoDto.numero(),
                enderecoDto.cep(),
                enderecoDto.cidade(),
                enderecoDto.estado()
        );
    }

    public EnderecoResponseDto(Endereco endereco) {
        this(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getEstado()
        );
    }
}
