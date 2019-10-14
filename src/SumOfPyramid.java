import resource.TestCases;
import utils.AccessPropertiesUtil;
import utils.Result;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static helpers.SumOfPyramidHelper.*;

/**
 * Main class of getting longest path sum of a pyramid by certain rules.
 */
public class SumOfPyramid {

    /**
     * Main method where the logic starts.
     */
    public static void main(String[] args) throws FileNotFoundException {
        int[][] pyramid = TestCases.input;
        ResourceBundle bundle = ResourceBundle.getBundle(AccessPropertiesUtil.PROPERTIES_PATH.getPropertyAccess());
        validatePyramid(bundle, pyramid);
        System.out.println(bundle.getString(AccessPropertiesUtil.ASSUMPTION.getPropertyAccess()));
        Result result = findMaximumSumPath(pyramid);
        if (result.getSumValue() == 0) {
            System.out.println(bundle.getString(AccessPropertiesUtil.WRONG_INPUT.getPropertyAccess()));
        } else {
            System.out.println(bundle.getString(AccessPropertiesUtil.RESULT.getPropertyAccess()) + result.getSumValue());
            System.out.println(bundle.getString(AccessPropertiesUtil.PATH.getPropertyAccess()) + result.getPaths().get(result.getSumValue()).toString().replaceAll("[\\[\\]]",""));
        }
    }

    /**
     * Method which initializes start parameters and passes them to the logic method.
     * @param pyramid 2 dimension array which contains pyramid.
     * @return Sum of maximum pyramid path.
     */
    private static Result findMaximumSumPath(int[][] pyramid) {
        int pathSum = 0;
        int i = 0;
        int j = 0;
        List<Integer> path = new ArrayList<>();
        Result result = new Result();
        result.evenFlag = true;
        findSum(pyramid, i, j, pathSum, result, path);
        return result;
    }

    /**
     * Method which recursively iterates through pyramid and finds longest path sum of a pyramid by certain rules.
     * @param pyramid 2 dimension array with pyramid information
     * @param i 2 dimension row index
     * @param j 2 dimension column index
     * @param sum value which contains sum of the path.
     * @param result object which contains result information.
     */
    private static void findSum(int[][] pyramid, int i, int j, int sum, Result result, List<Integer> path) {
        if (i >= pyramid.length) {
            modifyResult(result, sum, path);
            changeFlag(result);
            return;
        }
        int origSum = addSumToNode(sum, pyramid[i][j]);

        if (i < pyramid.length - 1) {
            if (isEven(pyramid[i + 1][j]) == result.evenFlag && isEven(pyramid[i + 1][j + 1]) == result.evenFlag) {
                modifyPath(path, i, j, pyramid);
                changeFlag(result);
                findSum(pyramid, i + 1, j, origSum, result, path);
                List<Integer> path1 = new ArrayList<>(path);

                changeFlag(result);
                findSum(pyramid, i + 1, j + 1, origSum, result, path1);
            } else if (isEven(pyramid[i + 1][j]) == result.evenFlag && isEven(pyramid[i + 1][j + 1]) != result.evenFlag) {
                modifyPath(path, i, j, pyramid);

                changeFlag(result);
                findSum(pyramid, i + 1, j, origSum, result, path);
            } else if (isEven(pyramid[i + 1][j]) != result.evenFlag && isEven(pyramid[i + 1][j + 1]) == result.evenFlag) {
                modifyPath(path, i, j, pyramid);

                changeFlag(result);
                findSum(pyramid, i + 1, j + 1, origSum, result, path);
            }
                changeFlag(result);
        }

        //change result.sumValue value if it is less then origSum on down layer of pyramid.
        if (i == pyramid.length-1) {
            modifyPath(path, i, j, pyramid);

            modifyResult(result, origSum, path);
            changeFlag(result);
        }
    }
}
