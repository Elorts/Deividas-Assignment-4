import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class DataSeparationApp {

	public static void main(String[] args) throws IOException {

		Integer masterFileSize = (int) Files.lines(Paths.get("student-master-list.csv")).count();

		Student[] professorC = new Student[masterFileSize];
		Student[] professorS = new Student[masterFileSize];
		Student[] professorA = new Student[masterFileSize];

		readDataFromFile(professorC, professorS, professorA);
	}

	private static void readDataFromFile(Student[] professorC, Student[] professorS, Student[] professorA) {

		Integer c = 0;
		Integer s = 0;
		Integer a = 0;
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

				if (student.getCourse().startsWith("COMP")) {

					professorC[c] = student;
					c++;

				} else if (student.getCourse().startsWith("STAT")) {

					professorS[s] = student;
					s++;

				} else {

					professorA[a] = student;
					a++;

				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Oops, no file!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Oops, some I/O problem - Exception!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		professorC = cleanArray(professorC, c);
		professorS = cleanArray(professorS, s);
		professorA = cleanArray(professorA, a);

		Arrays.sort(professorC);
		Arrays.sort(professorS);
		Arrays.sort(professorA);

		writeToFile(professorC, "course1.csv");
		writeToFile(professorS, "course2.csv");
		writeToFile(professorA, "course3.csv");
	}

	private static void writeToFile(Student[] professor, String outputFile) {
		BufferedWriter fileWriter = null;

		try {
			fileWriter = new BufferedWriter(new FileWriter(outputFile));
			fileWriter.write("Student ID,Student Name,Course,Grade\n");
			for (int i = 0; i < (professor.length); i++) {
				fileWriter.write(professor[i].getStudentId() + "," + professor[i].getStudentName() + ","
						+ professor[i].getCourse() + "," + professor[i].getGrade() + "\n");
			}

		} catch (FileNotFoundException e) {
			System.out.println("Oops, no file!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Oops, some I/O problem - Exception!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
				System.out.println("Output file " + outputFile + " saved.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static Student[] cleanArray(Student[] professor, Integer counter) {
		Student[] tempArray = new Student[counter];

		for (int i = 0; i < counter; i++) {
			tempArray[i] = professor[i];
		}
		return tempArray;

	}

}
