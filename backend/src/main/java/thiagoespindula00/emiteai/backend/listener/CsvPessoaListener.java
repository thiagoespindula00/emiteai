package thiagoespindula00.emiteai.backend.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import thiagoespindula00.emiteai.backend.configuration.RabbitConfiguration;
import thiagoespindula00.emiteai.backend.dto.PessoaDetalhesDto;
import thiagoespindula00.emiteai.backend.service.CsvPessoaService;
import thiagoespindula00.emiteai.backend.service.PessoaService;

import java.util.List;

@Component
public class CsvPessoaListener {
    private final CsvPessoaService csvPessoaService;

    public CsvPessoaListener(CsvPessoaService csvPessoaService) {
        this.csvPessoaService = csvPessoaService;
    }

    @RabbitListener(queues = RabbitConfiguration.CSV_QUEUE)
    public void listenCsv(String csv) {
        System.out.println("Listener diz: " + csv);
        csvPessoaService.gerarCsvPessoas();
    }
}
