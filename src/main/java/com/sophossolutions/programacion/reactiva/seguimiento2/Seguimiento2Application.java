package com.sophossolutions.programacion.reactiva.seguimiento2;

import com.sophossolutions.programacion.reactiva.seguimiento2.model.OrdenLocal;
import com.sophossolutions.programacion.reactiva.seguimiento2.service.KafkaProducerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class Seguimiento2Application implements CommandLineRunner {

	private final KafkaProducerService kafkaProducerService;
	private static final String TOPICO = "Pedidos-2023-08";

	public Seguimiento2Application(KafkaProducerService kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Seguimiento2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		producerData();
	}

	private void producerData(){
		OrdenLocal pedido1 = OrdenLocal.builder()
				.id(100)
				.fecha(LocalDateTime.of(2023, 8,9,12,0))
				.total(50000.00)
				.build();
		OrdenLocal pedido2 = OrdenLocal.builder()
				.id(101)
				.fecha(LocalDateTime.of(2023, 8,9,12,30))
				.total(26000.00)
				.build();
		kafkaProducerService.sendKey(TOPICO, pedido1.getId(), pedido1);
		kafkaProducerService.sendKey(TOPICO, pedido2.getId(), pedido2);
	}

}
