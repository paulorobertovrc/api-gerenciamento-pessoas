package br.dev.pauloroberto.gerenciamentopessoas.domain.repository;

import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Endereco;
import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PessoaRepositoryTest {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Test
    public void deveriaCarregarumRecursoPessoaPeloId() {
        Long id = 1L;
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        if (pessoa != null && endereco != null) {
            pessoa.setEndereco(List.of(endereco));
        }

        Assertions.assertNotNull(pessoa);
        assertEquals(id, pessoa.getId());
        assertEquals("Paulo", pessoa.getNome());
        assertEquals("1985-05-02", pessoa.getDataNascimento().toString());
        assertEquals("Rua das Flores", pessoa.getEndereco().get(0).getLogradouro());
        assertEquals("123", pessoa.getEndereco().get(0).getNumero());
        assertEquals("São Paulo", pessoa.getEndereco().get(0).getCidade());
        assertEquals("SP", pessoa.getEndereco().get(0).getEstado());
        assertEquals("12345-678", pessoa.getEndereco().get(0).getCep());
    }

    @Test
    public void deveriaCarregarumRecursoPessoaPeloNome() {
        String nome = "Paulo";
        Long id = 1L;
        Optional<Pessoa> pessoa = pessoaRepository.findByNome(nome);
        enderecoRepository.findById(id).ifPresent(endereco ->
                pessoa.orElseThrow().setEndereco(List.of(endereco))
        );

        Assertions.assertNotNull(pessoa);
        assertEquals(id, pessoa.orElseThrow().getId());
        assertEquals("Paulo", pessoa.orElseThrow().getNome());
        assertEquals("1985-05-02", pessoa.orElseThrow().getDataNascimento().toString());
        assertEquals("Rua das Flores", pessoa.orElseThrow().getEndereco().get(0).getLogradouro());
        assertEquals("123", pessoa.orElseThrow().getEndereco().get(0).getNumero());
        assertEquals("São Paulo", pessoa.orElseThrow().getEndereco().get(0).getCidade());
        assertEquals("SP", pessoa.orElseThrow().getEndereco().get(0).getEstado());
        assertEquals("12345-678", pessoa.orElseThrow().getEndereco().get(0).getCep());
    }
}