package ua.com.foxminded.formulaone.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import ua.com.foxminded.formulaone.controller.RaceFileDataLoader;
import ua.com.foxminded.formulaone.model.Racer;

public class RaceFileDataLoaderTest {
    
    RaceFileDataLoader raceFileDataLoader = new RaceFileDataLoader();
    
    @Test
    void loadStatisticsShouldReturnExpectedNumberOfRacers() throws IOException {
        Map<String, Racer> racers = raceFileDataLoader.loadStatistics();
        int expect = 19;
        int actual = racers.size();
        assertEquals(expect, actual);
    }
    
    @Test
    void loadStatisticsShouldReturnExpectedRacers() throws IOException {
        Map<String, Racer> racers = raceFileDataLoader.loadStatistics();
        Racer hamiltonRacer = new Racer("Lewis Hamilton", "MERCEDES");
        Racer vettelRacer = new Racer("Sebastian Vettel", "FERRARI");
        Racer sainzRacer = new Racer("Carlos Sainz", "RENAULT");
        
        LocalDateTime hamiltonStartTime = LocalDateTime.parse("2018-05-24T12:18:20.125");
        LocalDateTime hamiltonEndtTime = LocalDateTime.parse("2018-05-24T12:19:32.585");
        LocalDateTime vettelStartTime = LocalDateTime.parse("2018-05-24T12:02:58.917");
        LocalDateTime vettelEndtTime = LocalDateTime.parse("2018-05-24T12:04:03.332");
        LocalDateTime sainzStartTime = LocalDateTime.parse("2018-05-24T12:03:15.145");
        LocalDateTime sainzEndtTime = LocalDateTime.parse("2018-05-24T12:04:28.095");
        
        Duration hamiltonDuration = Duration.parse("PT1M12.46S");
        Duration vettelDuration = Duration.parse("PT1M4.415S");
        Duration sainzDuration = Duration.parse("PT1M12.95S");
        
        hamiltonRacer.setStartTime(hamiltonStartTime);
        hamiltonRacer.setFinishTime(hamiltonEndtTime);
        vettelRacer.setStartTime(vettelStartTime);
        vettelRacer.setFinishTime(vettelEndtTime);
        sainzRacer.setStartTime(sainzStartTime);
        sainzRacer.setFinishTime(sainzEndtTime);
        
        hamiltonRacer.setDuration(hamiltonDuration);
        vettelRacer.setDuration(vettelDuration);
        sainzRacer.setDuration(sainzDuration);
        
        assertTrue(racers.containsValue(hamiltonRacer));
        assertTrue(racers.containsValue(vettelRacer));
        assertTrue(racers.containsValue(sainzRacer));
    }
    
    @Test
    void loadStatisticsShouldReturnExpectedRacers1() throws IOException {
        Map<String, Racer> racers = raceFileDataLoader.loadStatistics();
        for (Map.Entry<String, Racer> racer : racers.entrySet()) {
            System.out.println(racer.getKey() + " " + racer.getValue());
        }
    }
}
