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
                            ps.addPhone(phone);
                            System.out.println("Phone added.\n");
                            break;
                        } else
                            System.out.println("Invalid phone");
                    }
                    break;

                }
                case "2":
                    while (true) {
                        System.out.print("Type in a phone to be found:");
                        phone = sc.nextLine();
                        if (checkPhone(phone)) {
                            if(ps.findPhone(phone))
                                System.out.println("Phone found.\n");
                            else
                                System.out.println("Phone not found.\n");
                            break;
                        } else
                            System.out.println("Invalid phone");
                    }
                    break;
                case "3": {
                    while (true) {
                        System.out.print("Type in a phone to be deleted:");
                        phone = sc.nextLine();
                        if (checkPhone(phone)) {
                            if (ps.deletePhone(phone))
                                System.out.println("Phone deleted.\n");
                            else
                                System.out.println("No phone found.\n");
                            break;
                        } else
                            System.out.println("Invalid phone");
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
                                System.out.println("Invalid input");
                            else {
                                addRandomPhones(ps, iQuantity);
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                        }
                    }
                }
                case "5":{
                    printPhoneStorage(ps);
                }

                case "0":
                    exit=false;
                    break;
                default:
                    System.out.println("Invalid input\n");


            }

        }
    }

    private static void printMenu(){
        System.out.println("" +
                "To add a phone, type in:------------ 1\n" +
                "To find a phone, type in:----------- 2\n" +
                "To delete a phone, type in:--------- 3\n" +
                "To add random phones, type in:------ 4\n" +
                "To see the structure, type in:------ 5\n" +
                "To exit, type in:------------------- 0\n");
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
            String zeroes="";
            for(int i=0;i<repetitions;i++)
                zeroes+=0;
            return zeroes+number;
        }
    }

    private static void printPhoneStorage(PhoneStorage ps){

    }

}
