
import org.junit.jupiter.api.Test;


public class AntColonyOptTest {

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


    enum ParamsOpt {
        C, ALPHA, BETA, EVA, Q, ANT_FACTOR, RANDOM_FACTOR
    }

    double[][] matrix = {
            {0, 1, 2},
            {1, 0, 3},
            {2, 3, 0}
    };

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
