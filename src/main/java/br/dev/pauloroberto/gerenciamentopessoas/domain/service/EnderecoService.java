package br.dev.pauloroberto.gerenciamentopessoas.domain.service;

import br.dev.pauloroberto.gerenciamentopessoas.api.model.EnderecoDto;
import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Endereco;
import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Pessoa;
import br.dev.pauloroberto.gerenciamentopessoas.domain.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    public Endereco atualizarEndereco(Long id, EnderecoDto enderecoDto) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(enderecoDto, endereco, "id");

        enderecoRepository.save(endereco);

        return endereco;
    }

    public void excluirEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

    public void definirEnderecoPrincipal(Long enderecoId, Long pessoaId) {
        List<Endereco> enderecos = enderecoRepository.findAllByPessoaId(pessoaId);
        enderecos.forEach(Endereco::unsetPrincipal);
        enderecoRepository.findById(enderecoId).ifPresent(Endereco::setPrincipal);
        enderecoRepository.saveAll(enderecos);
    }

    public Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id).orElseThrow();
    }
}
