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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@SpringBootApplication
public class UnlimintApplication implements CommandLineRunner {

	private Map<String, OrderEntryProducer> orderEntryProducers;
	private BlockingQueue<OrderEntry> ordersQueue;
    private PathParser pathParser;
    private Converter converter;
    private int numberOfProducerThreads;
    private int numberOfConverterThreads;
	private ExecutorService exSerProducer;
	private ExecutorService exSerConverter;

	@Autowired
	public UnlimintApplication(@Value("${UnlimintApplication.numberOfProducerThreads}") int numberOfProducerThreads,
                               @Value("${UnlimintApplication.numberOfConverterThreads}") int numberOfConverterThreads,
							   @Qualifier("orderEntryProducers") Map<String, OrderEntryProducer> orderEntryProducers,
							   @Qualifier("getOrdersQueue") BlockingQueue<OrderEntry> ordersQueue,
							   PathParser pathParser,
							   Converter converter) {
		this.orderEntryProducers = orderEntryProducers;
		this.ordersQueue = ordersQueue;
		this.pathParser = pathParser;
		this.converter = converter;
		this.numberOfProducerThreads = numberOfProducerThreads;
		this.numberOfConverterThreads = numberOfConverterThreads;
		this.exSerProducer = Executors.newFixedThreadPool(numberOfProducerThreads);
		this.exSerConverter = Executors.newFixedThreadPool(numberOfConverterThreads);
	}

	public static void main(String[] args) {
		SpringApplication.run(UnlimintApplication.class, args);
	}

	@Override
	public void run(String[] args) throws Exception {
		List<File> argsApplication = checkPath(args);
		runConsumer();
		runProducers(argsApplication);

	}

	private List<File> checkPath(String[] args) {
		List<File> filePaths = new ArrayList<>();
		for (int i = 0; i < args.length; i++) {
			File file = new File(args[i]);
			if (file.exists()) {
				filePaths.add(file);
			} else {
				System.out.println("Не найден Файл с именем " + args[i]);
			}
		}
		return filePaths;
	}

	private void runConsumer() {
		for (int i = 0; i < numberOfConverterThreads; i++) {
			exSerConverter.execute(converter);
		}
	}

	private void runProducers(List<File> argsApplication) {
		for (int i = 0; i < argsApplication.size(); i++) {
			boolean b = orderEntryProducers.containsKey(pathParser.getTheFileExtensionFromThePath(argsApplication.get(i)));
			if (!orderEntryProducers.containsKey(pathParser.getTheFileExtensionFromThePath(argsApplication.get(i)))) {
				System.out.println("Не найден класс обработчик к файлу с расширением: " + pathParser.getTheFileExtensionFromThePath(argsApplication.get(i)));
				argsApplication.remove(argsApplication.get(i));
			}
		}

		if (!argsApplication.isEmpty()) {
			CountDownLatch countDownLatchProducer = new CountDownLatch(argsApplication.size());
			for (File arg : argsApplication) {
				exSerProducer.execute(() -> {
					orderEntryProducers.get(pathParser.getTheFileExtensionFromThePath(arg)).readOrders(arg);
					countDownLatchProducer.countDown();
				});
			}
			try {
				countDownLatchProducer.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stopExecutorService(exSerProducer);
			while (!ordersQueue.isEmpty()) {}
		}
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

