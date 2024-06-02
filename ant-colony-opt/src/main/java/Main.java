
public class Main {

    public static void main(String[] args) {
        double c = 1.0;
        double alpha = 1;
        double beta = 5;
        double evaporation = 0.5;
        double Q = 500; // Pheromon Konstante
        /*
            Erklärung zu Pheromon-Konstante
         */
        double antFactor = 0.8;
        double randomFactor = 0.01;
        int numberOfCities = 100;

        double[][] fixedMatrix = {
                {0, 9, 20, 31},
                {9, 0, 14, 7},
                {20, 14, 0, 28},
                {31, 7, 28, 0}
        };

        // Mit festen Abständen
        AntColonyOpt aco = new AntColonyOpt(fixedMatrix, numberOfCities, c, alpha, beta, evaporation, Q, antFactor, randomFactor);
        int attempts = 6;
        aco.startAntOptimization(attempts);

        // Mit randomisierten Abständen

        System.out.println("#".repeat(70));
        AntColonyOpt acoRandom = new AntColonyOpt(null, numberOfCities, c, alpha, beta, evaporation, Q, antFactor, randomFactor);
        int attemptsRandom = 6;
        acoRandom.startAntOptimization(attemptsRandom);
    }
}

