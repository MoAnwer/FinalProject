/**
 * @author mohamed_anwer
 * @see https://github.com/MoAnwer/FinalProject
 */

import java.io.*;

class Student extends Person {

  private String name;
  private long universityNumber;
  private String specializtion;
  private static final String STUDENT_FILE = "studentInfo.txt";

  public Student(String name, int age, double height, String nationality, long universityNumber, String specializtion) {
    super(age, height, nationality);
    this.name = name;
    this.universityNumber = universityNumber;
    this.specializtion = specializtion;
  }

  public Student(){
    super(0, 0 ,"");
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSpecializtion(String specializtion) {
    this.specializtion = specializtion;
  }

  public void setUniversityNumber(long universityNumber) {
    this.universityNumber = universityNumber;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return super.getAge();
  }

  public double getHeight() {
    return super.getHeight();
  }

  public String getNationality() {
    return super.getNationality();
  }

  public String getSpecializtion() {
    return specializtion;
  }

  public long getUniversityNumber() {
    return universityNumber;
  }

  public String displayInfo() {
    return "Name: " + this.getName() + ", Age: " + this.getAge() + ", height: " + this.getHeight() + ", nationality: " + this.getNationality() + ", university number: " + this.getUniversityNumber() + ", specializtion: " + this.getSpecializtion();
  }

  public boolean insertStudentToFile() {
    boolean insertSuccess;

    try {

      BufferedWriter writer = new BufferedWriter(new FileWriter(Student.STUDENT_FILE, true));

      writer.append(this.displayInfo() + "\n");
      writer.flush();

      writer.close();

      insertSuccess = true;

    } catch (IOException e) {
      
      System.out.println("ERROR: " + e.getMessage());
      insertSuccess = false;
    }

    return insertSuccess;
  }


  public static String studentsData() {
    String studentData = "";
    try {

      BufferedReader reader = new BufferedReader(new FileReader(Student.STUDENT_FILE));
      String line = reader.readLine();
      while(line != null) {
        studentData += line + "\n";
        line = reader.readLine();
      }
      reader.close();
      
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return studentData;
  }

}