package ua.com.foxminded.formulaone.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ua.com.foxminded.formulaone.model.Racer;

public class RaceFileDataLoader implements RaceDataLoader {
    private static final DateTimeFormatter DATE_TIME_FORMATTE = DateTimeFormatter
            .ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
    private static final char DILIMETER = '_';
    private static final int RACER_REF_LENGTH = 3;
    private static final int BEG_KEY_INDEX = 0;
    private static final int END_KEY_INDEX = 3;
    private static String startFileName = "start.log";
    private static String finishFileName = "end.log";
    private static String abbreviationsFileName = "abbreviations.txt";
    
    @Override
    public Map<String, Racer> loadStatistics() throws IOException {
        Map<String, Racer> racerStatistics = initializeRacersStatistics(abbreviationsFileName);
        addTimeForRaceStatistics(racerStatistics, startFileName, finishFileName);
        return racerStatistics;
    }
    
    private File getFileFromResurces(String file) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(file);
        if (url == null) {
            throw new IllegalArgumentException("File not foud");
        } else {
            return new File(url.getFile());
        }
    }
    
    private Map<String, Racer> initializeRacersStatistics(String abbreviationsFileName) throws IOException {
        File file = getFileFromResurces(abbreviationsFileName);
        try (BufferedReader bufReader = new BufferedReader(new FileReader(file));
                Stream<String> abbreviation = bufReader.lines()) {
            return abbreviation.collect(Collectors.toMap(this::extractKey,
                    value -> new Racer(fetchRacerName(value), fetchRacerTeam(value))));
        } catch (IOException e) {
            throw new IOException("Issues while reading the data from file 'abbreviation.txt");
        }
    }
    
    private String extractKey(String abbreviations) {
        return abbreviations.substring(BEG_KEY_INDEX, END_KEY_INDEX);
    }
    
    private void addTimeForRaceStatistics(Map<String, Racer> racerStatistics, String startFileName,
            String finishFileName) throws IOException {
        File startFile = getFileFromResurces(startFileName);
        try (BufferedReader bufReader = new BufferedReader(new FileReader(startFile));
                Stream<String> startTime = bufReader.lines()) {
            startTime.forEach(value -> indicateStartTime(racerStatistics, value));
        } catch (IOException e) {
            throw new IOException("Issues while reading the data from file 'start.log");
        }
        File finishFile = getFileFromResurces(finishFileName);
        try (BufferedReader bufReader = new BufferedReader(new FileReader(finishFile));
                Stream<String> endTime = bufReader.lines()) {
            endTime.forEach(value -> indicateEndtTime(racerStatistics, value));
        } catch (IOException e) {
            throw new IOException("Issues while reading the data from file 'end.log");
        }
    }
    
    private void indicateStartTime(Map<String, Racer> racerStatistics, String startTime) {
        Racer racer = getRacer(racerStatistics, startTime);
        racer.setStartTime(fetchTime(startTime));
    }
    
    private void indicateEndtTime(Map<String, Racer> racerStatistics, String endTime) {
        Racer racer = getRacer(racerStatistics, endTime);
        racer.setFinishTime(fetchTime(endTime));
        racer.setDuration(Duration.between(racer.getStartTime(), racer.getFinishTime()));
    }
    
    private Racer getRacer(Map<String, Racer> racerStatistics, String statisticsLine) {
        return racerStatistics.get(extractKey(statisticsLine));
    }
    
    private LocalDateTime fetchTime(String time) {
        return LocalDateTime.from(DATE_TIME_FORMATTE.parse(time.substring(RACER_REF_LENGTH)));
    }
    
    private String fetchRacerName(String abbreviations) {
        int beginIndex = abbreviations.indexOf(DILIMETER);
        int endIndex = abbreviations.lastIndexOf(DILIMETER);
        return abbreviations.substring(beginIndex + 1, endIndex);
    }
    
    private String fetchRacerTeam(String abbreviations) {
        int beginIndex = abbreviations.lastIndexOf(DILIMETER);
        return abbreviations.substring(beginIndex + 1);
    }
}
