//# Assignment: University Student Management System
//
//### **Scenario**
//
//You are tasked with building a student management system for a university. 
//The system needs to track both individual student information (instance context) and 
//university-wide statistics (static context).
//
//---
//
//### **1. University Class (Static Members)**
//
//This class handles data that is common to the entire institution.
//
//* **Static Variables:**
//* `totalStudents` (int): Track the total number of students (Initial value: 0).
//* `UNIVERSITY_CODE` (String): A constant value `"UNI-2024"`.
//* `currentAcademicYear` (String): The current year (Initial value: `"2024-2025"`).
//* `minPassingGrade` (double): The minimum grade to pass (Initial value: `2.0`).
//
//
//* **Static Methods:**
//* `displayUniversityInfo()`: Displays all university statistics.
//* `updateAcademicYear(String newYear)`: Updates the academic year.
//* `isPassingGrade(double gpa)`: Returns `true` if GPA  `minPassingGrade`.
//* `getTotalStudents()`: Returns the current count of students.
//
//
//* **Static Initializer:**
//* Print: `"University class loaded and initialized"`
//* Print: `"Setting up university system..."`
//
//
//
//---
//
//### **2. Student Class (Non-Static Members)**
//
//This class represents individual students and their specific data.
//
//* **Static Variable:**
//* `nextStudentNumber` (int): Used for ID generation (Initial value: `1000`).
//
//
//* **Instance Variables:**
//* `studentId` (String): Auto-generated as `"STU-" + nextStudentNumber`.
//* `name` (String), `major` (String), `gpa` (double), `coursesEnrolled` (int).
//
//
//* **Constructors:**
//* `Student(String name, String major)`: For students with no initial GPA.
//* `Student(String name, String major, double initialGpa)`: For students with an existing GPA.
//
//
//* **Non-Static Methods:**
//* `displayStudentInfo()`: Prints all details including ID, Name, Major, GPA, and Enrolled Courses.
//* `updateGPA(double newGrade, int creditHours)`: Updates the student's GPA.
//* `enrollCourse(int creditHours)`: Increments the count of courses.
//* `isOnDeansList()`: Returns `true` if GPA  `3.5`.
//
//
//* **Initializers:**
//* **Static Initializer:** Print: `"Student class loaded and initialized"`.
//* **Instance Initializer:** Print: `"Creating a new student object..."` and initialize `coursesEnrolled = 0`, `gpa = 0.0`.
//
//
//
//---
//
//### **3. Main Method Execution Flow**
//
//Your program should follow these steps in order:
//
//1. **Start:** Print the main title.
//2. **Initial State:** Call `University.displayUniversityInfo()` before creating any objects.
//3. **Object Creation:** Create three students:
//* **Alice Johnson**, Computer Science (no initial GPA).
//* **Bob Smith**, Mathematics (initial GPA 3.2).
//* **Carol Davis**, Physics (initial GPA 3.8).
//
//
//4. **Operations:** * Enroll Alice in 3 credit hours  Update GPA to 3.7.
//* Enroll Bob in 4 credit hours  Update GPA to 3.9.
//* Enroll Carol in 3 credit hours  Update GPA to 3.6.
//
//
//5. **Display:** Call `displayStudentInfo()` for each student.
//6. **University Update:** Change academic year to `"2025-2026"`.
//7. **Validation:** Test passing grade logic for GPAs `1.5` and `2.5`.
//8. **Final Status:** Display updated university info and total student count.
//
//---
//
//### **Expected Output Highlights**
//
//* Class loading messages must appear automatically.
//* Student IDs should increment (STU-1000, STU-1001, etc.).
//* Status checks must show PASSING/FAILING or YES/NO based on the logic.
//
//---
//
//### **Bonus: GymMember Requirement (From last images)**
//
//If you also need the Gym assignment:
//
//* **Class:** `GymMember`
//* **Fields:** `memberId` (int), `memberName` (String), `joiningFee` (int, hardcoded 500), `totalMembers` (static).
//* **Logic:** Increment `totalMembers` in the constructor and display member details for Arjun (101), Neha (102), and Ravi (103).
//
//**Would you like me to provide the Java code implementation for the University Student Management System?**



