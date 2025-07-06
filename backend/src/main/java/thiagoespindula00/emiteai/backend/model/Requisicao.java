package thiagoespindula00.emiteai.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "requisicoes")
public class Requisicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String metodoHttp;

    private String endpoint;

    private LocalDateTime dataHora;

    public Requisicao() {
    }

    public Requisicao(String metodoHttp, String endpoint) {
        this.metodoHttp = metodoHttp;
        this.endpoint = endpoint;
        this.dataHora = LocalDateTime.now();
    }
}
