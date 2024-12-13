public class ConsoleStyler {

  private static String RESET = "\u001B[0m";
  private static String BOLD = "\u001B[1m";
  private static String WHITE_COLOR = "\u001B[37m";
  private static String RED_COLOR = "\u001B[41m";
  private static String GREEN_COLOR = "\u001B[42m";
  private static String BLUE_COLOR =  "\u001B[44m";

  public static void errorMsg(String message) {
    
    String errorMsg = WHITE_COLOR +  RED_COLOR + "ERROR:" + RESET;
    System.out.println("-".repeat(40));
    System.out.println(errorMsg + " " +  message);
    System.out.println("-".repeat(40) + "\n");
  }

  public static void successMsg(String message) {
    String successMsg = WHITE_COLOR +  GREEN_COLOR + " Success: " + RESET;
    System.out.println("-".repeat(40));
    System.out.print(successMsg + " " +  message + "\n");
    System.out.println("-".repeat(40));
  }

  public static void mainTitle(String title) {
    title = BOLD + WHITE_COLOR + BLUE_COLOR + title + RESET;
    System.out.println("-".repeat(70) + title + "-".repeat(80));
  }

  public static void blankLine(int count) {
    System.out.println("-".repeat(count));
  }

  public static void boldText(String text) {
    text = BOLD + text + RESET;
    System.out.print(text);
  }

}
