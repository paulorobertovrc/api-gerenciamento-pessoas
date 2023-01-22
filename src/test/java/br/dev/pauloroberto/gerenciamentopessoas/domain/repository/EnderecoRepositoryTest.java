package br.dev.pauloroberto.gerenciamentopessoas.domain.repository;

import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Endereco;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EnderecoRepositoryTest {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Test
    public void deveriaCarregarumRecursoEnderecoPeloId() {
        Long id = 1L;
        Endereco endereco = enderecoRepository.findById(id).orElse(null);

        assertNotNull(endereco);
        assertEquals(id, endereco.getId());
        assertEquals("Rua das Flores", endereco.getLogradouro());
        assertEquals("123", endereco.getNumero());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals("SP", endereco.getEstado());
        assertEquals("12345-678", endereco.getCep());
        assertTrue(endereco.isPrincipal());
    }

    @Test
    public void deveriaCarregarumRecursoEnderecoPeloIdDaPessoa() {
        Long id = 1L;
        Endereco endereco = (Endereco) enderecoRepository.findByPessoaId(id).orElseThrow();

        assertNotNull(endereco);
        assertEquals(id, endereco.getId());
        assertEquals("Rua das Flores", endereco.getLogradouro());
        assertEquals("123", endereco.getNumero());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals("SP", endereco.getEstado());
        assertEquals("12345-678", endereco.getCep());
        assertTrue(endereco.isPrincipal());
    }

}