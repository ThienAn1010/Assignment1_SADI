package sadi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Student {
    private String id;
    private String name;
    private LocalDate birthdate;
    private List<Student> students;

    public Student(String id, String name, String birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdateFormatter(birthdate);

    }

    public Student() {
        students = new ArrayList<>();
        students.add(new Student("s3825455", "Nguyen Hoang Thien An", "10/10/2001"));
        students.add(new Student("s3825456", "Nguyen Dang Lam Phuong", "04/12/2001"));
        students.add(new Student("s3825457", "Nguyen Hoang Nam", "10/05/2001"));
        students.add(new Student("s3825458", "Pham Quang Man", "06/09/2001"));
        students.add(new Student("s3825459", "Nguyen Van Binh", "03/04/2001"));
        students.add(new Student("s1234567", "Nguyen Bao Ngoc", "01/07/2001"));
        students.add(new Student("s7654321", "Bui Sy Nghi", "11/02/2001"));
        students.add(new Student("s1313131", "Le Ha Tan Thien", "01/12/2001"));
        students.add(new Student("s1212121", "Bui Anh Tuan", "09/06/2001"));
        students.add(new Student("s2323232", "Ha Anh Tuan", "06/09/2001"));
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    private LocalDate birthdateFormatter(String birthDateString) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate officialBirthDate = LocalDate.parse(birthDateString, formatter);
            return officialBirthDate;
    }

    @Override
    public String toString() {
        return "Student name: " + name  +
                " | ID: " + id  +
                " | Birthdate: " + birthdate
                ;
    }
}



