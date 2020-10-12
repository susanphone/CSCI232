import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ValueCount {
    public static void main(String[] args) {
        // getting two command line arguments
        final int r = Integer.parseInt(args[0]);
        final int n = Integer.parseInt(args[1]);
        // get a random number generator
        Random random = new Random();
        // create a map to keep track on random number and their frequencies
        Map<Integer, Integer> numberFreq = new HashMap<>();

        // itierator to generate random numbers
        for (int i = 0; i<n; i++) {
            final int randomNumber = random.nextInt(r);
            // if the number exists, add to the frequency
            // else add a new random number
            if(numberFreq.containsKey(randomNumber)) {
                int existingFreq = numberFreq.get(randomNumber);
                existingFreq ++;
                numberFreq.put(randomNumber, existingFreq);
            } else {
                numberFreq.put(randomNumber, 1);
            }
        }
        // print nicely for better understanding
        for (Map.Entry<Integer, Integer> e : numberFreq.entrySet()) {
            System.out.printf("%d occurred %d time(s)\n", e.getKey(), e.getValue());
        }
    }
}




