import javax.swing.JOptionPane;
import java.io.*;
import java.util.Scanner;

class member {
    String forename;
    String surname;
    double distance;
}

class Main {
  public static void main(String[] args) throws IOException {
    
    member [] Members = getInfo();
    double furthest = findFurthest(Members);
    displayFurthest(furthest);
    writeToFile(Members, furthest);

}

public static void writeToFile(member [] tempMembers, double tempFurthest) throws IOException {

    String fileName = "results.txt";
    File writeFile = new File (fileName);

    if(!writeFile.exists()) {
        writeFile.createNewFile();
    }

    FileWriter fw = new FileWriter(writeFile.getAbsoluteFile());
    BufferedWriter bw = new BufferedWriter(fw);

    bw.write("The prize winning members are:");
    bw.write("\n");
    for (int index = 0; index < tempMembers.length; index ++) {
        if (tempMembers[index].distance > (0.7*tempFurthest)) {
          bw.write(tempMembers[index].forename + " " + tempMembers[index].surname);
          bw.write("\n");
        }
    }

    int marathons = 0;

    bw.write("\n");
    bw.write("The number of whole marathons walked by each member is:");
    bw.write("\n");
    
    for (int index = 0; index < tempMembers.length; index ++) {
        marathons = (int) Math.floor(tempMembers[index].distance / 26.22);
        bw.write(tempMembers[index].forename + "," + tempMembers[index].surname + "," + marathons);
        bw.write("\n");
    }

    bw.newLine();
    bw.close();
}


public static void displayFurthest(double tempFurthest) {   
    System.out.println("The furthest distance walked was " + tempFurthest + " miles.");
}


public static double findFurthest(member [] tempMembers) {

    double tempFurthest = tempMembers[0].distance;
    
    for (int index = 1; index < tempMembers.length; index++) {
        if(tempMembers[index].distance > tempFurthest) {
          tempFurthest = tempMembers[index].distance;
        }
    }
    return tempFurthest;

}


public static member[] getInfo() {

  int noInFile = 0;
  Scanner fileScanner = null;
  String fileName = "members.txt";


  try { 
    fileScanner = new Scanner (new BufferedReader (new FileReader (fileName)));
    fileScanner.useDelimiter("[\\r\\n,]+");
    while (fileScanner.hasNext()) {
      noInFile = noInFile + 1;
      fileScanner.next();
      }
    } catch (FileNotFoundException error) {
        System.out.println("File not found, fix the code and try again!");
    } finally {
        if(fileScanner != null) {
          fileScanner.close();
        }
    }
  

  int numberInRecords= noInFile/3;
  int index = 0;
  member [] tempMembers = new member[numberInRecords];

  try { 
    fileScanner = new Scanner (new BufferedReader (new FileReader (fileName)));
    fileScanner.useDelimiter("[\\r\\n,]+");
    while (fileScanner.hasNext()) {
      tempMembers[index] = new member();
      tempMembers[index].forename = fileScanner.next();
      tempMembers[index].surname = fileScanner.next();
      tempMembers[index].distance = fileScanner.nextDouble();
      index = index + 1;
      }
    } catch (FileNotFoundException error) {
        System.out.println("File not found, fix the code and try again!");
    } finally {
        if(fileScanner != null) {
          fileScanner.close();
        }
  }
  return tempMembers;
}
}

