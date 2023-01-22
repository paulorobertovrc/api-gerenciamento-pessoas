package br.dev.pauloroberto.gerenciamentopessoas.domain.repository;

import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
    Optional<Pessoa> findByNome(String nome);
}
