import java.util.ArrayList;
import java.util.List;

public class CI3 {

    public static void main(String args[]) {
        System.out.println("-- start --");
        List<Integer> result = getDuplicateNumber(new int[]{2, 3, 1, 0, 2, 5, 3});
        if (result != null) {
            result.forEach(System.out::println);
        }
        System.out.println("-- end --");

        int oneDuplicateNumber = getOneDuplicateNumber(new int[]{2, 3, 5, 4, 3, 2, 6, 7});
        System.out.println("{2, 3, 5, 4, 3, 2, 6, 7} oneDuplicateNumber = " + oneDuplicateNumber);


        int oneDuplicateNumber2 = getOneDuplicateNumber(new int[]{2, 2, 5, 4, 3, 2, 6, 7});
        System.out.println("{2, 2, 5, 4, 3, 2, 6, 7} oneDuplicateNumber = " + oneDuplicateNumber2);


        int oneDuplicateNumber3 = getOneDuplicateNumber(new int[]{1, 2, 3, 2});
        System.out.println("{1, 2, 3, 2} oneDuplicateNumber = " + oneDuplicateNumber3);


        int oneDuplicateNumber4 = getOneDuplicateNumber(new int[]{1, 2});
        System.out.println("{1,2} oneDuplicateNumber = " + oneDuplicateNumber4);

        int oneDuplicateNumber5 = getOneDuplicateNumber(new int[]{1, 2, 3, 4});
        System.out.println("{1,2，3，4} oneDuplicateNumber = " + oneDuplicateNumber5);

        int oneDuplicateNumber6 = getOneDuplicateNumber(new int[]{2, 3, 5, 4, 3, 2, 6});
        System.out.println("{2, 3, 5, 4, 3, 2, 6} oneDuplicateNumber = " + oneDuplicateNumber6);

    }


    private static List<Integer> getDuplicateNumber(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return null;
        }
        int length = inputArray.length;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            while (inputArray[i] != i) {
                if (inputArray[inputArray[i]] == inputArray[i]) {
                    result.add(inputArray[i]);
                    break;
                } else {
                    int temp = inputArray[i];
                    inputArray[i] = inputArray[temp];
                    inputArray[temp] = temp;
                }
            }
        }
        return result;
    }


    private static int getOneDuplicateNumber(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0 || inputArray.length == 1) {
            return -1;
        }

        int length = inputArray.length;
        int start = 1;
        int end = length - 1;

        while (end >= start) {
            int middle = (start + end) / 2;
            int countInRange = countRange(inputArray, length, start, middle);
            //分两种情况 有可能最后两个指针重合，也有可能最后两个指针相差1
            if (end == start) {
                if (countInRange > 1) {
                    return start;
                } else {
                    break;
                }
            } else if (end == start + 1) {
                if (countInRange > 1) {
                    return start;
                } else if (countRange(inputArray, length, end, end) > 1) {
                    return end;
                } else {
                    break;
                }
            }

            //二分查找，折半缩小范围
            if (countInRange <= middle) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }
        return -1;
    }

    private static int countRange(int[] inputArray, int length, int start, int end) {
        if (inputArray == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (inputArray[i] >= start && inputArray[i] <= end) {
                count++;
            }
        }
        return count;
    }

}
