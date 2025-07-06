package thiagoespindula00.emiteai.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import thiagoespindula00.emiteai.backend.dto.PessoaDetalhesDto;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CsvPessoaService {
    private final PessoaService pessoaService;

    @Value("${csv.diretorio}")
    private String diretorioCsv;

    public CsvPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    private String geraConteudoCsv() {
        List<PessoaDetalhesDto> pessoas = pessoaService.listarTodos();
        StringBuffer conteudoCsv = new StringBuffer();

        conteudoCsv.append("CPF;Nome;CEP;Bairro;Municipio;Numero;Complemento;Estado").append("\n");
        for (PessoaDetalhesDto pessoa : pessoas) {
            conteudoCsv.append(pessoa.cpf()).append(";")
                    .append(pessoa.nome()).append(";")
                    .append(pessoa.endereco().cep()).append(";")
                    .append(pessoa.endereco().bairro()).append(";")
                    .append(pessoa.endereco().municipio()).append(";")
                    .append(pessoa.endereco().numero()).append(";")
                    .append(pessoa.endereco().complemento()).append(";")
                    .append(pessoa.endereco().estado())
                    .append("\n");
        }

        System.out.println(conteudoCsv.toString());

        return conteudoCsv.toString();
    }

    public void gerarCsvPessoas() {
        System.out.println("Iniciando exportacao no diretorio " + diretorioCsv);
        try {
            Path pastaCsv = Paths.get(diretorioCsv);
            if (!Files.exists(pastaCsv)) {
                Files.createDirectory(pastaCsv);
            }

            Path arquivoCsv = pastaCsv.resolve("pessoas-" + System.currentTimeMillis() + ".csv");
            try (BufferedWriter writer = Files.newBufferedWriter(arquivoCsv)) {
                writer.write(geraConteudoCsv());

                System.out.println("Arquivo CSV gerado com sucesso em " + arquivoCsv.toAbsolutePath());
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
