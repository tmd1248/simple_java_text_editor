import java.io.File;  // Imports the File class
import java.io.FileWriter; // Imports the ability to write to a file
import java.io.IOException;  // Imports the IOException class to handle errors
import java.io.FileNotFoundException; // imports the FileNotFoundException to handle errors
import java.util.Scanner; // Imports the Scanner to take user input as to the name of the file

public class CreateFile {
  /* The first method, MakeFile, will attempt to create a new text file, which is guaranteed to be of type 'txt'. 
  * If the file already exists, the name will be stored regardless for later operations 
  * Given the bimodal operation here, there is a possibility for accidentally creating a new file instead of loading an existing one.*/  
  public static String MakeFile(Scanner inputScanner) {
    System.out.println("Please enter the name of the file you would like to open or create: ");
    String fileName = inputScanner.nextLine();
    fileName = fileName + ".txt";
    try {
      File textFile = new File(fileName);
      if (textFile.createNewFile()) {
        System.out.println("File created: " + textFile.getName());
      } else {
        System.out.println("File loaded: " + textFile.getName());
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return fileName;
  }
  /* This takes the filename above and will print the contents if it is not empty. 
   * If it is empty, it will notify the user 
   * This will be done multiple times potentially */
  public static void ReadFile(String fileName) {
    try {
      File textFile = new File(fileName);
      Scanner textReader = new Scanner(textFile);
      if(textReader.hasNextLine()){
        while (textReader.hasNextLine()) {
          String data = textReader.nextLine();
          System.out.println(data);
        }
      }
      else {
        System.out.println("This text file is empty");
      }
      textReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  /* This method will write to the file. It takes the central scanner, and the file name we're working with as arguments 
   * It first checks whether you'd like to replace or append the information already contained in the text file
   * then prompts the user to put in new information, all instances of this will be separated with a new line 
   */
  public static void WriteFile(String fileName, Scanner inputScanner) {
    System.out.println("Would you like to append or replace? type \"append\" to append or \"replace\" to replace.");
    String choice = inputScanner.nextLine();
    boolean choiceMade = false;
    boolean append = false;
    while(choiceMade == false) {
      switch(choice) {
        case "append": 
        append = true;
        choiceMade = true;
        break;
        case "replace": 
        choiceMade = true;
        break;
        default: 
        System.out.println("Sorry, that wasn't recognized. Remember, commands are case sensitive. please try again.");
        System.out.println("Would you like to append or replace? type \"append\" to append or \"replace\" to replace.");
        choice = inputScanner.nextLine(); 
      }

    }
    try {
      System.out.println("Enter the text you would like to write to the text file: ");
      String userText = inputScanner.nextLine();
      FileWriter Writer = new FileWriter(fileName, append);
      Writer.write(userText + "\n");
      Writer.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  } 
/* A fairly simple method that deletes the file with the given name assuming nothing else is using it. 
 * Will still only work with text files as that's decided in 'makefile', so hopefully less risky than it otherwise could be. 
 */
  public static void DeleteFile(String fileName) {
    File textFile = new File(fileName);
    if(textFile.delete()) {
      System.out.println("Deleted " + fileName);
    } else {
      System.out.println("We had some trouble deleting the file");
    }
  }
  public static void main(String[] args) {
    Scanner inputScanner = new Scanner(System.in);
    String fileName = MakeFile(inputScanner);
    boolean exit_program = false;
    boolean fileChosen = true;
    while(exit_program == false){
      if (fileChosen == true) {
        System.out.println("What would you like to do with this file? please type \"read\" or \"write\".");
        System.out.println("you can also type \"open\" to create or open a new file, or \"delete\" to delete the currently selected one.");
        System.out.println("when you are finished, type \"exit\" to leave.");
        String choice = inputScanner.nextLine();
        switch(choice) {
          case "read":
            ReadFile(fileName);
            break;
          case "write":
            WriteFile(fileName, inputScanner);
            break;
          case "open":
            fileName = MakeFile(inputScanner);
            break;
          case "delete":
            DeleteFile(fileName);
            fileChosen = false;
            break;
          case "exit": 
            exit_program = true;
            break;
          default: 
            System.out.println("Sorry, that wasn't recognized. Remember, commands are case sensitive. please try again.");
        }
          } else {
            System.out.println("No file chosen. type \"open\" to create a new file, or \"exit\" to leave.");
            String choice = inputScanner.nextLine();
            switch(choice) {
              case "open":
              fileName = MakeFile(inputScanner);
              fileChosen = true;
              break;
            case "exit": 
              exit_program = true;
              break;
            default: 
              System.out.println("Sorry, that wasn't recognized. Remember, commands are case sensitive. please try again.");              
            }
          } 
  }
  inputScanner.close();
  }
}