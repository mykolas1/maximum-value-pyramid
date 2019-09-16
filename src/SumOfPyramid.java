import java.io.*;

public class SumOfPyramid {

    private static final String WRONG_INPUT = "Input array does not have a path to down layer.";

    private static final int[][] input = new int[][]{
            { 215, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 192, 124, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 117, 269, 442, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 218, 836, 347, 235, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 320, 805, 522, 417, 345, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 229, 601, 728, 835, 133, 124, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 248, 202, 277, 433, 207, 263, 257, 0, 0, 0, 0, 0, 0, 0, 0},
            { 359, 464, 504, 528, 516, 716, 871, 182, 0, 0, 0, 0, 0, 0, 0},
            { 461, 441, 426, 656, 863, 560, 380, 171, 923, 0, 0, 0, 0, 0, 0},
            { 381, 348, 573, 533, 448, 632, 387, 176, 975, 449, 0, 0, 0, 0, 0},
            { 223, 711, 445, 645, 245, 543, 931, 532, 937, 541, 444, 0, 0, 0, 0},
            { 330, 131, 333, 928, 376, 733,  17, 778, 839, 168, 197, 197, 0, 0, 0},
            { 131, 171, 522, 137, 217, 224, 291, 413, 528, 520, 227, 229, 928, 0, 0},
            { 223, 626,  34, 683, 839,  52, 627, 310, 713, 999, 629, 817, 410, 121, 0},
            { 924, 622, 911, 233, 325, 139, 721, 218, 253, 223, 107, 233, 230, 124, 233}
    };

    static class Result {
        int sumValue;
        boolean evenFlag;
    }
    public static void main(String[] args) throws FileNotFoundException {

        int sumOfPath = findMaximumSum(input);
        if (sumOfPath == 0) {
            System.out.println(WRONG_INPUT);
        } else
            System.out.println(sumOfPath);
    }
    private static int findMaximumSum(int[][] a) {
        int sum = 0;
        Result result = new Result();
        result.evenFlag = true;
        findSumLogic(a, 0, 0, sum, result);
        return result.sumValue;
    }

    private static void findSumLogic(int[][] a, int i, int j, int sum, Result result) {
        if (i >= a.length) {
            changeResultSumIfLess(result, sum);
            return;
        }
        int origSum = addSumToNode(sum, a[i][j]);

        if (i < a.length - 1) {
            if (isEven(input[i + 1][j]) == result.evenFlag && isEven(input[i + 1][j + 1]) == result.evenFlag) {
                changeFlag(result);
                findSumLogic(a, i + 1, j, origSum, result);
                changeFlag(result);
                findSumLogic(a, i + 1, j + 1, origSum, result);
                changeFlag(result);
            } else if (isEven(input[i + 1][j]) == result.evenFlag && isEven(input[i + 1][j + 1]) != result.evenFlag) {
                changeFlag(result);
                findSumLogic(a, i + 1, j, origSum, result);
            } else if (isEven(input[i + 1][j]) != result.evenFlag && isEven(input[i + 1][j + 1]) == result.evenFlag) {
                changeFlag(result);
                findSumLogic(a, i + 1, j + 1, origSum, result);
            }
        }

        //change result.sumValue value if it is less then origSum on down layer of pyramid.
        if (i == a.length-1) {
            changeResultSumIfLess(result, origSum);
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
