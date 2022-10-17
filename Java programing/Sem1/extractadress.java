import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class extractadress {

    public static void main(String[] args) throws IOException {

        
        int[] houseNumber = { 0, 0, 0 };
        String[] street = { "", "", "" };
        String[] town = { "", "", "" };
        String[] country = { "", "", "" };
        
        try {
            File f = new File("filename.txt");
            Scanner fscan = new Scanner(f);

            int i = 0;

            while (fscan.hasNext()) {


                houseNumber[i] = fscan.nextInt();
                street[i] = fscan.next();
                town[i] = fscan.next();
                country[i] = fscan.next();

                System.out.println("House Number:" + houseNumber[i]);
                System.out.println("Street Name :" + street[i]);
                System.out.println("Town:" + town[i]);
                System.out.println("Country" + country[i]);
                System.out.println("-----------------------------------------------------");

            }

            fscan.close();
        }

        catch (FileNotFoundException e) {

            System.out.println("Error");
            e.printStackTrace();

        }
    }

}
