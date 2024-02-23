package refactored;

public class StudentReportService {

    private FileService fileService;
    private StudentService studentService;

    public StudentReportService(FileService fileService, StudentService studentService) {
        this.fileService = fileService;
        this.studentService = studentService;
    }

    
    public void generateCourseStudentsReport(String courseKey, String reportFilename) {
        Student[] courseStudents = studentService.filterStudentByCourse(courseKey);

        fileService.writeStudentsFromFile(courseStudents, reportFilename);
    }
}
