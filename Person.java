/**
 * @author mohamed_anwer
 * @see https://github.com/MoAnwer/FinalProject
 */

class Person {

  private int age;
  private double height;
  private String nationality;

  public Person(int age, double height, String nationality) {
    this.age = age;
    this.height = height;
    this.nationality = nationality;
  }

  public int getAge() {
    return age;
  }

  public double getHeight() {
    return height;
  }

  public String getNationality() {
    return nationality;
  }
  
  public String displayInfo() {
    return "Age: " + this.getAge() + ", height: " + this.getHeight() + ", nationality: " + this.getNationality();
  }

}