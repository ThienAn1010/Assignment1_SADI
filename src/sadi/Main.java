package sadi;

import java.io.IOException;
import java.util.Scanner;


    public class Main {
        static String fileName = null;
        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);
            Validator validator = new Validator();
            int choice;
            fileName = validator.fileValidate();

            do {
                Validator.clearscr();
                System.out.println("STUDENT ENROLMENT MANAGER");
                System.out.println("*****************************");
                System.out.println("1. Add");
                System.out.println("2. Update");
                System.out.println("3. Print");
                System.out.println("4. Delete");
                System.out.println("5. Exit");
                System.out.println("*****************************");
                choice = validator.choiceValidator(5);
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

                    if (choice == 4) {
                        StudentEnrollment.deleteEnrollment();
                    }

            } while (choice != 5) ;
            sc.close();
            System.out.println("Exiting...");
        }

}
