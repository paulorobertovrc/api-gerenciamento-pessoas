package br.dev.pauloroberto.gerenciamentopessoas.api.controller;

import br.dev.pauloroberto.gerenciamentopessoas.api.model.*;
import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Endereco;
import br.dev.pauloroberto.gerenciamentopessoas.domain.model.Pessoa;
import br.dev.pauloroberto.gerenciamentopessoas.domain.service.EnderecoService;
import br.dev.pauloroberto.gerenciamentopessoas.domain.service.PessoaService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
@AllArgsConstructor
public class PessoaController {
    private final PessoaService pessoaService;
    private final EnderecoService enderecoService;

    @PostMapping
    @Transactional
    public ResponseEntity<PessoaDto> cadastrarPessoa(@RequestBody PessoaDto pessoaDto,
                                                             @NotNull UriComponentsBuilder uriComponentsBuilder) {
        Pessoa pessoa = pessoaService.cadastrarPessoa(pessoaDto);
        URI uri = uriComponentsBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();

        return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
    }

    @PostMapping("/{id}/enderecos")
    @Transactional
    public ResponseEntity<PessoaResponseDto> cadastrarEndereco(@PathVariable Long id,
                                                                 @RequestBody EnderecoDto enderecoDto,
                                                                 @NotNull UriComponentsBuilder uriComponentsBuilder) {
        pessoaService.cadastrarEndereco(id, enderecoDto);
        Pessoa pessoa = pessoaService.buscarPessoaPorId(id);
        URI uri = uriComponentsBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();

        return ResponseEntity.created(uri).body(new PessoaResponseDto(pessoa, enderecoDto));
    }

    @GetMapping
    public List<Pessoa> buscarPessoas() {
        return pessoaService.buscarPessoas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponseDto> buscarPessoaPorId(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.buscarPessoaPorId(id);

        if (pessoa.getEnderecoPrincipal() == null) {
            return ResponseEntity.ok(new PessoaResponseDto(pessoa));
        }

        Endereco endereco = enderecoService.buscarEnderecoPorId(pessoa.getEnderecoPrincipal());

        return ResponseEntity.ok(new PessoaResponseDto(pessoa, new EnderecoResponseDto(endereco)));
    }

    @GetMapping("/{id}/enderecos")
    public List<EnderecoResponseDto> buscarEnderecosPorPessoaId(@PathVariable Long id) {
        return enderecoService.buscarEnderecosPorPessoaId(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PessoaResponseDto> atualizarPessoa(@PathVariable Long id,
                                                             @RequestBody PessoaUpdateDto pessoaUpdateDto) {
        pessoaService.atualizarPessoa(id, pessoaUpdateDto);
        Pessoa pessoa = pessoaService.buscarPessoaPorId(id);

        if (pessoa.getEnderecoPrincipal() == null) {
            return ResponseEntity.ok(new PessoaResponseDto(pessoa));
        }

        Endereco endereco = enderecoService.buscarEnderecoPorId(pessoa.getEnderecoPrincipal());

        return ResponseEntity.ok(new PessoaResponseDto(pessoa, new EnderecoResponseDto(endereco)));
    }

    @PutMapping("/{pessoaId}/enderecos/{enderecoId}")
    @Transactional
    public ResponseEntity<PessoaResponseDto> atualizarEndereco(@PathVariable Long pessoaId,
                                                               @PathVariable Long enderecoId,
                                                               @RequestBody EnderecoUpdateDto enderecoUpdateDto) {
        enderecoService.atualizarEndereco(enderecoId, enderecoUpdateDto);
        Pessoa pessoa = pessoaService.buscarPessoaPorId(pessoaId);
        Endereco endereco = enderecoService.buscarEnderecoPorId(enderecoId);

        return ResponseEntity.ok(new PessoaResponseDto(pessoa, new EnderecoResponseDto(endereco)));
    }

    @PutMapping("/{pessoaId}/enderecos/{enderecoId}/definir-principal")
    @Transactional
    public ResponseEntity<PessoaResponseDto> definirEnderecoPrincipal(@PathVariable Long pessoaId,
                                                                      @PathVariable Long enderecoId) {
        pessoaService.definirEnderecoPrincipal(pessoaId, enderecoId);

        return ResponseEntity.ok(new PessoaResponseDto(
                pessoaService.buscarPessoaPorId(pessoaId),
                new EnderecoResponseDto(enderecoService.buscarEnderecoPorId(enderecoId))
        ));
    }

    @DeleteMapping("/{pessoaId}/enderecos/{enderecoId}")
    @Transactional
    public ResponseEntity<Void> removerEndereco(@PathVariable Long pessoaId,
                                                @PathVariable Long enderecoId) {
        if (!enderecoService.enderecoExiste(enderecoId)) {
            return ResponseEntity.notFound().build();
        }

        Pessoa pessoa = pessoaService.buscarPessoaPorId(pessoaId);
        if (pessoa.getEnderecoPrincipal().equals(enderecoId)) {
            pessoaService.removerEnderecoPrincipal(pessoaId);
        }

        enderecoService.removerEndereco(enderecoId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> removerPessoa(@PathVariable Long id) {
        if (!pessoaService.pessoaExiste(id)) {
            return ResponseEntity.notFound().build();
        }

        enderecoService.removerTodosEnderecos(id);
        pessoaService.removerPessoa(id);

        return ResponseEntity.noContent().build();
    }
}
