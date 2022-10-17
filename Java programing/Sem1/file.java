import java.util.*;

import java.io.*;

class file {

   final static String filename = "filename.txt";

   public static void main(String[] args) {

      int avg = 0;
      int count = 0;
      int total = 0;
      try {
         File fil = new File("filename.txt");
         Scanner my = new Scanner(fil);

         while (my.hasNext()) {
            String data = my.next();
            if (data != " ") {

               total += Integer.parseInt(data);
               count++;

               System.out.println(data);
            }
         }
         avg = total / count;
         System.out.println("");
         System.out.println("");
         System.out.println("");
         System.out.println(count);
         System.out.println("");
         System.out.println("");
         System.out.println(avg);
         my.close();
      } catch (FileNotFoundException e) {

         System.out.println("Error");
         e.printStackTrace();
      }
   }
}