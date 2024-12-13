/**
 * @author mohamed_anwer
 * @see https://github.com/MoAnwer/FinalProject
 */

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Course {

  private String courseName;
  private String courseCode;
  private int courseHoursNumber; 
  private ArrayList<Student> students;
  private ArrayList<Long> SUNs;
  private ArrayList<String> coursesCodes;
  private static final String COURSE_FILE = "course.txt";
  private static final String STUDENT_COURSE_FILE = "studentCourse.txt";

  public Course(String courseName, String courseCode, int courseHoursNumber) {
    this.courseName = courseName;
    this.courseCode = courseCode;
    this.courseHoursNumber = courseHoursNumber;
    this.students = new ArrayList<Student>();
    this.SUNs = new ArrayList<Long>();
    this.coursesCodes = new ArrayList<String>();
  }
  
  public Course(){
    this.students = new ArrayList<Student>();
    this.SUNs = new ArrayList<Long>();
    this.coursesCodes = new ArrayList<String>();
  }

  public String getCourseName() {
    return courseName;
  }

  public String getCourseCode() {
    return courseCode;
  }

  public int getCourseHoursNumber() {
    return courseHoursNumber;
  }

  public void addStudent(Student student) {
    students.add(student);
  }

  public String dispalyInfo() {
    return "Course name: " + this.getCourseName() + ", Course Code: " + this.getCourseCode() + ", Course hours: " + this.getCourseHoursNumber();
  }

  public boolean insertCourseToFile() {
    boolean insertSuccess = false; 
    try {
      FileWriter fileWriter = new FileWriter(Course.COURSE_FILE, true);
      PrintWriter printer = new PrintWriter(fileWriter);

      printer.append(this.dispalyInfo() + "\n");
      printer.flush();

      printer.close();

      insertSuccess = true;

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    return insertSuccess;
  }

  public static String coursesData() {
    String coursesData = "";
    try {
      Scanner scanner = new Scanner(new File(Course.COURSE_FILE));
      while (scanner.hasNext()) {
        coursesData += scanner.nextLine() + "\n";
      }
      scanner.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return coursesData;
  }


  public String registrationData(long universityNumber, String courseCode) {
    return "SUN: " + universityNumber + ", Course code: " + courseCode  + "\n";
  }

  public Student getStudentByUN(long universityNumber) {
    Student fetchedStudent = new Student();
    for (Student student : students) {
      if (student.getUniversityNumber() == universityNumber) {
        fetchedStudent = student;
      }
    }
    return fetchedStudent;
  }

  public boolean saveStudentRegistrationData(long universityNumber, String courseCode) {

    boolean insertSuccess = false; 
    
    try {
      
      Scanner scanner = new Scanner(new File(Course.COURSE_FILE));

      while (scanner.hasNextLine()) {
        
        String line = scanner.nextLine();

        int i = line.indexOf("de:");
          
        if(i != -1) {
          int startPoint = ++i;
          ++startPoint;
          ++startPoint;
          if(startPoint != -1) {
            int endPoint = line.lastIndexOf(", ");
            String codeStr = line.substring(startPoint, endPoint).trim();
            this.coursesCodes.add(codeStr);
          }

        }
      }

      Scanner scanner2 = new Scanner(new File("studentinfo.txt"));
      
      while (scanner2.hasNextLine()) {
        String line = scanner2.nextLine().replace("university number", "SUN");
        int i = line.indexOf("SUN:");
        
        if(i != -1) {
          int startPoint = ++i;
          ++startPoint;
          ++startPoint;
          ++startPoint;

          if(startPoint != -1) {
            int endPoint = line.lastIndexOf(", ");
            String sunNumber = line.substring(startPoint, endPoint).trim();
            this.SUNs.add(Long.parseLong(sunNumber));
          }
        }
      }

      scanner.close();
      scanner2.close();

    } catch (IOException e)  {
      System.out.println(e.getMessage());
    }

    try {
      FileWriter fileWriter = new FileWriter(Course.STUDENT_COURSE_FILE, true);
      PrintWriter printer = new PrintWriter(fileWriter);


      boolean SUNfounded = false;
      for (Long sun : SUNs) {
        long sunCase = (long)sun;
        if(sunCase == universityNumber) {
          SUNfounded = true;
        }
      }

      if(!SUNfounded) {
        ConsoleStyler.errorMsg("Incorrect university number !!.");
        System.out.println(SUNs);
        return false;
      }

      boolean codeFounded = false;

      for (String code : coursesCodes) {        
        if(code.equals(courseCode.trim())) {
          codeFounded = true;
        }
      }

      if(!codeFounded) {
        ConsoleStyler.errorMsg("Incorrect course code !!.");
        return false;
      }


      printer.append(this.registrationData(universityNumber, courseCode));
      printer.flush();

      printer.close();

      insertSuccess = true; 

    } catch (IOException e)  {
      System.out.println(e.getMessage());
    }

    return insertSuccess;
  }


  public void studentCoursesData() {

    this.getStudentCourseInfoFromFile();
    this.displayRegistrationData();

  }


  public void getStudentCourseInfoFromFile() {
    try {

      Scanner scanner = new Scanner(new File(Course.STUDENT_COURSE_FILE));

      while (scanner.hasNextLine()) {
        
        String line = scanner.nextLine();
        int courseCodeIndex = line.lastIndexOf(":");
        String code = line.substring(++courseCodeIndex).trim();

        int i = line.indexOf(":", 3);

        if(i != -1) {
          int startPoint = ++i;

          if(startPoint != -1) {
            int endPoint = line.indexOf(", ");
            String sunNumber = line.substring(startPoint, endPoint).trim();

            this.SUNs.add(Long.parseLong(sunNumber));
            this.coursesCodes.add(code);
          }
        }
      }
      scanner.close();

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }  
  }

  public void displayRegistrationData() {
    for (int i = 0; i < this.SUNs.size(); i++) {
      System.out.println(getStudentFromFileBy(SUNs.get(i)) + ", Course code: " + coursesCodes.get(i));
    }
  }

  public String studentInfoData() {
    String studentsInfo = "";
    try {
      Scanner scanner = new Scanner(new File("studentinfo.txt"));
      while (scanner.hasNextLine()) {    
        studentsInfo += scanner.nextLine() + "\n";
      }
      scanner.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return studentsInfo;
  }

  public ArrayList<Long> parsingStudentInfo(ArrayList<String> coursesCodes) {
    ArrayList<Long> universityNumber = new ArrayList<Long>();

    String[] studentsDataArray = this.studentInfoData().split("\n");

    for (int i = 0; i < studentsDataArray.length; i++) {
      String studentData = studentsDataArray[i];
      System.out.println(studentData);
      String newString = studentData.replaceAll("university number:", "SUN:");

      int universityNumberIndex = newString.indexOf("N:");
      ++universityNumberIndex;

      int end = newString.lastIndexOf(",");

      if(universityNumberIndex != -1) {
        String universityNumberStr = newString.substring(++universityNumberIndex,end).trim();
        universityNumber.add(Long.parseLong(universityNumberStr));
      }
    }

   return universityNumber;
  }

  public String getStudentFromFileBy(long sun) {
    String student = "";
    
    String[] studentsDataArray = this.studentInfoData().split("\n");

    for (int i = 0; i < studentsDataArray.length; i++) {
      String studentData = studentsDataArray[i];
      String newString = studentData.replaceAll("university number:", "SUN:");

      int universityNumberIndex = newString.indexOf("N:");
      ++universityNumberIndex;

      int end = newString.lastIndexOf(",");

      if(universityNumberIndex != -1) {
        String universityNumberStr = newString.substring(++universityNumberIndex,end).trim();

        if(Long.parseLong(universityNumberStr) == sun) {
          student += studentData;
        }
        
      }

    }

    return student;
  }
}