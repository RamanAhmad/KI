import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

public class AntColonyOpt {

    // Algorithmus Parameter
    /**
     * Die Konstante c bestimmt den Einfluss der Pheromonmenge auf die Wahrscheinlichkeit,
     * dass eine Ameise eine bestimmte Kante wählt.
     * Ein höherer c-Wert verstärkt den Einfluss der Pheromonspuren auf die Entscheidungsfindung der Ameisen.
     */
    private final double c;
    /**
     * Der Parameter alpha bestimmt den Einfluss der Pheromonmenge auf die Wahrscheinlichkeit,
     * dass eine Ameise eine bestimmte Kante wählt. Ein höherer alpha-Wert verstärkt den Einfluss der Pheromonspuren.
     */
    private final double alpha;
    /**
     * Der Parameter beta bestimmt den Einfluss der Sichtbarkeit (Invers der Distanz) auf die Wahrscheinlichkeit,
     * dass eine Ameise eine bestimmte Kante wählt. Ein höherer beta-Wert verstärkt den Einfluss der kürzeren Distanzen.
     */
    private final double beta;
    /**
     * Der Parameter evaporation bestimmt die Rate, mit der die Pheromonspuren auf den Kanten im Laufe der Zeit abnehmen.
     * Ein höherer Wert führt zu einer schnelleren Verdunstung, wodurch die Ameisen stärker dazu angeregt werden, neue Wege zu erkunden.
     */
    private final double evaporation;
    /**
     * Die Konstante Q bestimmt die Menge der Pheromone, die eine Ameise nach Abschluss ihrer Tour hinterlässt.
     * Ein höherer Q-Wert bedeutet, dass erfolgreiche Touren mehr Pheromone auf ihren Kanten hinterlassen,
     * was zukünftige Ameisen dazu anregt, diese Kanten häufiger zu benutzen.
     */
    private final double Q;
    /**
     * Der Parameter antFactor bestimmt den Faktor, mit dem die Anzahl der Ameisen im Algorithmus berechnet wird.
     * Ein höherer Wert führt zu einer größeren Anzahl von Ameisen.
     */
    private final double antFactor;
    /**
     * Der Parameter randomFactor reguliert den Grad der Zufälligkeit im Ameisenalgorithmus.
     * Ein höherer Wert erhöht die Wahrscheinlichkeit zufälliger Entscheidungen während der Suche.
     */
    private final double randomFactor;

    // Anzahl der Städte und Ameisen
    private final int numberOfCities;
    private final int numberOfAnts;
    // Distanzmatrix der Städte
    private final double[][] graph;
    // Pheromonmatrix
    private final double[][] trails;
    // Liste der Ameisen
    private final List<Ant> ants = new ArrayList<>();
    // Zufallsgenerator
    private final Random random = new Random();
    // Wahrscheinlichkeiten für die Wahl der nächsten Stadt
    private final double[] probabilities;

    private int currentCity;

    public int[] getBestTourOrder() {
        return bestTourOrder;
    }

    private int[] bestTourOrder;
    private double bestTourLength;

    public double getWholeDis() {
        return wholeDis;
    }

    private double wholeDis;

    public AntColonyOpt(double[][] matrix, int noOfCities, double c, double alpha, double beta,
                        double evaporation, double Q, double antFactor, double randomFactor) {
        this.c = c;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporation = evaporation;
        this.Q = Q;
        this.antFactor = antFactor;
        this.randomFactor = randomFactor;

        if (matrix != null) {
            this.graph = matrix;
        } else {
            this.graph = generateRandomMatrix(noOfCities);
        }
        numberOfCities = graph.length;
        numberOfAnts = (int) (numberOfCities * antFactor);

        // Die Pheromonspuren zwischen den Städten
        trails = new double[numberOfCities][numberOfCities];
        // Die Wahrscheinlichkeiten für die Auswahl der nächsten Stadt durch eine Ameise
        probabilities = new double[numberOfCities];
        // Initialisierung von Ameisen
        IntStream.range(0, numberOfAnts)
                .forEach(i -> ants.add(new Ant(numberOfCities)));
    }

