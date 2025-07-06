package thiagoespindula00.emiteai.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import thiagoespindula00.emiteai.backend.dto.EnderecoRequestDto;

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

    public Endereco() {
    }

    private Endereco(EnderecoRequestDto enderecoRequestDto) {
        this.setCampos(enderecoRequestDto);
    }

    public static Endereco fromDto(EnderecoRequestDto enderecoRequestDto) {
        return new Endereco(enderecoRequestDto);
    }

    public void setCampos(EnderecoRequestDto enderecoRequestDto) {
        this.numero = enderecoRequestDto.numero();
        this.complemento = enderecoRequestDto.complemento();
        this.cep = enderecoRequestDto.cep();
        this.bairro = enderecoRequestDto.bairro();
        this.municipio = enderecoRequestDto.municipio();
        this.estado = enderecoRequestDto.estado();
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getEstado() {
        return estado;
    }
}
