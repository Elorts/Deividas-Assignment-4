import java.io.Serializable;

public class Student implements Comparable<Student> {
	private Integer studentId;
	private String studentName;
	private String course;
	private Integer grade;
	
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	@Override
	public int compareTo(Student that) {
		if (this.grade > that.grade) {
			return -1;
		} else if (this.grade.equals(that.grade)) {
			return 0;
		} else {
		return 1;
		}
	}
}