    public double[][] generateRandomMatrix(int n) {
        double[][] randomMatrix = new double[n][n];
        IntStream.range(0, n)
                .forEach(i -> IntStream.range(0, n)
                        .forEach(j -> randomMatrix[i][j] = Math.abs(random.nextInt(100) + 1)));
        return randomMatrix;
    }

    public void startAntOptimization(int attempt) {
        System.out.println();
        for (int i = 1; i <= attempt; i++) {
            System.out.println("Das ist der Versuch: " + i);
            solve();
        }
    }

    public void solve() {

        // Initialisierung des Pheromonmatrix mit einem Anfangswert c
        clearTrails();
        setupAnts();
        moveAnts();
        // Pheromonspuren aktualisieren
        updateTrails();
        // Beste Tour aktualisieren
        updateBest();
        System.out.println("Gesamte Länge: " + (bestTourLength - numberOfCities));
        System.out.println("Beste Tourreihe: " + Arrays.toString(bestTourOrder));
        bestTourOrder.clone();
        System.out.println("=".repeat(70));
        wholeDis = bestTourLength - numberOfCities;
    }

    public void setupAnts() {
        for (Ant ant : ants) {
            // Alle vorherigen Tourdaten löschen
            ant.clear();
            // Start und die nächste Stadt (random)
            ant.visitCity(-1, random.nextInt(numberOfCities));
        }
        currentCity = 0;
    }

    private void moveAnts() {
        for (int i = currentCity; i < numberOfCities - 1; i++) {
            for (Ant ant : ants) {
                ant.visitCity(currentCity, selectNextCity(ant));
            }
            currentCity++;
        }
    }

    private int selectNextCity(Ant ant) {
        int t = random.nextInt(numberOfCities - currentCity);
        if (random.nextDouble() < randomFactor) {
            OptionalInt cityIndex = IntStream.range(0, numberOfCities)
                    .filter(i -> i == t && !ant.visited(i))
                    .findFirst();
            if (cityIndex.isPresent()) {
                return cityIndex.getAsInt();
            }
        }
        calculateProbabilities(ant);
        double r = random.nextDouble();
        double total = 0;
        for (int i = 0; i < numberOfCities; i++) {
            total += probabilities[i];
            if (total >= r) {
                return i;
            }
        }

        throw new RuntimeException("There are no other cities");
    }

    /**
     * Die Wahrscheinlichkeiten für eine bestimmte Ameise berechnen
     */
    public void calculateProbabilities(Ant ant) {
        // Die aktuelle Stadt, die die Ameise besucht, wird ermittelt
        int i = ant.trail[currentCity];
        double pheromone = 0.0;
        // Die Summe der Pheromonwerte für die verbleibenden unbesuchten Städte wird berechnet
        for (int l = 0; l < numberOfCities; l++) {
            if (!ant.visited(l)) {
                pheromone += Math.pow(trails[i][l], alpha) * Math.pow(1.0 / graph[i][l], beta);
            }
        }
        // Die Wahrscheinlichkeiten für jede verbleibende unbesuchte Stadt werden berechnet
        for (int j = 0; j < numberOfCities; j++) {
            if (ant.visited(j)) {
                probabilities[j] = 0.0;
            } else {
                double numerator = Math.pow(trails[i][j], alpha) * Math.pow(1.0 / graph[i][j], beta);
                probabilities[j] = numerator / pheromone;
            }
        }
    }

    private void updateTrails() {
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                trails[i][j] *= evaporation;
            }
        }
        for (Ant a : ants) {
            double contribution = Q / a.trailLength(graph);
            for (int i = 0; i < numberOfCities - 1; i++) {
                trails[a.trail[i]][a.trail[i + 1]] += contribution;
            }
            trails[a.trail[numberOfCities - 1]][a.trail[0]] += contribution;
        }
    }

    private void updateBest() {
        if (bestTourOrder == null) {
            // Die Tour der ersten Ameise in der Liste ants als beste Tour festgelegt
            bestTourOrder = ants.get(0).trail;
            bestTourLength = ants.get(0).trailLength(graph);
        }
        for (Ant a : ants) {
            if (a.trailLength(graph) < bestTourLength) {
                bestTourLength = a.trailLength(graph);
                bestTourOrder = a.trail.clone();
            }
        }
    }

    private void clearTrails() {
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                trails[i][j] = c;
            }
        }
    }
}
