import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class Main {

    private static final String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    public static void main(String[] args) {
        // №1
        int pow = 3;
        for (int i = 2; i < 12; i++) { // 10 iteration by condition
            byte[] arr = new byte[i];
            arr[0] = 1;
            System.out.println("for " + (int) Math.pow(2, pow++) + " bits key space is: " + new BigInteger(arr));
        }
        // №2
        pow = 3;

        for (int i = 0; i < 10; i++) {
            System.out.print("for " + (int) Math.pow(2, pow) + " bits random key is: 0x");
            for (int j = 0; j < (int) Math.pow(2, pow); j++)
                System.out.print(symbols[new Random().nextInt(symbols.length)]);
            System.out.println();
            pow++;
        }

        System.out.println(keyBruteForce("0x118E1911"));
    }


    private static StringBuilder matchingKey = new StringBuilder();
    private static long keyBruteForce(String key){
        long time = System.currentTimeMillis();
        boolean flag = false;
        matchingKey.append("0x");
        for (int i = 2; i < key.length(); i ++)
            matchingKey.append("0");

        for (int i = 2; i < matchingKey.length() - 1; i++) {
            for (int j = 1; j < 16; j++) {
                if(flag)
                    return System.currentTimeMillis()-time;
                matchingKey.setCharAt(i, Character.toUpperCase(Integer.toHexString(j).charAt(0)));
                flag = solution(i + 1, key);
            }
        }
        return System.currentTimeMillis()-time;
    }
    public static boolean solution(int index, String key) {
        for(int i = 1; i < 16; i++) {
            matchingKey.setCharAt(index, Character.toUpperCase(Integer.toHexString(i).charAt(0)));
            if(index < matchingKey.length() - 1)
                solution(index + 1, key);
            if (matchingKey.toString().equals(key)) {
                return true;
            }
        }
        return false;
    }
}

