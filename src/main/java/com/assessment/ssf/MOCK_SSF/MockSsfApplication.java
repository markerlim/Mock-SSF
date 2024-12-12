package com.assessment.ssf.MOCK_SSF;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.assessment.ssf.MOCK_SSF.model.Event;
import com.assessment.ssf.MOCK_SSF.repo.RedisRepo;
import com.assessment.ssf.MOCK_SSF.service.DatabaseService;

@SpringBootApplication
public class MockSsfApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MockSsfApplication.class);

    @Autowired
    private RedisRepo redisRepo;

    public static void main(String[] args) throws IOException {
        // Initialize the application
        logger.info("Starting application...");

        if (args.length > 0) {
            String fileName = args[0];
            logger.info("Reading events from file: {}", fileName);

            try {
                List<Event> listOfEvents = DatabaseService.readFile(fileName);
                for (Event e : listOfEvents) {
                    logger.debug("Event read from file: {}", e.toPrintString());
                }
            } catch (IOException e) {
                logger.error("Error reading file: {}", fileName, e);
                throw e;  // Rethrow the exception
            }
        } else {
            logger.warn("No file argument provided, using default: /app/events.json");
        }

        SpringApplication.run(MockSsfApplication.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        logger.info("Running CommandLineRunner...");

        if (args.length > 0) {
            String fileName = args[0];
            logger.info("Reading events from file: {}", fileName);

            try {
                List<Event> listOfEvents = DatabaseService.readFile(fileName);
                logger.info("Total events read: {}", listOfEvents.size());
                for (Event e : listOfEvents) {
                    logger.debug("Saving event to Redis: {}", e.toPrintString());
                    redisRepo.saveEvent(e);
                }
            } catch (IOException e) {
                logger.error("Error reading file: {}", fileName, e);
                throw e;  // Rethrow the exception
            }
        } else {
            logger.warn("No file argument provided, using default: /app/events.json");
        }
    }
}
