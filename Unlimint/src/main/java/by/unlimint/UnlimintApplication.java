package by.unlimint;

import by.unlimint.model.OrderEntry;
import by.unlimint.service.Converter;
import by.unlimint.service.OrderEntryProducer;
import by.unlimint.service.PathParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Map;
import java.util.concurrent.*;

@SpringBootApplication
public class UnlimintApplication implements CommandLineRunner {

	@Value("${UnlimintApplication.numberOfProducerThreads}")
	private int numberOfProducerThreads;
	@Value("${UnlimintApplication.numberOfConverterThreads}")
	private int numberOfConverterThreads;

	private Map<String, OrderEntryProducer> orderEntryProducers;//Producer Consumer baeldun
	private BlockingQueue<OrderEntry> ordersQueue;
    private PathParser pathParser;
    private Converter converter;
	private CountDownLatch countDownLatchProducer = new CountDownLatch(numberOfProducerThreads);
	private ExecutorService exSerProducer = Executors.newFixedThreadPool(numberOfProducerThreads);
	private ExecutorService exSerConverter = Executors.newFixedThreadPool(numberOfConverterThreads);

	@Autowired
	public UnlimintApplication(@Qualifier("orderEntryProducers") Map<String, OrderEntryProducer> orderEntryProducers,
							   @Qualifier("getOrdersQueue") BlockingQueue<OrderEntry> ordersQueue,
							   PathParser pathParser,
							   Converter converter) {
//		this.countDownLatchProducer = new CountDownLatch(numberOfProducerThreads);
//		this.exSerProducer = Executors.newFixedThreadPool(numberOfProducerThreads);
//		this.exSerConverter = Executors.newFixedThreadPool(numberOfConverterThreads);
		this.orderEntryProducers = orderEntryProducers;
		this.pathParser = pathParser;
		this.converter = converter;
		this.ordersQueue = ordersQueue;
	}

	public static void main(String[] args) {
		SpringApplication.run(UnlimintApplication.class, args);
	}

	@Override//Метод вызывается после создания бинов.
	public void run(String... args) throws Exception {
		exSerConverter.execute(() -> {converter.runConverter();});

		for (String arg : args) {
			exSerProducer.execute(() -> {
				orderEntryProducers.get(pathParser.getTheFileExtensionFromThePath(arg)).readOrders(new File(arg));
				countDownLatchProducer.countDown();
			});
		}
		countDownLatchProducer.await();
		stopExecutorService(exSerProducer);
		while (!ordersQueue.isEmpty()) {}//Ждём пока очередь не опустеет.
		stopExecutorService(exSerConverter);

	}

	private void stopExecutorService(ExecutorService executorService) {
		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
	}
}
//Теория выключения с таблеткой: при опустении считываемого списка JsonProducer продолжает выпускать OrderEntry
//но поле stopMessage = false а не true (<- таблетка). Считывающие потоки закрываются из-за этой таблетки и когда
//закроются все то сработает CountDownLatch.await() и мы отдадим приказ executorService.shutdownNow().
//Сергей но это просто множество кода в котором нет никакой нужды.
