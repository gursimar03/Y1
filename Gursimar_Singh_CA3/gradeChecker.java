
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class gradeChecker {
    
    public static void main(String[] args)throws IOException {

        //Making a Constant Varibale for number of subjects
        final int numberOfSubjects = 2;
        
        //All variable initilizations
        double average = 0d;
        int totalMarks = 0;
        double high = 0;
        double low = Integer.MAX_VALUE;
        String lowestAvgStudentName = " ";
        String highestAvgStudentName = " ";
        
        try{

            //Making an object file
            File grade = new File("grades.txt");
            Scanner myFile = new Scanner(grade);

            System.out.println("Name            Maths           Programing          Average");

            while(myFile.hasNext()){

                String studentName = myFile.next();
                int mathScore = myFile.nextInt();
                int programingScore = myFile.nextInt();

                //Calculating Average 
                totalMarks = mathScore + programingScore;
                average = ((double) totalMarks) / numberOfSubjects; 

                
                System.out.println(studentName+"                "+mathScore+"                  "+programingScore+"              "+average);

                if(average < low){

                    low = average;
                    lowestAvgStudentName = studentName;

                }else if(average > high){

                    high = average;
                    highestAvgStudentName = studentName;

                }    
            }   

            System.out.println(" ");
            System.out.println(highestAvgStudentName+" is the student with highest average grade. ");
            System.out.println(lowestAvgStudentName+" is the student with lowest average grade. ");

            //Closing Scanner Object
            myFile.close();

        }
        
        catch(FileNotFoundException e){

            System.out.println("Error");
            e.printStackTrace();
        }
        
    }
}
