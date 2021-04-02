package sadi;

import java.io.IOException;
import java.util.List;

public interface StudentEnrolmentManager {
    public void add(String studentID, String courseID, String semester) throws IOException;
    public void update();
    public void delete();
    public void getAll();
    public void load() throws IOException;
    public void save() throws IOException;
    public void getOne(String type, String id);
}
