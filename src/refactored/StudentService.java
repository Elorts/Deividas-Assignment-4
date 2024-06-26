package refactored;

import java.util.Arrays;

public class StudentService {

    private Student[] students;

    public StudentService(FileService fileService) {
        students = fileService.readDataFromFile();
        sortStudents();
    }

    public void sortStudents() {
        Arrays.sort(students);
    }

    public Student[] filterStudentByCourse(String courseKey) {
        Student[] courseStudents = new Student[students.length];
        int counter = 0;
        for (Student student : students) {
            if (student.getCourse().contains(courseKey)) {
                courseStudents[counter++] = student;
            }
        }

        return courseStudents;
    }
}
