package sadi;

import java.io.IOException;
import java.util.Scanner;


    public class Main {
        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);
            Validator validator = new Validator();
            int choice;
            do {
                Validator.clearscr();
                System.out.println("STUDENT ENROLMENT MANAGER");
                System.out.println("*****************************");
                System.out.println("1. Add");
                System.out.println("2. Update");
                System.out.println("3. Print");
                System.out.println("4. Exit");
                System.out.println("*****************************");
                choice = validator.choiceValidator();


                    if (choice == 1) {
                        StudentEnrollment.enrollStudent();
                        choice = 0;
                    }

                    if (choice == 2) {
                        StudentEnrollment.updateEnrollment();
                        choice = 0;
                    }

                    if (choice == 3) {
                        StudentEnrollment.printEnrollment();
                        choice = 0;
                    }

            } while (choice != 4) ;
            sc.close();
            System.out.println("Exiting...");
        }

}
