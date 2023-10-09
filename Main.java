import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

class Main {

    static TreeMap<String, String> responses = new TreeMap<>();
    static HashSet<Transaction> set = new HashSet<>();

    public static void main(String[] args) {
        // formToRBT("BVB Ticket Order Form (Responses) - Form Responses.csv");
        // formToRBT("Venmo Transactions History.csv");

        transactionToRBT("/Users/shreyasnatarajan/Desktop/PersonalProjects/TicketChecker/ticketchecker/Venmo Transaction History - 1.csv");
    }


    public static void transactionToRBT(String filepath){
         try (Scanner scanner = new Scanner(new File(filepath))) {

            int skip = 0;

            while (scanner.hasNextLine()) {
                skip++;
                if(skip < 6){
                    scanner.nextLine();
                    continue;
                }

                String line = scanner.nextLine();
                String[] elements = line.split(",");

                Transaction transaction = null;

                try{
                    transaction = new Transaction(elements[6], elements[5]);
                } catch (IndexOutOfBoundsException e){
                    // do nothing 
                }

                set.add(transaction);

            }

         } catch(Exception e){ //for the file not found exception
            //do nothing
            e.printStackTrace();
        }
    }

    public static void printTransactions(){
        for(Transaction t : set){

            // search responses for the name
            // responses.get(t.);

            System.out.println(t.toString());

        }
    }





    public static void formToRBT(String filepath){
        try (Scanner scanner = new Scanner(new File(filepath))) {

            int skip3 = 0;

            while (scanner.hasNextLine()) {
                skip3++;
                if(skip3 <= 3){
                    scanner.nextLine();
                    continue;
                }

                String line = scanner.nextLine();

                if(line.contains("REFILL")){
                    System.out.println(line);
                }

                String[] splitLine = line.split(",");
                if(splitLine.length < 3){
                    continue;
                }
                String Key = splitLine[1].trim() + " " + splitLine[2].trim();
                String note = splitLine[4];

                responses.put(Key, note);

            }

        } catch(Exception e){ //for the file not found exception
            //do nothing
            e.printStackTrace();
        }

        System.out.println(responses.toString());
            
    }
}