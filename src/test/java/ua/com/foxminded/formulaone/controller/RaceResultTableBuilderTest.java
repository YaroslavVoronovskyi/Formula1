package ua.com.foxminded.formulaone.controller;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import ua.com.foxminded.formulaone.model.Racer;
import ua.com.foxminded.formulaone.view.RaceResultsTableBuilder;

public class RaceResultTableBuilderTest {    
    @Test
    public void checkRaceResultTableBuilderForEmpty() throws IOException {
        RaceDataLoader mockLoader = mock(RaceDataLoader.class);
        Mockito.when(mockLoader.loadStatistics()).thenReturn(Collections.emptyMap());
        RaceResultsTableBuilder loader = new RaceResultsTableBuilder(mockLoader);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> loader.raceResultsTableBuilder());
        assertEquals("Result can not be empty", exception.getMessage());
    }
    
    @Test
    public void checkRaceResultTableBuilderForNull() throws IOException {
        RaceDataLoader mockLoader = mock(RaceDataLoader.class);
        Mockito.when(mockLoader.loadStatistics()).thenReturn(null);
        RaceResultsTableBuilder loader = new RaceResultsTableBuilder(mockLoader);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> loader.raceResultsTableBuilder());
        assertEquals("Result can not be Null", exception.getMessage());
    }
    
    @Test
    public void checkRaceResultTableBuilder() throws IOException {    
        RaceDataLoader mockLoader = mock(RaceDataLoader.class);
        Mockito.when(mockLoader.loadStatistics()).thenReturn(createFakeRacersMap());
        RaceResultsTableBuilder builder = new RaceResultsTableBuilder(mockLoader);
        String result = builder.raceResultsTableBuilder();
        
        assertEquals("1.  Sebastian Vettel |FERRARI                  |1:04.415" + System.lineSeparator() + 
                     "2.  Daniel Ricciardo |RED BULL RACING TAG HEUER|1:12.013" + System.lineSeparator() + 
                     "3.  Valtteri Bottas  |MERCEDES                 |1:12.434" + System.lineSeparator() +
                     "4.  Lewis Hamilton   |MERCEDES                 |1:12.460" + System.lineSeparator() +
                     "5.  Stoffel Vandoorne|MCLAREN RENAULT          |1:12.463" + System.lineSeparator() +
                     "6.  Kimi Raikkonen   |FERRARI                  |1:12.639" + System.lineSeparator() +
                     "7.  Fernando Alonso  |MCLAREN RENAULT          |1:12.657" + System.lineSeparator() +
                     "8.  Sergey Sirotkin  |WILLIAMS MERCEDES        |1:12.706" + System.lineSeparator() +
                     "9.  Charles Leclerc  |SAUBER FERRARI           |1:12.829" + System.lineSeparator() +
                     "10. Sergio Perez     |FORCE INDIA MERCEDES     |1:12.848" + System.lineSeparator() +
                     "11. Romain Grosjean  |HAAS FERRARI             |1:12.930" + System.lineSeparator() +
                     "12. Pierre Gasly     |SCUDERIA TORO ROSSO HONDA|1:12.941" + System.lineSeparator() +
                     "13. Carlos Sainz     |RENAULT                  |1:12.950" + System.lineSeparator() +
                     "14. Esteban Ocon     |FORCE INDIA MERCEDES     |1:13.028" + System.lineSeparator() +
                     "15. Nico Hulkenberg  |RENAULT                  |1:13.065" + System.lineSeparator() +
                     "-------------------------------------------------------"  + System.lineSeparator() +
                     "16. Brendon Hartley  |SCUDERIA TORO ROSSO HONDA|1:13.179" + System.lineSeparator() +
                     "17. Marcus Ericsson  |SAUBER FERRARI           |1:13.265" + System.lineSeparator() +
                     "18. Lance Stroll     |WILLIAMS MERCEDES        |1:13.323" + System.lineSeparator() +
                     "19. Kevin Magnussen  |HAAS FERRARI             |1:13.393" + System.lineSeparator(), result);
    }
    
    private Map<String, Racer> createFakeRacersMap() throws IOException {
        Map<String, Racer> fakeRacersMap = new HashMap<>();
        Racer hamiltonRacer = new Racer("Lewis Hamilton", "MERCEDES");
        Racer vettelRacer = new Racer("Sebastian Vettel", "FERRARI");
        Racer sainzRacer = new Racer("Carlos Sainz", "RENAULT");
        Racer bottasRacer = new Racer ("Valtteri Bottas", "MERCEDES");
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
