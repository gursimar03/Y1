import java.util.Random;

class test {

    public static void main (String args[]){

    Random rand = new Random();

    int asci = 0;
    char ch = ' '; 
    
    
    for(int i = 0;i<=9;i++) {
        
    asci = rand.nextInt(25) + 65;
    ch = (char) asci ;
    
    System.out.println(ch);


    }


    }
}