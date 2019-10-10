package tTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.math3.stat.inference.TTest;
//import org.apache.commons.math3.stat.inference.TestUtils;

public class Main {

   public static void main(String[] args) {

      String filePath_1 = "perfTestValues" + File.separator + "measurementData_1.txt";
      String filePath_2 = "perfTestValues" + File.separator + "measurementData_2.txt";

      try {
         double[] valueList_1 = readDoublesFromFile(filePath_1);
         double[] valueList_2 = readDoublesFromFile(filePath_2);

         // double significanceLevel = getSignificanceLevel(valueList_1, valueList_2);
         // System.out.println(significanceLevel);
         // boolean unequal = perform_tTest(valueList_1, valueList_2, significanceLevel);

         boolean unequal = perform_tTest(valueList_1, valueList_2, 0.01);
         System.out.println(unequal);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

   /**
    * @param filePath_1
    * @throws IOException
    */
   private static double[] readDoublesFromFile(String filePath) throws IOException {

      List<String> stringList = Files.readAllLines(Paths.get(filePath));
      double[] doubles = new double[stringList.size()];

      for (int i = 0; i < stringList.size(); i++) {

         doubles[i] = Double.parseDouble(stringList.get(i));
      }
      return doubles;
   }

   /*
    * Determines the significanceLevel, from which on the values are considered different
    */
   // private static double getSignificanceLevel(double[] sample_1, double[] sample_2) {
   //
   // TTest tTest = new TTest();
   // return tTest.tTest(sample_1, sample_2);
   // }

   private static boolean perform_tTest(double[] sample_1, double[] sample_2, double significanceLevel) {

      TTest tTest = new TTest();
      return tTest.tTest(sample_1, sample_2, significanceLevel);
   }
}
