package tTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.math3.stat.inference.TTest;

public class Main {

   public static void main(String[] args) {

      String filePath_1 = "perfTestValues" + File.separator + "before.txt";
      String filePath_2 = "perfTestValues" + File.separator + "after.txt";

      try {
         double[] valueList_1 = readDoublesFromFile(filePath_1);
         double[] valueList_2 = readDoublesFromFile(filePath_2);

         // for (int i = 0; i < valueList_1.length; i++) {
         // System.out.println(i + " " + valueList_1[i]);
         // }
         // System.out.println("------------------------------------");
         // for (int i = 0; i < valueList_2.length; i++) {
         // System.out.println(i + " " + valueList_2[i]);
         // }

         double significanceLevel = getSignificanceLevel(valueList_1, valueList_2);
         // System.out.println(significanceLevel);
         boolean unequal = perform_tTest(valueList_1, valueList_2, significanceLevel);
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

   private static double getSignificanceLevel(double[] sample_1, double[] sample_2) {

      TTest tTest = new TTest();
      return tTest.tTest(sample_1, sample_2);
   }

   private static boolean perform_tTest(double[] sample_1, double[] sample_2, double significanceLevel) {

      TTest tTest = new TTest();
      return tTest.tTest(sample_1, sample_2, significanceLevel);
   }

}
