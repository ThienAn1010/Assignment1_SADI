package sadi;

import java.io.IOException;
import java.util.Scanner;

public class StudentEnrollment {
    static StudentEnrollmentDatabase studentEnrollmentDatabase = new StudentEnrollmentDatabase("Student enrollments.csv");
    private String studentID;
    private String courseID;
    private String semester;
    static Scanner sc = new Scanner(System.in);

    public StudentEnrollment(String studentID, String courseID, String semester) throws IOException {
        this.studentID = studentID;
        this.courseID = courseID;
        this.semester = semester;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getSemester() {
        return semester;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public static void enrollStudent() throws IOException {
        String newStudentID;
        String newCourse;
        String newSemester;
        try {
            studentEnrollmentDatabase.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Ultility.clearscr();
        System.out.println("*****************************");
        System.out.println("NEW ENROLLMENT");
        System.out.print("Please enter the student ID: ");
        newStudentID = sc.nextLine();
        System.out.print("Please enter the course ID: ");
        newCourse = sc.nextLine();
        System.out.print("Please enter the semester: ");
        newSemester = sc.nextLine();
        studentEnrollmentDatabase.add(newStudentID,newCourse,newSemester);
        try {
            studentEnrollmentDatabase.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Press \"Enter\" to exit");
        sc.nextLine();
    }

    public static void updateEnrollment(){
        String choice2;
        String updateSID;
        String updateCID;
        int choice3;
        String newUpdate;
        Ultility.clearscr();
        System.out.println("*****************************");
        System.out.println("UPDATE ENROLLMENT");
        System.out.print("Please enter the student ID that you want to update: ");
        updateSID = sc.nextLine();
        System.out.print("Please enter the course ID that you want to update: ");
        updateCID = sc.nextLine();

        try {
            studentEnrollmentDatabase.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("*****************************");

        StudentEnrollment updateStudent = studentEnrollmentDatabase.detailEnrollment(updateSID,updateCID);
        if (updateStudent != null) {
            System.out.println("Enrolment Information");
            System.out.println(updateStudent);
            System.out.println("Continue to update? Y/N");
            choice2 = sc.nextLine();
            if (choice2.equalsIgnoreCase("Y")) {
                do {
                    Ultility.clearscr();
                    System.out.println("*****************************");
                    System.out.println("Please choose what you want to update:");
                    System.out.println("1. Student ID");
                    System.out.println("2. Course ID");
                    System.out.println("3. Semester");
                    System.out.println("4. Return");
                    System.out.println("*****************************");
                    System.out.print("Please choose a command to execute: ");
                    choice3 = sc.nextInt();

                    if (choice3 == 1) {
                        Ultility.clearscr();
                        sc.nextLine();
                        System.out.println("*****************************");
                        System.out.println("Student ID update");
                        System.out.print("Please enter a new student ID: ");
                        newUpdate = sc.nextLine();
                        studentEnrollmentDatabase.update("sid",updateStudent,newUpdate);
                        try {
                            studentEnrollmentDatabase.save();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Press \"Enter\" to exit");
                    }

                    if (choice3 == 2) {
                        Ultility.clearscr();
                        sc.nextLine();
                        System.out.println("*****************************");
                        System.out.println("Course ID update");
                        System.out.print("Please enter a new course ID: ");
                        newUpdate = sc.nextLine();
                        studentEnrollmentDatabase.update("cid",updateStudent,newUpdate);
                        try {
                            studentEnrollmentDatabase.save();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Press \"Enter\" to exit");
                    }

                    if (choice3 == 3) {
                        Ultility.clearscr();
                        sc.nextLine();
                        System.out.println("*****************************");
                        System.out.println("Semester update");
                        System.out.print("Please enter a new semester: ");
                        newUpdate = sc.nextLine();
                        studentEnrollmentDatabase.update("sem",updateStudent,newUpdate);
                        try {
                            studentEnrollmentDatabase.save();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Press \"Enter\" to exit");
                    }
                    sc.nextLine();
                } while (choice3 != 4);
            } else if ((choice2.equalsIgnoreCase("N"))) {
                System.out.println("Press \"Enter\" to return");
                sc.nextLine();
            }
        } else {
            System.out.println("There is no enrollment matches your input!");
            System.out.println("Press \"Enter\" to return");
            sc.nextLine();
        }
    }

    public static void printEnrollment() {
        int choice;
        do {
            Ultility.clearscr();
            try {
                studentEnrollmentDatabase.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("*****************************");
            System.out.println("Please choose what you want to print");
            System.out.println("1. Print all enrollments");
            System.out.println("2. Print individually");
            System.out.println("3. Return");
            System.out.println("*****************************");
            System.out.print("Please choose a command to execute: ");
            choice = sc.nextInt();

            if (choice == 1) {
                Ultility.clearscr();
                sc.nextLine();
                System.out.println("ENROLLMENT LIST");
                System.out.println("*****************************");
                studentEnrollmentDatabase.getAll();
                System.out.println("*****************************");
                System.out.println("Press \"Enter\" to return");
                sc.nextLine();
                choice = 0;
            }

            if (choice == 2) {
                int choice2;
                do {
                    Ultility.clearscr();
//                    sc.nextLine();
                    System.out.println("*****************************");
                    System.out.println("Please choose what you want to print");
                    System.out.println("1. One student's enrollment list");
                    System.out.println("2. One course's enrollment list");
                    System.out.println("3. All courses in one semester");
                    System.out.println("4. Return");
                    System.out.println("*****************************");
                    System.out.print("Please choose a command to execute: ");
                    choice2 = sc.nextInt();


                    if (choice2 == 1) {
                        Ultility.clearscr();
                        sc.nextLine();
                        System.out.println("*****************************");
                        System.out.print("Please enter the student ID: ");
                        String studentIdInput = sc.nextLine();

                        studentEnrollmentDatabase.getOne("student",studentIdInput);
                        choice2 = 0;
                    }
                    if (choice2 == 2) {
                        Ultility.clearscr();
                        sc.nextLine();
                        System.out.println("*****************************");
                        System.out.print("Please enter the course ID: ");
                        String courseIdInput = sc.nextLine();
                        studentEnrollmentDatabase.getOne("course",courseIdInput);
                        choice2 = 0;

                    }
                    if (choice2 == 3) {
                        Ultility.clearscr();
                        sc.nextLine();
                        System.out.println("*****************************");
                        System.out.print("Please enter the semester: ");
                        String semesterInput = sc.nextLine();
                        studentEnrollmentDatabase.getOne("semester",semesterInput);
                        choice2 = 0;
                    }
                    choice = 0;
                } while (choice2 != 4);
                System.out.println("Press \"Enter\" to return");
                sc.nextLine();
            }

        } while (choice != 3);
        System.out.println("Press \"Enter\" to return");
        sc.nextLine();

    }











    @Override
    public String toString() {
        return
                "StudentID: " + studentID + " " +
                "| CourseID: " + courseID + " "   +
                "| semester: " + semester
                ;
    }


}




