

public class Main {

    public static void main(String[] args) {
        double[][] fixedMatrix = {
                {0, 29, 20, 21},
                {29, 0, 15, 17},
                {20, 15, 0, 28},
                {21, 17, 28, 0}
        };
        AntColonyOpt acoWithRandomMatrix = new AntColonyOpt(null, 21);
        AntColonyOpt actWithFixedMatrix = new AntColonyOpt(fixedMatrix, 0);
        actWithFixedMatrix.setupAnts();
        acoWithRandomMatrix.setupAnts();
        acoWithRandomMatrix.startAntOptimization(2);
        actWithFixedMatrix.startAntOptimization(2);
    }
}