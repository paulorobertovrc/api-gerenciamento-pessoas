package br.dev.pauloroberto.gerenciamentopessoas.domain.service;

import br.dev.pauloroberto.gerenciamentopessoas.api.model.EnderecoDto;
import br.dev.pauloroberto.gerenciamentopessoas.api.model.PessoaDto;
import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Pessoa;
import br.dev.pauloroberto.gerenciamentopessoas.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final EnderecoService enderecoService;

    public Pessoa cadastrarPessoa(PessoaDto pessoaDto) {
        Pessoa pessoa = new Pessoa(pessoaDto);
        pessoaRepository.save(pessoa);

        return pessoa;
    }

    public Pessoa cadastrarEndereco(Long id, EnderecoDto enderecoDto) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow();
        enderecoService.cadastrarEndereco(pessoa, enderecoDto);

        pessoaRepository.save(pessoa);

        return pessoa;
    }

    public Pessoa buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id).orElseThrow();
    }
}
