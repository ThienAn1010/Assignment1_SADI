package sadi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Student {
    private String id;
    private String name;
    private LocalDate birthdate;

    public Student(String id, String name, String birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdateFormatter(birthdate);
    }

    private LocalDate birthdateFormatter(String birthDateString) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate officialBirthDate = LocalDate.parse(birthDateString, formatter);
            return officialBirthDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}



