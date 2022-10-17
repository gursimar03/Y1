package SummerExam;

import java.util.ArrayList;
import java.util.Scanner;

public class countCharacter {

    public static void main(String[] args) {
        
        ArrayList<String> strings = new ArrayList<>();
        
        Scanner keyboard = new Scanner(System.in);

        // System.out.println("PLease enter 4 strings");

        // for (int i = 0; i < 5; i++) {
            
        //     String a = keyboard.next();
        //     strings.add(a);
       
        // }
        // int count = 0;
      

        // for (String string : strings) {
           
        

        //     for (int i = 0; i < string.length(); i++) {
                
        //         if('a' == string.charAt(i) || 'A' == string.charAt(i)){
        //             count++;
                    
        //         }


        //     }

        // }
        // System.out.println(count);
       

        // ArrayList<Integer> num = new ArrayList<>();
        // for (int i = 0; i < 4; i++) {
            
        //     System.out.println("enter number");
        //     num.add(keyboard.nextInt());
        // }


        // removeEnd(num,69);

        // removeEnd(num, 100);

        ArrayList<Integer> n = new ArrayList<>();
    
        n.add(68);

        n.add(60);
        n.add(61);
        n.add(13);
        
            removeEnd(n, 68);
            System.out.println(n);
    }


    static void removeEnd(ArrayList<Integer> a, int as){

        if(a.lastIndexOf(as) >= 0){
            a.remove(a.lastIndexOf(as));
        }

        for (Integer integer : a) {
            System.out.println(integer);
        }

        if(a.lastIndexOf(as) < 0){
            for (Integer integer : a) {
                a.remove(integer);
            }
        }
    }
   

    static void repeat(ArrayList<Integer> numbers){

        int size = numbers.size() - 1;
        ArrayList <Integer> newA = new ArrayList<>();
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {
                newA.add(numbers.get(j));
            }
        }

                   System.out.println(newA);
        

    }


}  
