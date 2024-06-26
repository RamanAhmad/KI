import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Util {

    public static class Result {
        int cities;
        double alpha;
        double beta;
        double bestTourLength;
        int[] bestTourOrder;

        public Result(int cities, double alpha, double beta, double bestTourLength, int[] bestTourOrder) {
            this.cities = cities;
            this.alpha = alpha;
            this.beta = beta;
            this.bestTourLength = bestTourLength;
            this.bestTourOrder = bestTourOrder;
        }

        @Override
        public String toString() {
            return String.format(Locale.US, "%d,%.2f,%.2f,%.2f,%s", cities, alpha, beta, bestTourLength, Arrays.toString(bestTourOrder));
        }
    }

    public static void writeResultsToCSV(List<Result> results, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Schreiben der Spaltenüberschriften
            writer.write("Cities,Alpha,Beta,Best Tour Length,Best Tour Order\n");
            // Schreiben der Ergebnisse
            for (Result result : results) {
                writer.write(result.toString() + "\n");
            }
            System.out.println("Die Ergebnisse wurden erfolgreich in die Datei " + filename + " geschrieben.");
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben der Datei " + filename + ": " + e.getMessage());
        }
    }
}
