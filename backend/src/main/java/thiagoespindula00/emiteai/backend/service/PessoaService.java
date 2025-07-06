package thiagoespindula00.emiteai.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
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
}
