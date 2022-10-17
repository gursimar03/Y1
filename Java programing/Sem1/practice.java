import java.util.Random;

public class practice{

        public static void main(String[] args) {
                
               Random rand = new Random();

               int a = 0;
               int b = 0;
               int count = 0;
               
               while(count < 10)
               {
                        a = rand.nextInt(10) + 1 ;
                        b = rand.nextInt(10) + 1 ;

                        System.out.println("a:"+a);
                        System.out.println("b:"+b);
                        System.out.println("Count :"+count);


                        if(a > b)
                                System.out.println("A is greater");
                        else if(a < b)
                                System.out.println("A is smaller");
                        else
                                System.out.println("both are equal");
                        count++;
               }

        }
}