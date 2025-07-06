package thiagoespindula00.emiteai.backend.dto;

import thiagoespindula00.emiteai.backend.model.Pessoa;

public record PessoaDetalhesDto(
        Long id,
        String nome,
        String cpf,
        EnderecoDetalhesDto endereco
) {
    public static PessoaDetalhesDto fromEntity(Pessoa pessoa) {
        return new PessoaDetalhesDto(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                EnderecoDetalhesDto.fromEntity(pessoa.getEndereco())
        );
    }
}
