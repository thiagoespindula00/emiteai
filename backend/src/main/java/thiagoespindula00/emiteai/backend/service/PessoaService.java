package thiagoespindula00.emiteai.backend.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thiagoespindula00.emiteai.backend.dto.PessoaDetalhesDto;
import thiagoespindula00.emiteai.backend.dto.PessoaRequestDto;
import thiagoespindula00.emiteai.backend.model.Pessoa;
import thiagoespindula00.emiteai.backend.repository.PessoaRepository;
import thiagoespindula00.emiteai.backend.trata_erros.ValidacaoException;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    private void validaCpfDuplicado(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new ValidacaoException("CPF já cadastrado");
        }
    }

    @Transactional
    public PessoaDetalhesDto cadastrar(PessoaRequestDto pessoaRequestDto) {
        validaCpfDuplicado(pessoaRequestDto.cpf());

        Pessoa pessoaCadastrada = repository.save(Pessoa.fromDto(pessoaRequestDto));
        return PessoaDetalhesDto.fromEntity(pessoaCadastrada);
    }

    @Transactional
    public void atualizar(Long id, PessoaRequestDto pessoaRequestDto) {
        Pessoa pessoa = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (!pessoa.getCpf().equals(pessoaRequestDto.cpf())) {
            validaCpfDuplicado(pessoaRequestDto.cpf());
        }
        pessoa.setCampos(pessoaRequestDto);
        repository.save(pessoa);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        repository.deleteById(id);
    }

    public Page<PessoaDetalhesDto> listar(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(PessoaDetalhesDto::fromEntity);
    }

    public PessoaDetalhesDto detalhar(Long id) {
        Pessoa pessoa = repository.findById(id).orElseThrow(EntityNotFoundException::new);

        return PessoaDetalhesDto.fromEntity(pessoa);
    }
}
