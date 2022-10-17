package exam15;

/**
 *
 * @author Gursimar
 */
public class question2 {

    public static void main(String[] args) {
        
        final int predictionYear = 2031;            //To store constants 
        final int investmentcheck = 175000;


        int year = 2021;                        
        double propertyValue = 150000;
        double goldValue = 150000;
        int counter = 0;
        int goldYearCheck = 0;
        int propertyYearCheck = 0;
        

        System.out.println("Year \t\t\t Property \t\t\t Gold");

        while(year != (predictionYear + 1)){

            System.out.printf("%d \t\t\t %.3f \t\t\t %.3f",year,propertyValue,goldValue);

            propertyValue = ((propertyValue * 3.0)/100) + propertyValue;        //calculating the property value 
            

            if(counter >= 3){                                                   //calculating the gold value after 3 years 

                goldValue = ((goldValue * 1.0)/100) + goldValue;
            }
            else{
                goldValue = ((goldValue * 5)/100) + goldValue;                   //calculating the gold value 
            }
            

            if(goldValue > investmentcheck && goldYearCheck == 0){              // checking when gold investment went over 175,000

                goldYearCheck = year;

            }
            if(propertyValue > investmentcheck && propertyYearCheck == 0){      // checking when property investment went over 175,000

                propertyYearCheck = year;
            }


            year++;
            counter++;
            System.out.println("");

        }

        System.out.println("");
        System.out.println("Property investement of $150,000 would be worth over "+investmentcheck+" in year "+propertyYearCheck);
        System.out.println("Gold investement of $150,000 would be worth over "+investmentcheck+" in year "+goldYearCheck);
    }
    
}
