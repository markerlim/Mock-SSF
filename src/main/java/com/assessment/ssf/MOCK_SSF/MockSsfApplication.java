package com.assessment.ssf.MOCK_SSF;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.assessment.ssf.MOCK_SSF.model.Event;
import com.assessment.ssf.MOCK_SSF.repo.RedisRepo;
import com.assessment.ssf.MOCK_SSF.service.DatabaseService;

@SpringBootApplication
public class MockSsfApplication implements CommandLineRunner {

	@Autowired
	private RedisRepo redisRepo;
	public static void main(String[] args) throws IOException {
		
		if (args.length > 0) {
			String fileName = args[0];
			List<Event>listofevents = DatabaseService.readFile(fileName);

			for(Event e : listofevents){
				System.out.println(e.toPrintString());
			}
		}

		SpringApplication.run(MockSsfApplication.class, args);

	}

	@Override
	public void run(String... args) throws IOException {

		if (args.length > 0) {
			String fileName = args[0];
			List<Event>listofevents = DatabaseService.readFile(fileName);

			for(Event e : listofevents){
				redisRepo.saveEvent(e);
			}
		}


	}

}
