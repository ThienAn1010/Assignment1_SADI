package sadi;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface StudentEnrolmentManager {
    public void add(String studentID, String studentName, String birthDate, String courseID, String courseName, int creditPoint, String semester) throws IOException;
    public void update(String type, StudentEnrollment updateStudent, String newUpdate);
    public void delete(String type, StudentEnrollment updateStudent);
    public void getAll();
    public void load() throws IOException;
    public void save() throws IOException;
    public void getOne(String studentId, String courseId, String semester);
}
