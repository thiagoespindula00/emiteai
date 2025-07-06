package thiagoespindula00.emiteai.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record PessoaRequestDto(
        @Size(max = 100)
        @NotBlank
        String nome,

        @CPF
        @NotBlank
        String cpf,

        @Valid
        EnderecoRequestDto endereco
) {
}
