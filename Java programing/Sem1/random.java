
import java.util.Random;

public class random {

    public static void main(String args[]) {

        Random rand = new Random();

        int r = 0;
        int count = 0;

        for (int i = 0; i < 10; i++) {

            r = rand.nextInt(19) + 1;

            if (r % 2 == 0) {

                count++;

            }
            System.out.println(r);
        }

        System.out.println("Number of even numbers : " + count);

        System.out.println("");
        System.out.println("");
        System.out.println("");

        count = 0;

        for (int i = 0; i < 10; i++) {

            r = rand.nextInt(46) + 4;

            if (r > count) {

                count = r;

            }
            System.out.println(r);
        }

        System.out.println("Highest number  : " + count);

    }

}
