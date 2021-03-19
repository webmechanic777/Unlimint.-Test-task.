package by.unlimint.springConfig;

import by.unlimint.model.OrderEntry;
import by.unlimint.service.OrderEntryProducer;
import com.google.gson.Gson;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class SpringConfig {

    @Autowired
    private List<OrderEntryProducer> orderEntryProducers;

    @Bean
    public BlockingQueue<OrderEntry> getOrdersQueue() {
        return new LinkedBlockingQueue<OrderEntry>(100);
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean("orderEntryProducers")
    public Map<String, OrderEntryProducer> orderEntryProducers() {
        return orderEntryProducers.stream().collect(Collectors.toMap(OrderEntryProducer::getType, Function.identity()));
    }
}