class University {
	static {
		System.out.println("University class loaded and initialized");
		System.out.println("Setting up university system...");
	}
	static int totalStudents = 0;
	final static String UNIVERSITY_CODE = "UNI-2024";
	static String currentAcademicYear = "2024-2025";
	static double minPassingGrade = 2.0;

	static void displayUniversityInfo() {
		System.out.println("Total Students: " + totalStudents + " UNIVERSITY CODE: " + UNIVERSITY_CODE
				+ " Current Academic Year: " + currentAcademicYear + " Minimun Passing Grade: " + minPassingGrade);
	}

	static void updateAcademicYear(String newYear) {
		currentAcademicYear = newYear;
	}

	static boolean isPassingGrade(double gpa) {
		return gpa >= minPassingGrade;
	}

	static int getTotalStudents() {
		return totalStudents;
	}
}

class Student {
	static int nextStudentNumber = 1000;
	String studentId;
	String name;
	String major;
	double gpa;
	int coursesEnrolled;
	static {
		System.out.println("Student class loaded and initialized");
	}
	{
		System.out.println("Creating a new student object...");
		coursesEnrolled = 0;
		gpa = 0.0;
	}

	Student(String name, String major) {
		this.name = name;
		this.major = major;
		University.totalStudents += 1;
		nextStudentNumber += 1;
		studentId = "STU-" + nextStudentNumber;
	}

	Student(String name, String major, double initialGpa) {
		this.name = name;
		this.major = major;
		this.gpa = initialGpa;
		University.totalStudents += 1;
		nextStudentNumber += 1;
		studentId = "STU-" + nextStudentNumber;
	}

	void displayStudentInfo() {
		System.out.println("ID: " + studentId + " Name: " + name + " Major: " + major + " GPA: " + gpa
				+ "  Enrolled Courses: " + coursesEnrolled);
	}

	void enrollCourse(int creditHours) {
		coursesEnrolled++;
		System.out.println(name + " enrolled in a " + creditHours + " credit hour course");

	}

	void updateGPA(double newGrade, int creditHours) {
		gpa = newGrade;
		System.out.println(name + "'s gpa updates to " + gpa);
	}

	boolean isOnDeansList() {
		return gpa >= 3.5;
	}
}

public class University12Feb {
	public static void main(String[] args) {
		System.out.println("University Student Management System");
		University.displayUniversityInfo();

		Student s1 = new Student("Alice Johnson", "Computer Science");
		Student s2 = new Student("Bob Smith", "Mathematics", 3.2);
		Student s3 = new Student("Carol Davis", "Physics", 3.8);

		s1.enrollCourse(3);
		s1.updateGPA(3.7, 3);

		s2.enrollCourse(4);
		s2.updateGPA(3.9, 4);

		s3.enrollCourse(3);
		s3.updateGPA(3.6, 3);

		s1.displayStudentInfo();
		s2.displayStudentInfo();
		s3.displayStudentInfo();

		University.updateAcademicYear("2025-2026");
		System.out.println(University.isPassingGrade(1.5));
		System.out.println(University.isPassingGrade(2.5));

		University.displayUniversityInfo();

	}

}


// OUTPUT
//University Student Management System
//University class loaded and initialized
//Setting up university system...
//Total Students: 0 UNIVERSITY CODE: UNI-2024 Current Academic Year: 2024-2025 Minimun Passing Grade: 2.0
//Student class loaded and initialized
//Creating a new student object...
//Creating a new student object...
//Creating a new student object...
//Alice Johnson enrolled in a 3 credit hour course
//Alice Johnson's gpa updates to 3.7
//Bob Smith enrolled in a 4 credit hour course
//Bob Smith's gpa updates to 3.9
//Carol Davis enrolled in a 3 credit hour course
//Carol Davis's gpa updates to 3.6
//ID: STU-1001 Name: Alice Johnson Major: Computer Science GPA: 3.7  Enrolled Courses: 1
//ID: STU-1002 Name: Bob Smith Major: Mathematics GPA: 3.9  Enrolled Courses: 1
//ID: STU-1003 Name: Carol Davis Major: Physics GPA: 3.6  Enrolled Courses: 1
//false
//true
//Total Students: 3 UNIVERSITY CODE: UNI-2024 Current Academic Year: 2025-2026 Minimun Passing Grade: 2.0
//
//