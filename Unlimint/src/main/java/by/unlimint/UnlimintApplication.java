package by.unlimint;

import by.unlimint.model.OrderEntry;
import by.unlimint.service.Converter;
import by.unlimint.service.OrderEntryProducer;
import by.unlimint.service.PathParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Map;
import java.util.concurrent.*;

@SpringBootApplication
public class UnlimintApplication implements CommandLineRunner {

//	private Map<String, OrderEntryProducer> orderEntryProducers;
////	private ExecutorService executorService = Executors.newFixedThreadPool(2);
////	private ExecutorService executorService4 = Executors.newFixedThreadPool(2);
//    private PathParser pathParser;
//    private Converter converter;

	@Autowired
	private BlockingQueue<OrderEntry> ordersQueue;

//	@Autowired
//	public UnlimintApplication(@Qualifier("orderEntryProducers") Map<String, OrderEntryProducer> orderEntryProducers, PathParser pathParser, Converter converter) {
//		this.orderEntryProducers = orderEntryProducers;
//		this.pathParser = pathParser;
//		this.converter = converter;
//	}

	public static void main(String[] args) {
		SpringApplication.run(UnlimintApplication.class, args);
	}

	@Override//Метод вызывается после создания бинов.
	public void run(String... args) throws Exception {
//		for (int i = 0; i < 4; i++) {//тут 4 потока считки, без ExecutorService
////			//consumer который ждёт объектов для распечатки
//			new Thread(converter).start();//на обработку уходит 2 секи
//		}
////		executorService4.execute(() -> {
////			converter.run();
////		});
////-------------------------------------------------------------------------------------------------
//		int e = 0;
////		CountDownLatch countDownLatch = new CountDownLatch(2);//количество файлов
////		for (String s : args) {
//		String type = pathParser.getTheFileExtensionFromThePath(args[e]);//получение расширения в зависимости от пути
//			File file = new File(args[e]); //orderEntryProducers.readFile("src/main/resources/orders.jsonl");
//			executorService.execute(() -> {//сюда передаём класс считывания
//				orderEntryProducers.get(type).readOrders(file);
////				countDownLatch.countDown();
////				orderEntryProducers.get("csv").readOrders(file);
//			});//вызвает метод readOrders
//		}
//-----------------------------------------------------------------------------------------------------
//		CountDownLatch countDownLatch = new CountDownLatch(2);
//		for (int i = 0; i < 2; i++) {
//			new Thread
//		}
	}
}
