package sadi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentEnrollmentDatabase implements StudentEnrolmentManager {
        List<StudentEnrollment> studentEnrollments;
        private final String file;
    static Scanner sc = new Scanner(System.in);

     public StudentEnrollmentDatabase(String file) {
         this.studentEnrollments = new ArrayList<>();
         this.file = file;
     }


     @Override
     public void add(String studentID, String courseID, String semester) throws IOException {
         StudentEnrollment newSE = new StudentEnrollment(studentID, courseID, semester);
         studentEnrollments.add(newSE);
     }

     public List<StudentEnrollment> getStudentEnrollments() {
         return studentEnrollments;
     }

     public void save() throws IOException {
         // opens the file for writing
         try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
             // iterates over the leads
             for (StudentEnrollment se : getStudentEnrollments()) {
                 // // creates an array of the lead's values
                 String[] values = { se.getStudentID(), se.getCourseID(),  se.getSemester() };
                 // // creates a new line
                 String line = String.join(",", values);
                 // // writes the line
                 bw.append(line);
                 // // writes "enter", so we more to the next line
                 bw.append("\n");
             }
             bw.flush();
         }
     }




     @Override
        public void update(String type, StudentEnrollment updateStudent, String newUpdate) {
            if (type.equals("sid")) {
                updateStudent.setStudentID(newUpdate);
                System.out.println(updateStudent);
            }
         if (type.equals("cid")) {
             updateStudent.setCourseID(newUpdate);
             System.out.println(updateStudent);
         }
         if (type.equals("sem")) {
             updateStudent.setSemester(newUpdate);
             System.out.println(updateStudent);
         }
        }

        @Override
        public void delete() {

        }


     @Override
     public void getOne(String type, String id) {
        if (type.equalsIgnoreCase("student")) {
            for (StudentEnrollment studentEnrollment : studentEnrollments) {
                if (studentEnrollment.getStudentID().equals(id)) {
                    System.out.println(studentEnrollment);
                }
            }
        }
         if (type.equalsIgnoreCase("course")) {
             for (StudentEnrollment studentEnrollment : studentEnrollments) {
                 if (studentEnrollment.getCourseID().equals(id)) {
                     System.out.println(studentEnrollment);
                 }
             }
             System.out.println("*****************************");
             System.out.println("Press \"Enter\" to return");
             sc.nextLine();
         }
         if (type.equalsIgnoreCase("semester")) {
             for (StudentEnrollment studentEnrollment : studentEnrollments) {
                 if (studentEnrollment.getSemester().equals(id)) {
                     System.out.println(studentEnrollment);
                 }
             }
             System.out.println("*****************************");
             System.out.println("Press \"Enter\" to return");
             sc.nextLine();
         }


     }



    public StudentEnrollment detailEnrollment(String type, String studentID, String selectedID) {
         if (type.equals("student")) {
            for (StudentEnrollment studentEnrollment : studentEnrollments) {
                if ((studentEnrollment.getStudentID().equals(studentID)) && (studentEnrollment.getCourseID().equals(selectedID))) {
                    return studentEnrollment;
                }
            }
        }
        if (type.equals("sem")) {
            for (StudentEnrollment studentEnrollment : studentEnrollments) {
                if ((studentEnrollment.getStudentID().equals(studentID)) && (studentEnrollment.getSemester().equals(selectedID))) {
                    System.out.println(studentEnrollment.getSemester());
                    return studentEnrollment;
                }
            }
        }


        return null;
    }


        @Override
        public void getAll() {
            for (StudentEnrollment studentEnrollment: studentEnrollments) {
                System.out.println(studentEnrollment);
            }
        }

     @Override
     public void load() throws IOException {
         studentEnrollments.clear();
         // Opens the file for reading
         try (BufferedReader br = new BufferedReader(new FileReader(file))) {
             String enrollmentInfo;
             // Reads it line by line
                 while ((enrollmentInfo = br.readLine()) != null) {
                     // splits the string line using semicolons
                     String[] values = enrollmentInfo.split(",");
                     String studentID = values[0];
                     String courseID = values[1];
                     String semester = values[2];
                     // adds a new lead with those values
                     add(studentID, courseID, semester);
                 }

         }
     }
 }
