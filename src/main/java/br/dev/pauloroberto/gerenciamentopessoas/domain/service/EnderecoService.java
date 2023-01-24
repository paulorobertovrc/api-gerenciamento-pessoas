package br.dev.pauloroberto.gerenciamentopessoas.domain.service;

import br.dev.pauloroberto.gerenciamentopessoas.api.model.EnderecoDto;
import br.dev.pauloroberto.gerenciamentopessoas.api.model.EnderecoResponseDto;
import br.dev.pauloroberto.gerenciamentopessoas.api.model.EnderecoUpdateDto;
import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Endereco;
import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Pessoa;
import br.dev.pauloroberto.gerenciamentopessoas.domain.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public Endereco cadastrarEndereco(Pessoa pessoa, EnderecoDto enderecoDto) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDto.logradouro());
        endereco.setNumero(enderecoDto.numero());
        endereco.setCep(enderecoDto.cep());
        endereco.setCidade(enderecoDto.cidade());
        endereco.setEstado(enderecoDto.estado());
        endereco.setPessoa(pessoa);

        enderecoRepository.save(endereco);

        return endereco;
    }

    public void atualizarEndereco(Long id, EnderecoUpdateDto enderecoDto) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow();

        if (enderecoDto.logradouro() != null &&
            (!enderecoDto.logradouro().isBlank() ||
            !enderecoDto.logradouro().equals(endereco.getLogradouro()))
        ) {
            endereco.setLogradouro(enderecoDto.logradouro());
        }

        if (enderecoDto.numero() != null &&
                (!enderecoDto.numero().isBlank() ||
                        !enderecoDto.numero().equals(endereco.getNumero()))
        ) {
            endereco.setNumero(enderecoDto.numero());
        }

        if (enderecoDto.cep() != null &&
                (!enderecoDto.cep().isBlank() ||
                        !enderecoDto.cep().equals(endereco.getCep()))
        ) {
            endereco.setCep(enderecoDto.cep());
        }

        if (enderecoDto.cidade() != null &&
                (!enderecoDto.cidade().isBlank() ||
                        !enderecoDto.cidade().equals(endereco.getCidade()))
        ) {
            endereco.setCidade(enderecoDto.cidade());
        }

        if (enderecoDto.estado() != null &&
                (!enderecoDto.estado().isBlank() ||
                        !enderecoDto.estado().equals(endereco.getEstado()))
        ) {
            endereco.setEstado(enderecoDto.estado());
        }

        enderecoRepository.save(endereco);
    }

    public Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id).orElseThrow();
    }

    public void removerEndereco(Long enderecoId) {
        enderecoRepository.deleteById(enderecoId);
    }

    public List<EnderecoResponseDto> buscarEnderecosPorPessoaId(Long id) {
        return enderecoRepository.findAllByPessoaId(id).stream().map(EnderecoResponseDto::new).toList();
    }

    public boolean enderecoExiste(Long id) {
        return enderecoRepository.existsById(id);
    }

    public void removerTodosEnderecos(Long id) {
        enderecoRepository.deleteAllByPessoaId(id);
    }
}
