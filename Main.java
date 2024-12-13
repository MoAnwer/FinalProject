/**
 * @author mohamed_anwer
 * @see https://github.com/MoAnwer/FinalProject
 */

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    
    Course courseController = new Course();

    boolean programEnd = false;

    while(!programEnd) {

      ConsoleStyler.mainTitle(" Student System ");
      
      System.out.println("\n1- Add new student.");
      System.out.println("2- Add new Course.");
      System.out.println("3- Register student in course.");
      System.out.println("4- Show students information.");
      System.out.println("5- Show all courses.");
      System.out.println("6- Show students with courses.");
      System.out.println("7- Exit.");

      ConsoleStyler.boldText("\nEnter number of operation: ");

      int operation = scanner.nextInt();

      switch (operation) {
        case 1:
          addStudent(scanner, courseController);
        break;
        case 2: 
          addCourse(scanner);
        break;
        case 3: 
          showRegisrationMenu(scanner);
        break;
        case 4: 
          displayStudentsData();
        break;
        case 5:
          displayCoursesData();
        break;
        case 6:
          displayRegistrationData(courseController);
        break;
        case 7:
          programEnd = true;
        break;
        default:
          ConsoleStyler.errorMsg("Invalid operation number !!");
        break;
      }

    }

  }

  public static void addStudent(Scanner scanner, Course course) {
    scanner.nextLine();

    System.out.print("Enter student name: ");
    String name = scanner.nextLine();

    System.out.print("Enter student age: ");
    int age = scanner.nextInt();

    System.out.print("Enter student height: ");
    double height = scanner.nextDouble();

    scanner.nextLine();

    System.out.print("Enter student nationality: ");
    String nationality = scanner.nextLine();

    System.out.print("Enter student university number: ");
    long universityNumber = scanner.nextLong();
    scanner.nextLine();

    System.out.print("Enter student specializtion: ");
    String specializtion = scanner.nextLine();

    Student student = new Student(name, age, height, nationality, universityNumber, specializtion);

    course.addStudent(student);

    if(student.insertStudentToFile()) {
      ConsoleStyler.successMsg("Student added successfully !!");
    } else {
      ConsoleStyler.errorMsg("Student not added :(");
    }
    
  }
  
  public static void addCourse(Scanner scanner) {
    ConsoleStyler.blankLine(40);
    ConsoleStyler.boldText("Add new Course: ");
    ConsoleStyler.blankLine(1);

    scanner.nextLine();
    System.out.print("\sEnter course name: ");
    String name = scanner.nextLine();

    System.out.print("\sEnter course code: ");
    String code = scanner.nextLine();

    System.out.print("\sEnter course hours: ");
    int hours = scanner.nextInt();

    Course course = new Course(name, code, hours);

    if(course.insertCourseToFile()) {
      ConsoleStyler.successMsg("Course added successfully !!");
    } else {
      ConsoleStyler.errorMsg("Course not added :(");
    }

  }

  public static void displayStudentsData() {
    ConsoleStyler.blankLine(50);
    System.out.println("Students Data: ");
    ConsoleStyler.blankLine(50);
    System.out.println(Student.studentsData());
  }
  
  public static void displayCoursesData() {
    ConsoleStyler.blankLine(50);
    System.out.println("Courses Data: ");
    ConsoleStyler.blankLine(50);
    System.out.print(Course.coursesData());
    ConsoleStyler.blankLine(140);
  }

  public static void showRegisrationMenu(Scanner scanner) {
    displayStudentsData();
    displayCoursesData();

    System.out.print("Enter Student university number: ");
    long universityNumber = scanner.nextLong();

    scanner.nextLine();

    System.out.print("Enter course code: ");
    String courseCode = scanner.nextLine();

    Course course = new Course();

    if(course.saveStudentRegistrationData(universityNumber, courseCode)) {
      ConsoleStyler.successMsg("Registration success !!");
    }
  } 

  public static void displayRegistrationData(Course course) {
    ConsoleStyler.blankLine(40);
    ConsoleStyler.boldText("Registration Data: \n");
    ConsoleStyler.blankLine(40);
    course.studentCoursesData();
    System.out.println("\n");
  }
}