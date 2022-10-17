
class twodice {

    public static void main(String args[])
    {
        
          int check1 = 0; int check2 = 0; int count = 0; 
          final int finalNumber
          = 1000;
         
          for (int startNumber = 100; startNumber <= finalNumber;
          startNumber++) {
         

          check1 = startNumber % 3; 
          check2 = startNumber % 4;
         
          if (check1 == 0 && check2 == 0) { 
          System.out.print(startNumber + " ");
         
          count++;
          count %= 10; 
          

         if (count == 0) { 
             System.out.println(""); }
         
          }
         
          }
         
    }

}