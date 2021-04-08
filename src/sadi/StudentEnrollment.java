package sadi;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentEnrollment {
    static StudentEnrollmentDatabase studentEnrollmentDatabase = new StudentEnrollmentDatabase("default.csv");
    private static Student student;
    private static Course course;
    private static Student studentInfo;
    private static Course courseInfo;
    private String studentID;
    private String courseID;
    private String semester;
    private String studentName;
    private String courseName;
    private String birthdate;
    private int numberOfCredits;
    static Scanner sc = new Scanner(System.in);
    static Validator validator = new Validator();
    //////////////////////////////////////////////////////////////////////////////////
    public StudentEnrollment(String studentID,String studentName, String birthdate, String courseID, String courseName, int numberOfCredits, String semester ) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.semester = semester;
        this.studentName = studentName;
        this.courseName = courseName;
        this.birthdate = birthdate;
        this.numberOfCredits = numberOfCredits;
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
    //////////////////////////////////////////////////////////////////////////////////
    public static void enrollStudent() {
        student = new Student();
        course = new Course();
        List<Course> courses = course.getCourses();
        List<Student> students = student.getStudents();
        try {
            studentEnrollmentDatabase.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Validator.clearscr();
        System.out.println("*****************************");
        System.out.println("NEW ENROLLMENT");
        System.out.println("press \"0\" to stop viewing");
        String newStudentID = validator.setSid();
        if (newStudentID.equals("0")) {
            return;
        }
        String newCourse = validator.setCid();
        if (newCourse.equals("0")) {
            return;
        }
        String newSemester = validator.setSem();
        if (newSemester.equals("0")) {
            return;
        }
        students.forEach(e -> {
            if (e.getId().equalsIgnoreCase(newStudentID)) {
                 studentInfo = e;
            }
        });
        courses.forEach(e -> {
            if (e.getId().equalsIgnoreCase(newCourse)) {
                courseInfo = e;
            }
        });
        String studentName = studentInfo.getName();
        String studentBirthdate = studentInfo.getBirthdate();
        String courseName = courseInfo.getName();
        int coursePoint = courseInfo.getNumberOfCredits();

        studentEnrollmentDatabase.add(newStudentID, studentName, studentBirthdate,newCourse, courseName, coursePoint, newSemester);
        try {
            studentEnrollmentDatabase.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Press \"Enter\" to exit");
        sc.nextLine();
    }

    public static void updateEnrollment() {
        int choice1;
        String choice2;
        int choice3;
        String updateSID;
        String updateCID;
        String newUpdate;

        do {
            Validator.clearscr();
            System.out.println("*****************************");
            System.out.println("UPDATE ENROLLMENT");
            System.out.println("1. Update enrollment detail");
            System.out.println("2. Add/Delete courses for student");
            System.out.println("3. Return");
            choice1 = validator.choiceValidator();

            if (choice1 == 1) {
                Validator.clearscr();
                System.out.println("*****************************");
                System.out.println("UPDATE ENROLLMENT DETAIL");
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
                StudentEnrollment updateStudent = studentEnrollmentDatabase.detailEnrollment("student", updateSID, updateCID);
                if (updateStudent != null) {
                    System.out.println("Enrolment Information");
                    System.out.println(updateStudent);
                    System.out.println("Continue to update? Y/N");
                    choice2 = sc.nextLine();
                    if (choice2.equalsIgnoreCase("Y")) {
                        do {
                            Validator.clearscr();
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
                                Validator.clearscr();
                                sc.nextLine();
                                System.out.println("*****************************");
                                System.out.println("Student ID update");
                                System.out.print("Please enter a new student ID: ");
                                newUpdate = sc.nextLine();
                                studentEnrollmentDatabase.update("sid", updateStudent, newUpdate);
                                try {
                                    studentEnrollmentDatabase.save();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Press \"Enter\" to exit");
                            }

                            if (choice3 == 2) {
                                Validator.clearscr();
                                sc.nextLine();
                                System.out.println("*****************************");
                                System.out.println("Course ID update");
                                System.out.print("Please enter a new course ID: ");
                                newUpdate = sc.nextLine();
                                studentEnrollmentDatabase.update("cid", updateStudent, newUpdate);
                                try {
                                    studentEnrollmentDatabase.save();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Press \"Enter\" to exit");
                            }

                            if (choice3 == 3) {
                                Validator.clearscr();
                                sc.nextLine();
                                System.out.println("*****************************");
                                System.out.println("Semester update");
                                System.out.print("Please enter a new semester: ");
                                newUpdate = sc.nextLine();
                                studentEnrollmentDatabase.update("sem", updateStudent, newUpdate);
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
                    System.err.println("There is no enrollment matches your input!");
                    System.err.println("Press \"Enter\" to return");
                    sc.nextLine();
                }
                choice1 = 0;
            }

            if (choice1 == 2) {
                Validator.clearscr();
                System.out.println("*****************************");
                System.out.println("UPDATE STUDENT'S COURSES");
                String newStudentID = validator.setSid2();
                if (newStudentID.equals("0")) {
                    return;
                }

                String semInput = validator.setSem();
                if (semInput.equals("0")) {
                    return;
                }
                try {
                    studentEnrollmentDatabase.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StudentEnrollment updateStudent = studentEnrollmentDatabase.detailEnrollment("sem", newStudentID, semInput);
                if (updateStudent != null) {
                    System.out.println("*****************************");
                    System.out.println("Enrolment Information");
                    System.out.println(updateStudent);
                    System.out.println("*****************************");
                    System.out.println("Continue to update? Y/N");
                    choice2 = sc.nextLine();

                    if (choice2.equalsIgnoreCase("Y")) {
                        Validator.clearscr();
                        System.out.println("Select your action");
                        System.out.println("1.Add");
                        System.out.println("2.Delete");
                        System.out.print("choose a command to execute: ");
                        int choice4 = sc.nextInt();

                        if (choice4 == 1) {
                            System.out.println("*****************************");
                            System.out.println("ADD COURSE");
                            String newCourse = validator.setCid();
                            if (newCourse.equals("0")) {
                                return;
                            }
                            studentEnrollmentDatabase.update("cid", updateStudent, newCourse);
                            try {
                                studentEnrollmentDatabase.save();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sc.nextLine();
                            System.out.println("The enrollment has been updated!");
                            System.out.println("Press \"Enter\" to exit");
                            sc.nextLine();
                        }

                        if (choice4 == 2) {
                            System.out.println("*****************************");
                            System.out.println("DELETE COURSE");
                            String newCourse = validator.setCid();
                            if (newCourse.equals("0")) {
                                return;
                            }
                            studentEnrollmentDatabase.delete("update", updateStudent);
                            try {
                                studentEnrollmentDatabase.save();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sc.nextLine();
                            System.out.println("The enrollment has been removed!");
                            System.out.println("Press \"Enter\" to exit");
                            sc.nextLine();
                        }


                    } else if ((choice2.equalsIgnoreCase("N"))) {
                        System.out.println("Press \"Enter\" to return");
                        sc.nextLine();
                    }
                } else {
                    System.err.println("There is no enrollment matches your input!");
                    System.err.println("Press \"Enter\" to return");
                    sc.nextLine();
                }
                choice1 = 0;
            }


        } while (choice1 != 3);
    }









    public static void printEnrollment() {
        int choice;
        do {
            Validator.clearscr();
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
                Validator.clearscr();
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
                    Validator.clearscr();
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
                        Validator.clearscr();
                        sc.nextLine();
                        System.out.println("*****************************");
                        System.out.print("Please enter the student ID: ");
                        String studentIdInput = sc.nextLine();
                        studentEnrollmentDatabase.getOne("student",studentIdInput);
                        System.out.println("*****************************");
                        System.out.println("Press \"Enter\" to return");
                        sc.nextLine();
                        choice2 = 0;
                    }
                    if (choice2 == 2) {
                        Validator.clearscr();
                        sc.nextLine();
                        System.out.println("*****************************");
                        System.out.print("Please enter the course ID: ");
                        String courseIdInput = sc.nextLine();
                        studentEnrollmentDatabase.getOne("course",courseIdInput);
                        choice2 = 0;

                    }
                    if (choice2 == 3) {
                        Validator.clearscr();
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




