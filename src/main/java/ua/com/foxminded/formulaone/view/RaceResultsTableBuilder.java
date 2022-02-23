package ua.com.foxminded.formulaone.view;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ua.com.foxminded.formulaone.controller.RaceDataLoader;
import ua.com.foxminded.formulaone.model.Racer;

public class RaceResultsTableBuilder {
    
    private static final char DOT = '.';
    private static final String BAR = "|";
    private static final String SPACE = " ";
    private static final String DASH = "-";
    private static final String DURATION_FORMAT = "m:ss.SSS";
    private static final int DOT_LENGTH = 1;
    private static final int SPACE_LENGTH = 1;
    private static final int BAR_LENGTH = 1;
    private static final int MAX_SIZE = 15;
    private static final int MIN_LENGTH = 0;

    private RaceDataLoader loader;
    
    public RaceResultsTableBuilder(RaceDataLoader loader) {
        this.loader = loader;
    }
    
    public void checkResult(Map<String, Racer> racerStatistics) {
        if (racerStatistics == null) {
            throw new IllegalArgumentException("Result can not be Null");
        }
        if (racerStatistics.isEmpty()) {
            throw new IllegalArgumentException("Result can not be empty");
        }
    }
    
    public String raceResultsTableBuilder() throws IOException {
        Map<String, Racer> racerStatistics = loader.loadStatistics();
        checkResult(racerStatistics);
        StringBuilder result = new StringBuilder();
        List<Racer> sortedRacers = racerStatistics.values().stream()
                .sorted(Comparator.comparing(Racer::getDuration))
                .collect(Collectors.toList());
        buildRaceResult(result, sortedRacers);
        return result.toString();
    }
    
    private void buildRaceResult(StringBuilder result, List<Racer> sortedRacers) {
        for (int index = 0; index < sortedRacers.size(); index++) {
            if (index == MAX_SIZE) {
                result.append(getDashLine(sortedRacers) + System.lineSeparator());
            }
            result.append(getRacerLine(sortedRacers.get(index), index, sortedRacers) + System.lineSeparator());
        }
    }
    
    private String getRacerLine(Racer racer, int position, List<Racer> sortedRacers) {
        StringBuilder racerData = new StringBuilder()
                .append(getPositionPart(position))
                .append(getRacerPart(racer, sortedRacers))
                .append(getTeamPart(racer, sortedRacers))
                .append(getLapTime(racer));
        return racerData.toString();
    }
    
    private String getPositionPart(int position) {
        StringBuilder positionPart = new StringBuilder()
                .append(position + 1)
                .append(DOT);
        if (((position + 1) / 10) > 0) {
            positionPart.append(SPACE);
        } else {
            positionPart.append(SPACE + SPACE);
        }
        return positionPart.toString();
    }
    
    private String getDashLine(List<Racer> sortedRacers) {
        int positionPartLength = getPositionLength(sortedRacers);
        int racerPartLength = getMaxDriverNameLength(sortedRacers) + BAR_LENGTH;
        int teamPartLength = getMaxTeamNameLength(sortedRacers) + BAR_LENGTH;
        int durationPartLength = DURATION_FORMAT.length();
        int dashLileLength = positionPartLength + racerPartLength + teamPartLength + durationPartLength;
        return addSymbolsToString(DASH, dashLileLength, DASH);
    }
    
    private int getPositionLength(List<Racer> sortedRacers) {
        return ((int) (Math.log10(sortedRacers.size()))) + DOT_LENGTH + SPACE_LENGTH;
    }
    
    private String getRacerPart(Racer racer, List<Racer> sortedRacers) {
        return (addSymbolsToString(racer.getName(), getMaxDriverNameLength(sortedRacers), SPACE)) + (BAR);
    }
    
    private String getTeamPart(Racer racer, List<Racer> sortedRacers) {
        return (addSymbolsToString(racer.getTeam(), getMaxTeamNameLength(sortedRacers), SPACE)) + (BAR);
    }
    
    private String getLapTime(Racer racer) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DURATION_FORMAT);
        LocalTime timeLaps = LocalTime.MIDNIGHT.plus(racer.getDuration());
        return timeLaps.format(formatter);
    }

    private int getMaxDriverNameLength(List<Racer> sortedRacers) {
        return sortedRacers.stream()
                .mapToInt(racer -> racer.getName().length()).max().orElse(MIN_LENGTH);
    }
    
    private int getMaxTeamNameLength(List<Racer> sortedRacers) {
        return sortedRacers.stream()
                .mapToInt(racer -> racer.getTeam().length()).max().orElse(MIN_LENGTH);
    }
    
    private String addSymbolsToString(String expression, int totalLength, String symbol) {
        StringBuilder result = new StringBuilder(expression);
        int spaces = totalLength - expression.length();
        for (int index = 0; index < spaces; index++) {
            result.append(symbol);
        }
        return result.toString();
    }

    public RaceDataLoader getLoader() {
        return loader;
    }

    public void setLoader(RaceDataLoader loader) {
        this.loader = loader;
    }
}
