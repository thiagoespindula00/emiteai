package thiagoespindula00.emiteai.backend.dto;

import jakarta.validation.constraints.Size;

public record EnderecoRequestDto(
        @Size(max = 10)
        String numero,

        @Size(max = 255)
        String complemento,

        @Size(max = 10)
        String cep,

        @Size(max = 100)
        String bairro,

        @Size(max = 100)
        String municipio,

        @Size(min = 2, max = 2)
        String estado
) {
}
