package thiagoespindula00.emiteai.backend.model;

import jakarta.persistence.*;
import thiagoespindula00.emiteai.backend.dto.PessoaRequestDto;

@Entity
@Table(name = "pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

    @Embedded
    private Endereco endereco;

    public Pessoa() {
    }

    private Pessoa(PessoaRequestDto requestDto) {
        this.setCampos(requestDto);
    }

    public static Pessoa fromDto(PessoaRequestDto requestDto) {
        return new Pessoa(requestDto);
    }

    public void setCampos(PessoaRequestDto requestDto) {
        this.nome = requestDto.nome();
        this.cpf = requestDto.cpf();
        this.endereco = Endereco.fromDto(requestDto.endereco());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
