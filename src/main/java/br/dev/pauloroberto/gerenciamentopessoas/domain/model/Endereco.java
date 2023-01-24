package br.dev.pauloroberto.gerenciamentopessoas.domain.model;

import br.dev.pauloroberto.gerenciamentopessoas.api.model.EnderecoDto;
import br.dev.pauloroberto.gerenciamentopessoas.domain.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NotNull(groups = ValidationGroups.EnderecoId.class)
    private Long id;
    @NotNull
    private String logradouro;
    @NotNull
    private String numero;
    @NotNull
    private String cidade;
    @NotNull
    private String estado;
    @NotNull
    private String cep;
    @ManyToOne
    @NotNull
    @Valid
    @ConvertGroup(to = ValidationGroups.PessoaId.class)
    private Pessoa pessoa;
    private boolean principal;

    public Endereco(EnderecoDto endereco, Pessoa pessoa) {
        this.logradouro = endereco.logradouro();
        this.numero = endereco.numero();
        this.cidade = endereco.cidade();
        this.estado = endereco.estado();
        this.cep = endereco.cep();
        this.pessoa = pessoa;
    }

    public void setPrincipal() {
        this.principal = true;
        this.pessoa.setEnderecoPrincipal(this.id);
    }

    public void unsetPrincipal() {
        this.principal = false;
    }
}
