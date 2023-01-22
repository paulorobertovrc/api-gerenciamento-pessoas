package br.dev.pauloroberto.gerenciamentopessoas.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

        @Test
        void deveriaCriarUmObjetoPessoaComUmEndereco() {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome("Paulo");
            pessoa.setDataNascimento(LocalDate.of(1985, 05, 02));
            pessoa.setEndereco(List.of(new Endereco(
                    null,
                    "Rua das Flores",
                    "123",
                    "São Paulo",
                    "SP",
                    "12345-678",
                    true,
                    null
            )));

            assertNotNull(pessoa);
            assertEquals("Paulo", pessoa.getNome());
            assertEquals(LocalDate.of(1985, 05, 02), pessoa.getDataNascimento());
            assertEquals(1, pessoa.getEndereco().size());
            assertEquals("Rua das Flores", pessoa.getEndereco().get(0).getLogradouro());
            assertEquals("123", pessoa.getEndereco().get(0).getNumero());
            assertEquals("São Paulo", pessoa.getEndereco().get(0).getCidade());
            assertEquals("SP", pessoa.getEndereco().get(0).getEstado());
            assertEquals("12345-678", pessoa.getEndereco().get(0).getCep());
            assertTrue(pessoa.getEndereco().get(0).isPrincipal());
        }
}