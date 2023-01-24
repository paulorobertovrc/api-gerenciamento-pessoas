package br.dev.pauloroberto.gerenciamentopessoas.domain.service;

import br.dev.pauloroberto.gerenciamentopessoas.api.model.EnderecoDto;
import br.dev.pauloroberto.gerenciamentopessoas.api.model.PessoaDto;
import br.dev.pauloroberto.gerenciamentopessoas.api.model.PessoaUpdateDto;
import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Endereco;
import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Pessoa;
import br.dev.pauloroberto.gerenciamentopessoas.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public void cadastrarEndereco(Long id, EnderecoDto enderecoDto) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow();
        Endereco endereco = enderecoService.cadastrarEndereco(pessoa, enderecoDto);

        pessoaRepository.save(pessoa);
    }

    public Pessoa buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id).orElseThrow();
    }

    public List<Pessoa> buscarPessoas() {
        return (List<Pessoa>) pessoaRepository.findAll();
    }

    public void definirEnderecoPrincipal(Long pessoaId, Long enderecoId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow();
        pessoa.setEnderecoPrincipal(enderecoId);
    }

    public void atualizarPessoa(Long id, PessoaUpdateDto pessoaUpdateDto) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow();

        if (pessoaUpdateDto.nome() != null &&
                (!pessoaUpdateDto.nome().isBlank() ||
                !pessoaUpdateDto.nome().equals(pessoa.getNome()))
        ) {
            pessoa.setNome(pessoaUpdateDto.nome());
        }

        if (pessoaUpdateDto.nascimento() != null &&
                (!pessoaUpdateDto.nascimento().isBlank() ||
                        !pessoaUpdateDto.nascimento().equals(pessoa.getNascimento().toString()))
        ) {
            pessoa.setNascimento(LocalDate.parse(pessoaUpdateDto.nascimento()));
        }

        pessoaRepository.save(pessoa);
    }

    public boolean pessoaExiste(Long id) {
        return pessoaRepository.existsById(id);
    }

    public void removerPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }

    public void removerEnderecoPrincipal(Long pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow();
        pessoa.setEnderecoPrincipal(null);
    }
}
