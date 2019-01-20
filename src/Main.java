import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        PhoneStorage ps=new PhoneStorage();

        String input;
        String phone;

        boolean exit=true;
        while(exit){
            printMenu();
            input=sc.nextLine();
            switch (input){
                case "1": {
                    while (true) {
                        System.out.print("Type in a phone to be added:");
                        phone = sc.nextLine();
                        if (checkPhone(phone)) {
                            if(ps.findPhone(phone)){
                                System.out.println("\nPhone already exists.\n");
                                break;
                            }
                            ps.addPhone(phone);
                            System.out.println("\nPhone added.\n");
                            break;
                        } else
                            System.out.println("\nInvalid phone");
                    }
                    break;

                }
                case "2":
                    while (true) {
                        System.out.print("Type in a phone to be found:");
                        phone = sc.nextLine();
                        if (checkPhone(phone)) {
                            if(ps.findPhone(phone))
                                System.out.println("\nPhone found.\n");
                            else
                                System.out.println("\nPhone not found.\n");
                            break;
                        } else
                            System.out.println("\nInvalid phone");
                    }
                    break;
                case "3": {
                    while (true) {
                        System.out.print("Type in a phone to be deleted:");
                        phone = sc.nextLine();
                        if (checkPhone(phone)) {
                            if (ps.deletePhone(phone))
                                System.out.println("\nPhone deleted.\n");
                            else
                                System.out.println("\nNo phone found.\n");
                            break;
                        } else
                            System.out.println("\nInvalid phone");
                    }
                    break;
                }

                case "4":{
                    while (true) {
                        System.out.println("Enter quantity(max 10000): ");
                        String quantity = sc.nextLine();
                        int iQuantity;
                        try {
                            iQuantity = Integer.valueOf(quantity);
                            if (iQuantity > 10000)
                                System.out.println("\nInvalid input");
                            else {
                                addRandomPhones(ps, iQuantity);
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("\nInvalid input.");
                        }
                    }
                    break;
                }
                case "5":{
                    ps.printAllPhones();
                    break;
                }

                case "6":{
                    ps.printTrees();
                    break;
                }

                case "clear":{
                    ps=new PhoneStorage();
                    System.out.println("\nAll phones removed\n");
                    break;
                }

                case "0":
                    exit=false;
                    break;
                default:
                    System.out.println("\nInvalid input\n");


            }

        }
    }

    private static void printMenu(){
        System.out.println("" +
                "To add a phone, type in:------------------- 1\n" +
                "To find a phone, type in:------------------ 2\n" +
                "To delete a phone, type in:---------------- 3\n" +
                "To add random phones, type in:------------- 4\n" +
                "To see all phones, type in:---------------- 5\n" +
                "To see the tree structures, type in:------- 6\n\n" +
                "To wipe out all phone numbers, type in:---- clear\n" +
                "To exit, type in:-------------------------- 0\n");
    }

    private static boolean checkPhone(String phone){
        if(phone.length()==10){
            try {
                Long.valueOf(phone);
                return true;
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
            return false;
    }

    private static void addRandomPhones(PhoneStorage ps, int quantity){
        Random random=new Random();
        long randomNumber;
        for(int i=0;i<quantity;i++) {
            randomNumber=(long)(9999999999L *random.nextDouble());
            ps.addPhone(addZeroesToLongNumber(String.valueOf(randomNumber)));
        }

    }

    private static String addZeroesToLongNumber(String number){
        if(number.length()==10)
            return number;
        else{
            int repetitions=10-number.length();
            StringBuilder zeroes= new StringBuilder();
            for(int i=0;i<repetitions;i++)
                zeroes.append(0);
            return zeroes+number;
        }
    }


}
