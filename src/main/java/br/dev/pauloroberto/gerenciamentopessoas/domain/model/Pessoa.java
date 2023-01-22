package br.dev.pauloroberto.gerenciamentopessoas.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Endereco> endereco;
}
