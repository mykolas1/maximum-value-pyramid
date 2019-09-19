import resource.TestCases;

import java.io.*;
import java.util.ResourceBundle;

public class SumOfPyramid {

    static class Result {
        int sumValue;
        boolean evenFlag;
    }
    public static void main(String[] args) throws FileNotFoundException {
        int[][] input = TestCases.input;
        ResourceBundle bundle = ResourceBundle.getBundle("resource/config");
        validatePyramid(bundle, input);
        System.out.println("The assumption is made that 0 (ZERO) is even number.");
        int sumOfPath = findMaximumSum(input);
        if (sumOfPath == 0) {
            System.out.println(bundle.getString("WRONG_INPUT"));
        } else
            System.out.println("Result:" + sumOfPath);
    }
    private static int findMaximumSum(int[][] a) {
        int sum = 0;
        Result result = new Result();
        result.evenFlag = true;
        findSumLogic(a, 0, 0, sum, result);
        return result.sumValue;
    }

    private static void findSumLogic(int[][] array, int i, int j, int sum, Result result) {
        if (i >= array.length) {
            changeResultSumIfLess(result, sum);
            changeFlag(result);
            return;
        }
        int origSum = addSumToNode(sum, array[i][j]);

        if (i < array.length - 1) {
            if (isEven(array[i + 1][j]) == result.evenFlag && isEven(array[i + 1][j + 1]) == result.evenFlag) {
                changeFlag(result);
                findSumLogic(array, i + 1, j, origSum, result);
                changeFlag(result);
                findSumLogic(array, i + 1, j + 1, origSum, result);
            } else if (isEven(array[i + 1][j]) == result.evenFlag && isEven(array[i + 1][j + 1]) != result.evenFlag) {
                changeFlag(result);
                findSumLogic(array, i + 1, j, origSum, result);
            } else if (isEven(array[i + 1][j]) != result.evenFlag && isEven(array[i + 1][j + 1]) == result.evenFlag) {
                changeFlag(result);
                findSumLogic(array, i + 1, j + 1, origSum, result);
            }
                changeFlag(result);
        }

        //change result.sumValue value if it is less then origSum on down layer of pyramid.
        if (i == array.length-1) {
            changeResultSumIfLess(result, origSum);
            changeFlag(result);
        }
    }

    private static void validatePyramid(ResourceBundle bundle, int[][] input) {
        if (isEven(input[0][0])) {
            System.out.println(bundle.getString("WRONG_INPUT"));
            System.exit(1);
        }
    }

    private static void changeResultSumIfLess (Result result, int sum) {
        if (result.sumValue < sum) {
            result.sumValue = sum;
        }
    }

    private static void changeFlag (Result result) {
        result.evenFlag = !result.evenFlag;
    }

    private static boolean isEven (int number) {
        return number % 2 == 0;
    }

    private static int addSumToNode (int sum, int node) {
        return sum + node;
    }
}
