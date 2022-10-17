
package exam15;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Gursimar
 */
public class question3 {

    public static void main(String[] args) throws IOException {

        final int lowAttendes = 9; // Constents for Question 3(e) to store constants
        final int highAttendes = 18;

        Scanner input = new Scanner(new File("exam15/exercises.txt"));

        String instructor = null, class_name = null;
        double price = 0;
        int duration = 0, salary_per_class = 0, attendees = 0;
        double profit = 0;
        double totalProfit = 0;

        double lowestProfit = Integer.MAX_VALUE; // Variable to store lowest profit earned
        String LowestProfitClass = null; // Variable to store the name of lowest profit class
        String LowestProfitInstructor = null; // Variable to store the name of instructor for lowest profit class
        int classesTakenByJohn = 0; // Variable to store classes taken by John
        int classesTakenByPaul = 0; // Variable to store classes taken by Paul
        int classesTakenByEmma = 0; // Variable to store classes taken by Emma
        String mostClassesTakenBy = null; // Variable to store the name of instructor who took most classes
        int mostClassesTaken = 0; // Variable to store most number of classes
        double averageProfit = 0; // Variable to store average profit
        int totalNumberOfclasses = 0; // Variable to store total number of classes

        System.out.printf("%-10s%12s%9s%15s%20s%13s%13s\n",
                "Class", "Duration", "Price", "Instructor", "Salary per Class", "Attendees", "Profit");

        while (input.hasNext()) {
            class_name = input.next();
            duration = input.nextInt();
            price = input.nextDouble();
            instructor = input.next();
            salary_per_class = input.nextInt();
            attendees = input.nextInt();

            // Question 3(b)
            profit = (price * attendees) - salary_per_class; // Calculating the profit

            // Question 3(c)
            totalProfit += profit; // Adding all the profit to later calculate average

            if (profit < lowestProfit) { // Checking if the profit is the lowest among all

                lowestProfit = profit;
                LowestProfitClass = class_name;
                LowestProfitInstructor = instructor;
            }

            if (instructor.equals("Paul")) { // Conditions to check how many classes were taken by instructors
                classesTakenByPaul++;
            } else if (instructor.equals("John")) {
                classesTakenByJohn++;
            } else if (instructor.equals("Emma")) {
                classesTakenByEmma++;
            }

            System.out.printf("%-10s%12s%9s%15s%20s%13s%13.2f", class_name, duration, price, instructor,
                    salary_per_class, attendees, profit);

            // Question 3(e)
            if (attendees > lowAttendes && attendees < highAttendes) {
                System.out.print("***");
            }

            totalNumberOfclasses++; // Counting number of classes
            System.out.println("");

        }

        // Question 3(d)
        averageProfit = totalProfit / totalNumberOfclasses; // Calculating average profit

        System.out.println("");
        System.out.println("The total profit for all calsses is $" + totalProfit);
        System.out.printf("Average profit is %.2f$", averageProfit);
        System.out.println("");
        System.out.println("The smallest profit was achieved by instructor " + LowestProfitInstructor + " at "
                + lowestProfit + "$ for " + LowestProfitClass);

        // Question 3(f)
        if (classesTakenByEmma > classesTakenByJohn) // Conditions to check who took the most classes
        {
            if (classesTakenByPaul > classesTakenByEmma) {

                mostClassesTaken = classesTakenByPaul;
                mostClassesTakenBy = "Paul";
            } else {

                mostClassesTaken = classesTakenByEmma;
                mostClassesTakenBy = "Emma";
            }
        } else if (classesTakenByJohn > classesTakenByPaul) {

            mostClassesTaken = classesTakenByJohn;
            mostClassesTakenBy = "John";
        }

        System.out.println("");
        System.out.println("John carried out " + classesTakenByJohn + " class(es)");
        System.out.println("Emma carried out " + classesTakenByEmma + " class(es)");
        System.out.println("Paul carried out " + classesTakenByPaul + " class(es)");
        System.out.println("");
        System.out
                .println(mostClassesTakenBy + " is the instructor for most classes: " + mostClassesTaken + " classes");
        System.out.println("");
    }
}
