package helpers;

import utils.AccessPropertiesUtil;
import utils.Result;

import java.util.*;

/**
 * Helper class which contains logic methods.
 */
public class SumOfPyramidHelper {

    /**
     * Checks is number is even.
     * @param number
     * @return true/false
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Changes result object flag.
     * @param result result object which stores flag information.
     */
    public static void changeFlag (Result result) {
        result.evenFlag = !result.evenFlag;
    }

    /**
     * Adds two elements.
     * @param sum
     * @param node
     * @return composition of nodes sum with next value.
     */
    public static int addSumToNode (int sum, int node) {
        return sum + node;
    }

    /**
     * Modifiess result object's maximum sum variable. Also saves each path with it's value.
     * @param result result object.
     * @param sum maximum path sum.
     * @param path path of maximum sum.
     */
    public static void modifyResult (Result result, int sum, List<Integer> path) {
        if (result.sumValue < sum) {
            result.sumValue = sum;
            result.getPaths().put(result.sumValue, path);
        }
    }

    /**
     * Validates if top element of pyramid is even.
     * @param bundle
     * @param input
     */
    public static void validatePyramid(ResourceBundle bundle, int[][] input) {
        if (isEven(input[0][0])) {
            System.out.println(bundle.getString(AccessPropertiesUtil.WRONG_INPUT.getPropertyAccess()));
            System.exit(1);
        }
    }

    /**
     * Modifies path or adds elements to it.
     * @param path existing path.
     * @param rowIterator row iterator and path element index.
     * @param columnIterator column iterator.
     * @param pyramid pyramid 2d array.
     */
    public static void modifyPath(List<Integer> path, int rowIterator, int columnIterator, int[][] pyramid) {
        if (path.size() > rowIterator) {
            path.set(rowIterator, pyramid[rowIterator][columnIterator]);
        } else {
            path.add(pyramid[rowIterator][columnIterator]);
        }
    }

}
