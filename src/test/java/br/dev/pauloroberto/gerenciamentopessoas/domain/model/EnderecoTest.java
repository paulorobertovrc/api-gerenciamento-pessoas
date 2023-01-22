package br.dev.pauloroberto.gerenciamentopessoas.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void deveriaCriarUmObjetoEndereco() {
        Endereco endereco = new Endereco(
                null,
                "Rua das Flores",
                "123",
                "São Paulo",
                "SP",
                "12345-678",
                true,
                null);

        assertNotNull(endereco);
        assertEquals("Rua das Flores", endereco.getLogradouro());
        assertEquals("123", endereco.getNumero());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals("SP", endereco.getEstado());
        assertEquals("12345-678", endereco.getCep());
        assertTrue(endereco.isPrincipal());
        assertNull(endereco.getPessoa());
    }
}