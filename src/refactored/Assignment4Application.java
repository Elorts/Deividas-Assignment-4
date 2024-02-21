package refactored;

public class Assignment4Application {

    public static void main(String[] args) {
        FileService fileService = new FileService();
        StudentService studentService = new StudentService(fileService);
        StudentReportService reportService = new StudentReportService(fileService, studentService);

        reportService.generateCourseStudentsReport("COMPSCI", "course1-ref.csv");
        reportService.generateCourseStudentsReport("APMTH", "course2-ref.csv");
        reportService.generateCourseStudentsReport("STAT", "course3-ref.csv");
    }
}
