import java.util.*;

import java.io.*;

class countwords {

    final static String filename = "filename.txt";

    public static void main(String[] args) {

        int count = 0;
        try {
            File fil = new File("filename.txt");
            Scanner my = new Scanner(fil);     
         
            
            while (my.hasNext()) {
                    count++;
                    my.next();
            }
            System.out.println(count);
            my.close();
        } catch (FileNotFoundException e) {

            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
