package thiagoespindula00.emiteai.backend.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import thiagoespindula00.emiteai.backend.dto.PessoaDetalhesDto;
import thiagoespindula00.emiteai.backend.dto.PessoaRequestDto;
import thiagoespindula00.emiteai.backend.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PessoaDetalhesDto> cadastrar(@RequestBody @Valid PessoaRequestDto requestDto, UriComponentsBuilder uriBuilder) {
        PessoaDetalhesDto pessoaCadastrada = service.cadastrar(requestDto);

        var location = uriBuilder
                .path("pessoas/{id}")
                .buildAndExpand(pessoaCadastrada.id())
                .toUri();

        return ResponseEntity.created(location).body(pessoaCadastrada);
    }
}
