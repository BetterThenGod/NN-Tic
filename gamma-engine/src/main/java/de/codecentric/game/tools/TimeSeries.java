package de.codecentric.game.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class TimeSeries {

    private List<Integer> gammaTimeSeries = new ArrayList<>();
    private List<Integer> randomTimeSeries = new ArrayList<>();
    private List<Integer> drawsTimeSeries = new ArrayList<>();


    public void add(int gammaWins, int randomWins, int draws) {
        gammaTimeSeries.add(gammaWins);
        randomTimeSeries.add(randomWins);
        drawsTimeSeries.add(draws);
    }

    public void write(String fileName) {
        BufferedWriter writer = null;
        try {
            File timeSeriesFile = new File(fileName);
            writer = new BufferedWriter(new FileWriter(timeSeriesFile));

            int i = 1;
            writer.write("Games:,");
            for (Integer n : gammaTimeSeries) {
                writer.write("G" + i++ + ",");
            }
            writer.newLine();

            writer.write("Gamma wins:,");
            for (Integer n : gammaTimeSeries) {
                writer.write(n + ",");
            }
            writer.newLine();

            writer.write("Randome wins:,");
            for (Integer n : randomTimeSeries) {
                writer.write(n + ",");
            }
            writer.newLine();

            writer.write("Draws:,");
            for (Integer n : drawsTimeSeries) {
                writer.write(n + ",");
            }
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
