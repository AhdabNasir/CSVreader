import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EmployeeSystem {

 public static void main(String[] args) {

  String csvFile = "";
  try {
   csvFile = args[0];
  } catch (Exception e) {
   System.out.println("Please provide the file name");
   System.exit(0);
  }

  String line = "";
  String csvSplitBy = ",";

  Map < String, Integer > NameOccurence = new HashMap < String, Integer > ();
  Map < String, Integer > SalaryOccurence = new HashMap < String, Integer > ();

  try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
   int count = 1;
   while ((line = br.readLine()) != null) {
    String[] data = line.split(csvSplitBy);

    if (data.length < 4 || data[0].equalsIgnoreCase("Name"))
     continue;

    if (NameOccurence.get(data[0].toLowerCase()) != null) {
     count = NameOccurence.get(data[0].toLowerCase()) + 1;
     NameOccurence.put(data[0].toLowerCase(), count);
    } else {
     NameOccurence.put(data[0].toLowerCase(), 1);
    }

    if (SalaryOccurence.get(data[1]) != null) {
     count = SalaryOccurence.get(data[1]) + 1;
     SalaryOccurence.put(data[1], count);
    } else {
     SalaryOccurence.put(data[1], 1);
    }
   }
  } catch (IOException e) {
   e.printStackTrace();
  }

  try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

   while ((line = br.readLine()) != null) {
    String[] data = line.split(csvSplitBy);

    if (data.length < 4 || data[0].equalsIgnoreCase("Name"))
     continue;

    float point = Float.parseFloat(data[3]);

    if (data[2].equalsIgnoreCase("experienced") && SalaryOccurence.get(data[1]) == 1 &&
     NameOccurence.get(data[0].toLowerCase()) == 1) {
     if (point >= 24) {
      System.out.println("Can be promoted as points is: " + point + " which is greater or equal to 24");
      System.out.println("and there current salary is " + data[1]);
      System.out.println("and there name is: " + data[0]);

      if (data.length > 4 && data[4] != null) {
       System.out.print(" [" + data[4] + "]");
      } else {}
      
     } else {
      System.out.println("Employee with name: " + data[0] + " salary: " + data[1] + " can't be promoted as points: " + point +
       " is smaller than 24");
     }
    } else if (data[2].equalsIgnoreCase("intern")) {
     System.out.println("Employee with name: " + data[0] + " salary: " + data[1] + " can't be promoted as they are an intern");
    } else if (NameOccurence.get(data[0].toLowerCase()) > 1) {
     System.out.println("Employee with name: " + data[0] + " and salary: " + data[1] +
      " can't be promoted as there is more than employee with the same name: " + data[0].toLowerCase());
    } else if (SalaryOccurence.get(data[1]) > 1) {
     System.out.println("Employee with name: " + data[0] + " and salary: " + data[1] +
      " can't be promoted as there is more than employee with the same salary: " + data[1]);
    }

    System.out.println();
   }
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
}