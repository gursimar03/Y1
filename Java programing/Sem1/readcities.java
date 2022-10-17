import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class readcities {

    public static void main(String[] args) throws IOException {

        try {

            File f = new File("city.txt");
            Scanner fs = new Scanner(f);

            int numberOfCities = 0;
            String largestCity = " ";
            String SmallestCity = " ";
            int min = Integer.MAX_VALUE;
            int max = 0;
            int totalPopulation = 0;

            while (fs.hasNext()) {

                
                String cityName = fs.next();
                System.out.println("City Name: "+cityName);
                int population = fs.nextInt();
                System.out.println("City Population: "+population);

                System.out.println("---------------------------------------------------");
                totalPopulation += population;

                if(population > max)
                {
                    max = population;
                    largestCity = cityName;
                   
                }
                else if(population < min)
                {
                    min = population;
                    SmallestCity = cityName;
                   
                }

                
                numberOfCities++;
            }
            System.out.println("---------------------------------------------------");
            if(numberOfCities > 0){

                double average = ((double) totalPopulation)/ numberOfCities;
                System.out.println("Total Average Population : "+ average);
                
            
            }

            System.out.println(max+" "+largestCity);
            System.out.println(min+" "+SmallestCity);
            System.out.println("Number of Cities: "+ numberOfCities);
            
            fs.close();
        } catch (FileNotFoundException e) {

            System.out.println("ERROR");
            e.getStackTrace();
        }

    }
}
