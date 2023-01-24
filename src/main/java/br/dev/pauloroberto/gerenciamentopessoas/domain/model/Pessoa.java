package br.dev.pauloroberto.gerenciamentopessoas.domain.model;

import br.dev.pauloroberto.gerenciamentopessoas.api.model.PessoaDto;
import br.dev.pauloroberto.gerenciamentopessoas.domain.ValidationGroups;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NotNull(groups = ValidationGroups.PessoaId.class)
    private Long id;
    private String nome;
    private LocalDate nascimento;
    private Long enderecoPrincipal;

    public Pessoa(PessoaDto pessoaDto) {
        this.nome = pessoaDto.nome();
        this.nascimento = LocalDate.parse(pessoaDto.nascimento());
        this.enderecoPrincipal = null;
    }
}
