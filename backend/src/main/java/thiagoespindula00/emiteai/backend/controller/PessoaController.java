package thiagoespindula00.emiteai.backend.controller;

import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import thiagoespindula00.emiteai.backend.configuration.RabbitConfiguration;
import thiagoespindula00.emiteai.backend.dto.PessoaDetalhesDto;
import thiagoespindula00.emiteai.backend.dto.PessoaRequestDto;
import thiagoespindula00.emiteai.backend.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService service;
    private final RabbitTemplate rabbitTemplate;

    public PessoaController(PessoaService service, RabbitTemplate rabbitTemplate) {
        this.service = service;
        this.rabbitTemplate = rabbitTemplate;
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

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaRequestDto requestDto) {
        service.atualizar(id, requestDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        service.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDetalhesDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDetalhesDto> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(service.detalhar(id));
    }

    @PostMapping("/csv")
    public ResponseEntity<?> gerarCsv() {
        rabbitTemplate.convertAndSend(RabbitConfiguration.CSV_QUEUE, "Frontend solicitou geração de csv");

        return ResponseEntity.accepted().build();
    }
}
