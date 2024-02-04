import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DataSeparationApp {

	public static void main(String[] args) {

		ArrayList<Student> professorC = new ArrayList<Student>();
		ArrayList<Student> professorS = new ArrayList<Student>();
		ArrayList<Student> professorA = new ArrayList<Student>();

		readDataFromFile(professorC, professorS, professorA);

		Collections.sort(professorC);
		writeToFile(professorC, "course1.csv");

		Collections.sort(professorS);
		writeToFile(professorS, "course2.csv");

		Collections.sort(professorA);
		writeToFile(professorA, "course3.csv");

	}

	private static void readDataFromFile(ArrayList<Student> professorC, ArrayList<Student> professorS,
			ArrayList<Student> professorA) {
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
					professorC.add(student);
				} else if (student.getCourse().startsWith("STAT")) {
					professorS.add(student);
				} else
					professorA.add(student);
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
	}

	private static void writeToFile(ArrayList<Student> professor, String outputFile) {
		BufferedWriter fileWriter = null;

		try {
			fileWriter = new BufferedWriter(new FileWriter(outputFile));

			fileWriter.write("Student ID,Student Name,Course,Grade\n");

			for (Student s : professor) {
				fileWriter.write(
						s.getStudentId() + "," + s.getStudentName() + "," + s.getCourse() + "," + s.getGrade() + "\n");
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

}
