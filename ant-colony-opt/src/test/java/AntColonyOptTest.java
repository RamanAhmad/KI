
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class AntColonyOptTest {


    @Test
    public void test_alpha_and_beta() {
        test_alpha_and_beta_by_adjusting_cities_number(10);
//        test_alpha_and_beta_by_adjusting_cities_number(100);
//        test_alpha_and_beta_by_adjusting_cities_number(1000);
//
//        Util.writeResultsToCSV(results, "results.csv");
    }

    @Test
    public void test_act_alpha_and_beta_with_zero() {
        test_act_static(10, 0, 0);
        test_act_static(100, 1, 0);
    }

    @Test
    public void test_act_alpha_and_beta_with_big_numbers() {
        test_act_static(100, 500, 100);
        test_act_static(100, 20, 100);
    }
    double[][] matrix = {
            {0, 1, 2},
            {1, 0, 3},
            {2, 3, 0}
    };

    @Test
    public void test_fixedMatrix_C() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.C, 1.0, matrix);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.C, 2, matrix);
        antColonyOpt1.startAntOptimization(attempt);
    }

    @Test
    public void test_randomMatrix_C() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.C, 1.0, null);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.C, 2, null);
        antColonyOpt1.startAntOptimization(attempt);
    }

    @Test
    public void test_fixedMatrix_alpha() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.ALPHA, 1.0, matrix);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.ALPHA, 100, matrix);
        antColonyOpt1.startAntOptimization(attempt);
    }

    @Test
    public void test_randomMatrix_alpha() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.ALPHA, 1.0, null);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.ALPHA, 100, null);
        antColonyOpt1.startAntOptimization(attempt);
    }

    @Test
    public void test_fixedMatrix_beta() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.BETA, 5.0, matrix);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.BETA, 10.0, matrix);
        antColonyOpt1.startAntOptimization(attempt);
    }

    @Test
    public void test_randomMatrix_beta() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.BETA, 5.0, null);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.BETA, 10.0, null);
        antColonyOpt1.startAntOptimization(attempt);
    }

    @Test
    public void test_fixedMatrix_eva() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.EVA, 0.5, matrix);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.EVA, 0.3, matrix);
        antColonyOpt1.startAntOptimization(attempt);
    }

    @Test
    public void test_randomMatrix_eva() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.EVA, 0.5, null);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.EVA, 0.3, null);
        antColonyOpt1.startAntOptimization(attempt);
    }

    @Test
    public void test_fixedMatrix_q() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.Q, 500.0, matrix);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.Q, 1000.0, matrix);
        antColonyOpt1.startAntOptimization(attempt);
    }

    @Test
    public void test_randomMatrix_q() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.Q, 500.0, null);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.Q, 1000.0, null);
        antColonyOpt1.startAntOptimization(attempt);
    }

    @Test
    public void test_fixedMatrix_antFactor() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.ANT_FACTOR, 0.8, matrix);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.ANT_FACTOR, 1.0, matrix);
        antColonyOpt1.startAntOptimization(attempt);
    }

    @Test
    public void test_randomMatrix_antFactor() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.ANT_FACTOR, 0.8, null);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.ANT_FACTOR, 1.0, null);
        antColonyOpt1.startAntOptimization(attempt);
    }

    @Test
    public void test_fixedMatrix_randomFactor() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.RANDOM_FACTOR, 0.01, matrix);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.RANDOM_FACTOR, 0.05, matrix);
        antColonyOpt1.startAntOptimization(attempt);
    }

    @Test
    public void test_randomMatrix_randomFactor() {
        int attempt = 1;
        AntColonyOpt antColonyOpt = coreAntColonyOpt(ParamsOpt.RANDOM_FACTOR, 0.01, null);
        antColonyOpt.startAntOptimization(attempt);

        AntColonyOpt antColonyOpt1 = coreAntColonyOpt(ParamsOpt.RANDOM_FACTOR, 0.05, null);
        antColonyOpt1.startAntOptimization(attempt);
    }

    private void test_act_static(int cities, double alpha, double beta) {
        List<Util.Result> listOfAttempt = new ArrayList<>();
        Util.Result bestResult = null;
        System.out.println("Testen von Städten, Alpha und Beta:");
        AntColonyOpt aco = testAntColonyOptPheromon(null, cities, alpha, beta);
        aco.startAntOptimization(1);

        int[] bestTourOrder = aco.getBestTourOrder();
        Util.Result result = new Util.Result(cities, alpha, beta, aco.getWholeDis(), bestTourOrder);
        listOfAttempt.add(result);
        if (bestResult == null || result.bestTourLength < bestResult.bestTourLength) {
            bestResult = result;
        }
//        for (Util.Result res : listOfAttempt) {
//            System.out.println(res);
//        } System.out.println("Cities, Alpha, Beta, Best Tour Length, Best Tour Order");
//

        // Beste Parameter ausgeben
        if (bestResult != null) {
            System.out.println("\nBeste Parameter:");
            System.out.println("Cities, Alpha, Beta, Best Tour Length, Best Tour Order");
            System.out.println(bestResult);
        }
    }



    private List<Util.Result> test_alpha_and_beta_by_adjusting_cities_number(int cities) {
        List<Util.Result> listOfAttempt = new ArrayList<>();
        Util.Result bestResult = null;
        System.out.println("Testen von Städten, Alpha und Beta:");

        // Testen von Alpha von 0 bis 100 und Beta von 99 bis 0
        for (int i = 0; i < 100; i++) {
            double alpha = i;
            double beta = 99 - i;
            AntColonyOpt aco = testAntColonyOptPheromon(null, cities, alpha, beta);
            aco.startAntOptimization(1);

            int[] bestTourOrder = aco.getBestTourOrder();
            Util.Result result = new Util.Result(cities, alpha, beta, aco.getWholeDis(), bestTourOrder);
            listOfAttempt.add(result);
            if (bestResult == null || result.bestTourLength < bestResult.bestTourLength) {
                bestResult = result;
            }
        }

        // Testen von Alpha von 99 bis 0 und Beta von 0 bis 100
        System.out.println("Testen von Alpha von 100 bis 0 und Beta von 0 bis 100:");
        for (int i = 0; i < 100; i++) {
            double alpha = 99 - i;
            double beta = i;
            AntColonyOpt aco = testAntColonyOptPheromon(null, cities, alpha, beta);
            aco.startAntOptimization(1);

            int[] bestTourOrder = aco.getBestTourOrder();
            Util.Result result = new Util.Result(cities, alpha, beta, aco.getWholeDis(), bestTourOrder);
            listOfAttempt.add(result);
            if (bestResult == null || result.bestTourLength < bestResult.bestTourLength) {
                bestResult = result;
            }
        }

        // Testen von Alpha und Beta von 0 bis 100
        System.out.println("Testen von Alpha und Beta von 0 bis 100:");
        for (int i = 0; i < 100; i++) {
            double alpha = i;
            double beta = i;
            AntColonyOpt aco = testAntColonyOptPheromon(null, cities, alpha, beta);
            aco.startAntOptimization(1);

            int[] bestTourOrder = aco.getBestTourOrder();
            Util.Result result = new Util.Result(cities, alpha, beta, aco.getWholeDis(), bestTourOrder);
            listOfAttempt.add(result);
            if (bestResult == null || result.bestTourLength < bestResult.bestTourLength) {
                bestResult = result;
            }
        }

        // Beste Parameter hinzufügen

        if (bestResult != null) {
            System.out.println("\nBeste Parameter:");
            System.out.println("Cities, Alpha, Beta, Best Tour Length, Best Tour Order");
            System.out.println(bestResult);
            listOfAttempt.add(bestResult);
        }

//        System.out.println("Cities, Alpha, Beta, Best Tour Length, Best Tour Order");
//        for (Util.Result result : listOfAttempt) {
//            System.out.println(result);
//        }

        return listOfAttempt;
    }

    enum ParamsOpt {
        C, ALPHA, BETA, EVA, Q, ANT_FACTOR, RANDOM_FACTOR, ALPHA_AND_BETA
    }

    private AntColonyOpt testAntColonyOptPheromon(double[][] matrix, int noOfCities, double alpha, double beta) {
        return new AntColonyOpt(matrix, noOfCities, 1.0, alpha, beta, 0.5, 500.0, 0.8, 0.01);
    }

    private AntColonyOpt coreAntColonyOpt(ParamsOpt paramOption, double value, double[][] matrix) {

        AntColonyOpt antColonyOpt = null;
        switch (paramOption) {
            case C:
                if (matrix == null) antColonyOpt = new AntColonyOpt(null, 3, value, 1.0, 5.0, 0.5, 500.0, 0.8, 0.01);
                else antColonyOpt = new AntColonyOpt(matrix, 3, value, 1.0, 5.0, 0.5, 500.0, 0.8, 0.01);
                break;
            case ALPHA:
                if (matrix == null) antColonyOpt = new AntColonyOpt(null, 3, 1.0, value, 5.0, 0.5, 500.0, 0.8, 0.01);
                else antColonyOpt = new AntColonyOpt(matrix, 3, 1.0, value, 5.0, 0.5, 500.0, 0.8, 0.01);
                break;
            case BETA:
                if (matrix == null) antColonyOpt = new AntColonyOpt(null, 3, 1.0, 1.0, value, 0.5, 500.0, 0.8, 0.01);
                else antColonyOpt = new AntColonyOpt(matrix, 3, 1.0, 1.0, value, 0.5, 500.0, 0.8, 0.01);
                break;
            case EVA:
                if (matrix == null) antColonyOpt = new AntColonyOpt(null, 3, 1.0, 1.0, 5.0, value, 500.0, 0.8, 0.01);
                else antColonyOpt = new AntColonyOpt(matrix, 3, 1.0, 1.0, 5.0, value, 500.0, 0.8, 0.01);
                break;
            case Q:
                if (matrix == null) antColonyOpt = new AntColonyOpt(null, 3, 1.0, 1.0, 5.0, 0.5, value, 0.8, 0.01);
                else antColonyOpt = new AntColonyOpt(matrix, 3, 1.0, 1.0, 5.0, 0.5, value, 0.8, 0.01);
                break;
            case ANT_FACTOR:
                if (matrix == null) antColonyOpt = new AntColonyOpt(null, 3, 1.0, 1.0, 5.0, 0.5, 500.0, value, 0.01);
                else antColonyOpt = new AntColonyOpt(matrix, 3, 1.0, 1.0, 5.0, 0.5, 500.0, value, 0.01);
                break;
            case RANDOM_FACTOR:
                if (matrix == null) antColonyOpt = new AntColonyOpt(null, 3, 1.0, 1.0, 5.0, 0.5, 500.0, 0.8, value);
                else antColonyOpt = new AntColonyOpt(matrix, 3, 1.0, 1.0, 5.0, 0.5, 500.0, 0.8, value);

        }
        return antColonyOpt;
    }

}
