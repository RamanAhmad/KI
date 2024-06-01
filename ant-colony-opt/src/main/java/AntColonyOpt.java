import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

public class AntColonyOpt {

    // Algorithmus Parameter
    private final double c;
    private final double alpha;
    private final double beta;
    private final double evaporation;
    private final double Q;
    private final double antFactor;
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

    private int currentIndex;

    private int[] bestTourOrder;
    private double bestTourLength;

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

        trails = new double[numberOfCities][numberOfCities];
        probabilities = new double[numberOfCities];
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
        clearTrails();
        setupAnts();
        moveAnts();
        updateTrails();
        updateBest();
        System.out.println("Gesamte Länge: " + (bestTourLength - numberOfCities));
        System.out.println("Beste Torreihe: " + Arrays.toString(bestTourOrder));
        bestTourOrder.clone();
        System.out.println("=".repeat(70));
    }

    public void setupAnts() {
        for (Ant ant : ants) {
            ant.clear();
            ant.visitCity(-1, random.nextInt(numberOfCities));
        }
        currentIndex = 0;
    }

    private void moveAnts() {
        for (int i = currentIndex; i < numberOfCities - 1; i++) {
            for (Ant ant : ants) {
                ant.visitCity(currentIndex, selectNextCity(ant));
            }
            currentIndex++;
        }
    }

    private int selectNextCity(Ant ant) {
        int t = random.nextInt(numberOfCities - currentIndex);
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

    public void calculateProbabilities(Ant ant) {
        int i = ant.trail[currentIndex];
        double pheromone = 0.0;
        for (int l = 0; l < numberOfCities; l++) {
            if (!ant.visited(l)) {
                pheromone += Math.pow(trails[i][l], alpha) * Math.pow(1.0 / graph[i][l], beta);
            }
        }
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
