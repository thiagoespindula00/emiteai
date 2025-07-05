package thiagoespindula00.emiteai.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    @Column(length = 10)
    private String numero;

    private String complemento;

    @Column(length = 10)
    private String cep;

    @Column(length = 100)
    private String bairro;

    @Column(length = 100)
    private String municipio;

    @Column(length = 2)
    private String estado;
}
