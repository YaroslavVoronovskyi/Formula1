package ua.com.foxminded.formulaone.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ua.com.foxminded.formulaone.model.Racer;
import ua.com.foxminded.formulaone.view.RaceResultsTableBuilder;

public class SimpleTest {
    @Test
    public void checkRaceResultTableBuilderToThrowException() throws IOException {    
        RaceDataLoader mockLoader = mock(RaceDataLoader.class);
        Mockito.when(mockLoader.loadStatistics()).thenReturn(createFakeRacersMapException());
        RaceResultsTableBuilder builder = new RaceResultsTableBuilder(mockLoader);
        String expectedMessage = builder.raceResultsTableBuilder();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.checkResult(createFakeRacersMapException());
        });

        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    } 
    


    private Map<String, Racer> createFakeRacersMapException() {
        Map<String, Racer> fakeRacersMap = new HashMap<>();
        Racer hamiltonRacer = new Racer("Lewis Hamilton", null);
        Racer vettelRacer = new Racer("Sebastian Vettel", null);
        Racer sainzRacer = new Racer("Carlos Sainz", null);
        
        Duration hamiltonDuration = Duration.parse("PT1M12.46S");
        Duration vettelDuration = Duration.parse("PT1M4.415S");
        Duration sainzDuration = Duration.parse("PT1M12.95S");
        
        hamiltonRacer.setDuration(hamiltonDuration);
        vettelRacer.setDuration(vettelDuration);
        sainzRacer.setDuration(sainzDuration);
        
        fakeRacersMap.put("LHM", hamiltonRacer);
        fakeRacersMap.put("SVF", vettelRacer);
        fakeRacersMap.put("CSR", sainzRacer);
        
        return fakeRacersMap;
    }
    
    @Test
    public void checkRaceResultTableBuilderToThrowException1() throws IOException {    
        RaceDataLoader mockLoader = mock(RaceDataLoader.class);
        Mockito.when(mockLoader.loadStatistics()).thenReturn(createFakeRacersMapException());
        RaceResultsTableBuilder builder = new RaceResultsTableBuilder(mockLoader);
        String expectedMessage = builder.raceResultsTableBuilder();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.checkResult(createFakeRacersMapException());
        });

        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    } 
    
    
    @Test
    public void checkRaceResultTableBuilder() throws IOException {
        RaceDataLoader mockLoader = mock(RaceDataLoader.class);
        Mockito.when(mockLoader.loadStatistics()).thenReturn(createFakeRacersMap());
        RaceResultsTableBuilder builder = new RaceResultsTableBuilder(mockLoader);
        String result = builder.raceResultsTableBuilder();
        
        assertTrue(builder.raceResultsTableBuilder()
                .contains("1.  Sebastian Vettel |FERRARI                  |1:04.415"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("2.  Daniel Ricciardo |RED BULL RACING TAG HEUER|1:12.013"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("3.  Valtteri Bottas  |MERCEDES                 |1:12.434"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("4.  Lewis Hamilton   |MERCEDES                 |1:12.460"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("5.  Stoffel Vandoorne|MCLAREN RENAULT          |1:12.463"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("6.  Kimi Raikkonen   |FERRARI                  |1:12.639"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("7.  Fernando Alonso  |MCLAREN RENAULT          |1:12.657"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("8.  Sergey Sirotkin  |WILLIAMS MERCEDES        |1:12.706"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("9.  Charles Leclerc  |SAUBER FERRARI           |1:12.829"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("10. Sergio Perez     |FORCE INDIA MERCEDES     |1:12.848"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("11. Romain Grosjean  |HAAS FERRARI             |1:12.930"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("12. Pierre Gasly     |SCUDERIA TORO ROSSO HONDA|1:12.941"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("13. Carlos Sainz     |RENAULT                  |1:12.950"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("14. Esteban Ocon     |FORCE INDIA MERCEDES     |1:13.028"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("15. Nico Hulkenberg  |RENAULT                  |1:13.065"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("16. Brendon Hartley  |SCUDERIA TORO ROSSO HONDA|1:13.179"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("17. Marcus Ericsson  |SAUBER FERRARI           |1:13.265"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("18. Lance Stroll     |WILLIAMS MERCEDES        |1:13.323"));
        assertTrue(builder.raceResultsTableBuilder()
                .contains("19. Kevin Magnussen  |HAAS FERRARI             |1:13.393"));
        assertFalse(result.contains("20."));
        assertNotNull(result);
    }
    
    private Map<String, Racer> createFakeRacersMap() throws IOException {
        Map<String, Racer> fakeRacersMap = new HashMap<>();
        Racer hamiltonRacer = new Racer("Lewis Hamilton", "MERCEDES");
        Racer vettelRacer = new Racer("Sebastian Vettel", "FERRARI");
        Racer sainzRacer = new Racer("Carlos Sainz", "RENAULT");
        Racer bottasRacer = new Racer("Valtteri Bottas", "MERCEDES");
        Racer ricciardoRacer = new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER");
        Racer magnussenRacer = new Racer("Kevin Magnussen", "HAAS FERRARI");
        Racer perezRacer = new Racer("Sergio Perez", "FORCE INDIA MERCEDES");
        Racer leclercRacer = new Racer("Charles Leclerc", "SAUBER FERRARI");
        Racer vandoorneRacer = new Racer("Stoffel Vandoorne", "MCLAREN RENAULT");
        Racer harleyRacer = new Racer("Brendon Hartley", "SCUDERIA TORO ROSSO HONDA");
        Racer strollRacer = new Racer("Lance Stroll", "WILLIAMS MERCEDES");
        Racer grosjeanRacer = new Racer("Romain Grosjean", "HAAS FERRARI");
        Racer alonsoRacer = new Racer("Fernando Alonso", "MCLAREN RENAULT");
        Racer sirotkinRacer = new Racer("Sergey Sirotkin", "WILLIAMS MERCEDES");
        Racer hulkenbergRacer = new Racer("Nico Hulkenberg", "RENAULT");
        Racer ericssonRacer = new Racer("Marcus Ericsson", "SAUBER FERRARI");
        Racer gaslyRacer = new Racer("Pierre Gasly", "SCUDERIA TORO ROSSO HONDA");
        Racer oconRacer = new Racer("Esteban Ocon", "FORCE INDIA MERCEDES");
        Racer raikkonenRacer = new Racer("Kimi Raikkonen", "FERRARI");
        
        Duration hamiltonDuration = Duration.parse("PT1M12.46S");
        Duration vettelDuration = Duration.parse("PT1M4.415S");
        Duration sainzDuration = Duration.parse("PT1M12.95S");
        Duration bottasDuration = Duration.parse("PT1M12.434S");
        Duration ricciardoDuration = Duration.parse("PT1M12.013S");
        Duration magnussenDuration = Duration.parse("PT1M13.393S");
        Duration perezDuration = Duration.parse("PT1M12.848S");
        Duration leclercDuration = Duration.parse("PT1M12.829S");
        Duration vandoorneDuration = Duration.parse("PT1M12.463S");
        Duration harleyDuration = Duration.parse("PT1M13.179S");
        Duration strollDuration = Duration.parse("PT1M13.323S");
        Duration grosjeanDuration = Duration.parse("PT1M12.93S");
        Duration alonsoDuration = Duration.parse("PT1M12.657S");
        Duration sirotkiDuration = Duration.parse("PT1M12.706S");
        Duration hulkenberDuration = Duration.parse("PT1M13.065S");
        Duration ericssonDuration = Duration.parse("PT1M13.265S");
        Duration gaslyDuration = Duration.parse("PT1M12.941S");
        Duration oconDuration = Duration.parse("PT1M13.028S");
        Duration raikkonenDuration = Duration.parse("PT1M12.639S");
        
        hamiltonRacer.setDuration(hamiltonDuration);
        vettelRacer.setDuration(vettelDuration);
        sainzRacer.setDuration(sainzDuration);
        bottasRacer.setDuration(bottasDuration);
        ricciardoRacer.setDuration(ricciardoDuration);
        magnussenRacer.setDuration(magnussenDuration);
        perezRacer.setDuration(perezDuration);
        leclercRacer.setDuration(leclercDuration);
        vandoorneRacer.setDuration(vandoorneDuration);
        harleyRacer.setDuration(harleyDuration);
        strollRacer.setDuration(strollDuration);
        grosjeanRacer.setDuration(grosjeanDuration);
        alonsoRacer.setDuration(alonsoDuration);
        sirotkinRacer.setDuration(sirotkiDuration);
        hulkenbergRacer.setDuration(hulkenberDuration);
        ericssonRacer.setDuration(ericssonDuration);
        gaslyRacer.setDuration(gaslyDuration);
        oconRacer.setDuration(oconDuration);
        raikkonenRacer.setDuration(raikkonenDuration);
        
        fakeRacersMap.put("LHM", hamiltonRacer);
        fakeRacersMap.put("SVF", vettelRacer);
        fakeRacersMap.put("CSR", sainzRacer);
        fakeRacersMap.put("VBM", bottasRacer);
        fakeRacersMap.put("DRR", ricciardoRacer);
        fakeRacersMap.put("KMH", magnussenRacer);
        fakeRacersMap.put("SPF", perezRacer);
        fakeRacersMap.put("CLS", leclercRacer);
        fakeRacersMap.put("SVM", vandoorneRacer);
        fakeRacersMap.put("BHS", harleyRacer);
        fakeRacersMap.put("LSW", strollRacer);
        fakeRacersMap.put("RGH", grosjeanRacer);
        fakeRacersMap.put("FAM", alonsoRacer);
        fakeRacersMap.put("SSW", sirotkinRacer);
        fakeRacersMap.put("NHR", hulkenbergRacer);
        fakeRacersMap.put("MES", ericssonRacer);
        fakeRacersMap.put("PGS", gaslyRacer);
        fakeRacersMap.put("EOF", oconRacer);
        fakeRacersMap.put("KRF", raikkonenRacer);
        
        return fakeRacersMap;
    }
    
}
