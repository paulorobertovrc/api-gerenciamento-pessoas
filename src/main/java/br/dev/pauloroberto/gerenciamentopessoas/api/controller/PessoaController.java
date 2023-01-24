package br.dev.pauloroberto.gerenciamentopessoas.api.controller;

import br.dev.pauloroberto.gerenciamentopessoas.api.model.EnderecoDto;
import br.dev.pauloroberto.gerenciamentopessoas.api.model.EnderecoResponseDto;
import br.dev.pauloroberto.gerenciamentopessoas.api.model.PessoaDto;
import br.dev.pauloroberto.gerenciamentopessoas.api.model.PessoaResponseDto;
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

@RestController
@RequestMapping("/pessoas")
@AllArgsConstructor
public class PessoaController {
    private final PessoaService pessoaService;
    private final EnderecoService enderecoService;

    @PostMapping
    @Transactional
    public ResponseEntity<PessoaResponseDto> cadastrarPessoa(@RequestBody PessoaDto pessoaDto,
                                                             @NotNull UriComponentsBuilder uriComponentsBuilder) {
        Pessoa pessoa = pessoaService.cadastrarPessoa(pessoaDto);
        URI uri = uriComponentsBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();

        return ResponseEntity.created(uri).body(new PessoaResponseDto(pessoa));
    }

    @PostMapping("/{id}/enderecos")
    @Transactional
    public ResponseEntity<PessoaResponseDto> cadastrarEndereco(@PathVariable Long id,
                                                                 @RequestBody EnderecoDto enderecoDto,
                                                                 @NotNull UriComponentsBuilder uriComponentsBuilder) {
        Pessoa pessoa = pessoaService.cadastrarEndereco(id, enderecoDto);
        URI uri = uriComponentsBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();

        return ResponseEntity.created(uri).body(new PessoaResponseDto(pessoa, new EnderecoResponseDto(enderecoDto)));
    }

    @PutMapping("/{pessoaId}/enderecos/{enderecoId}/definir-principal")
    @Transactional
    public ResponseEntity<PessoaResponseDto> definirEnderecoPrincipal(@PathVariable Long pessoaId,
                                                                      @PathVariable Long enderecoId) {
        enderecoService.definirEnderecoPrincipal(pessoaId, enderecoId);
        return ResponseEntity.ok(new PessoaResponseDto(
                pessoaService.buscarPessoaPorId(pessoaId),
                new EnderecoResponseDto(enderecoService.buscarEnderecoPorId(enderecoId))
        ));
    }
}
