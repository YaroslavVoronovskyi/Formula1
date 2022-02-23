package ua.com.foxminded.formulaone.controller;

import java.io.IOException;
import java.util.Map;
import ua.com.foxminded.formulaone.model.Racer;

public interface RaceDataLoader {
    public Map<String, Racer> loadStatistics() throws IOException;
    
}
