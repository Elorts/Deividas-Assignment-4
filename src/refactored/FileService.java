package refactored;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileService {

    public Student[] readDataFromFile() {

        Integer masterFileSize = 0;
        try {
            masterFileSize = (int) Files.lines(Paths.get("student-master-list.csv")).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Student[] students = new Student[masterFileSize - 1];
        int studentCtr = 0;
        BufferedReader fileReader = null;

        try {
            fileReader = new BufferedReader(new FileReader("student-master-list.csv"));

            String[] splittedString;
            String line = fileReader.readLine();

            while ((line = fileReader.readLine()) != null) {

                Student student = new Student();
                splittedString = line.split(",");

                student.setStudentId(Integer.parseInt(splittedString[0]));
                student.setStudentName(splittedString[1]);
                student.setCourse(splittedString[2]);
                student.setGrade(Integer.parseInt(splittedString[3]));

                students[studentCtr] = student;
                studentCtr++;
            }

            return students;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        public void writeStudentsFromFile (Student[]students, String filename){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename));) {

                writer.write("Student ID,Student Name,Course,Grade\n");
                for (Student student : students) {
                    if (student != null) {
                        writer.write(student.getStudentId() + "," + student.getStudentName() + ","
                                + student.getCourse() + "," + student.getGrade() + "\n");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

