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
    // public static final String EXCHANGE = "pessoa.export.exchange";

    @Bean
    public Queue queue() {
        return new Queue(CSV_QUEUE, true);
    }

//    @Bean
//    public DirectExchange exchange() {
//        return new DirectExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Binding binding(Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("pessoa.export");
//    }
}
