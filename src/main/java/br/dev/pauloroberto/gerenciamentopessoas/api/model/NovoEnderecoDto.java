package br.dev.pauloroberto.gerenciamentopessoas.api.model;

public record NovoEnderecoDto(
    String logradouro,
    String numero,
    String cep,
    String cidade,
    String estado
) {
    public NovoEnderecoDto(EnderecoDto enderecoDto) {
        this(
                enderecoDto.logradouro(),
                enderecoDto.numero(),
                enderecoDto.cep(),
                enderecoDto.cidade(),
                enderecoDto.estado()
        );
    }
}
