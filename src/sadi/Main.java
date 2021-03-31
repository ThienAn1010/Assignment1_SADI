package sadi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


    public class Main {
        public static void main(String[] args) throws IOException {
            // Populate data
            //////////////////////////////////////////////////////////////////////////////////
            ArrayList<Student> students = new ArrayList<>();
            ArrayList<Course> courses = new ArrayList<>();
            students.add(new Student("s3825455", "Nguyen Hoang Thien An", "10/10/2001"));
            students.add(new Student("s3825456", "Nguyen Dang Lam Phuong", "04/12/2001"));
            students.add(new Student("s3825457", "Nguyen Hoang Nam", "10/05/2001"));
            students.add(new Student("s3825458", "Pham Quang Man", "06/09/2001"));
            students.add(new Student("s3825459", "Nguyen Huu Luan", "01/04/2001"));
            courses.add(new Course("COSC1234", "Intro to IT", 12));
            courses.add(new Course("COSC1235", "Intro to Programming", 12));
            courses.add(new Course("COSC1236", "UCD", 12));
            courses.add(new Course("COSC1237", "SADI", 12));
            courses.add(new Course("COSC1238", "SEPM", 12));
            //////////////////////////////////////////////////////////////////////////////////
            Scanner sc = new Scanner(System.in);
            int choice;
            do {
                Ultility.clearscr();
                System.out.println("STUDENT ENROLMENT MANAGER");
                System.out.println("*****************************");
                System.out.println("1. Add");
                System.out.println("2. Update");
                System.out.println("3. Print");
                System.out.println("4. Exit");
                System.out.println("*****************************");
                System.out.print("choose a command to execute: ");
                choice = sc.nextInt();

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
