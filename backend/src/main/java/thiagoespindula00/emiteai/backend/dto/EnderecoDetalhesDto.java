package thiagoespindula00.emiteai.backend.dto;

import thiagoespindula00.emiteai.backend.model.Endereco;

public record EnderecoDetalhesDto(
        String numero,
        String complemento,
        String cep,
        String bairro,
        String municipio,
        String estado
) {
    public static EnderecoDetalhesDto fromEntity(Endereco endereco) {
        return new EnderecoDetalhesDto(
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCep(),
                endereco.getBairro(),
                endereco.getMunicipio(),
                endereco.getEstado()
        );
    }
}
