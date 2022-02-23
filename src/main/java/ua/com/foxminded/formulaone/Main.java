package ua.com.foxminded.formulaone;

import java.io.FileNotFoundException;
import java.io.IOException;

import ua.com.foxminded.formulaone.controller.RaceFileDataLoader;
import ua.com.foxminded.formulaone.view.RaceResultsTableBuilder;

public class Main {
    public static void main(String[] args) {
        try {
            RaceFileDataLoader fileLoader = new RaceFileDataLoader();
            RaceResultsTableBuilder raceResult = new RaceResultsTableBuilder(fileLoader);
            System.out.println(raceResult.raceResultsTableBuilder());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }  
}
