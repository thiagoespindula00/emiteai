package thiagoespindula00.emiteai.backend.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitConfiguration {
    public static final String CSV_QUEUE = "csv.queue";

    @Bean
    public Queue queue() {
        return new Queue(CSV_QUEUE, true);
    }
}
