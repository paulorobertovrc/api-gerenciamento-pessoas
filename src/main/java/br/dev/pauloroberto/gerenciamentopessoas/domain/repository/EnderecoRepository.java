package br.dev.pauloroberto.gerenciamentopessoas.domain.repository;

import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {
    Optional<Object> findByPessoaId(Long id);

    List<Endereco> findAllByPessoaId(Long pessoaId);
}
