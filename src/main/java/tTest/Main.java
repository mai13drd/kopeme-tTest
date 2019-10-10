package tTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.math3.stat.inference.TTest;
import org.apache.commons.math3.stat.inference.TestUtils;

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

         /*
          * siehe doc der methode!!! The test does not assume that the underlying popuation variances are equal and it uses approximated degrees of freedom computed from the sample
          * data to compute the p-value. The t-statistic used is as defined in t(double [], double []) and the Welch-Satterthwaite approximation to the degrees of freedom is used,
          * as described here. To perform the test under the assumption of equal subpopulation variances, use homoscedasticTTest(double [], double []).
          */
         double significanceLevel = getSignificanceLevel(valueList_1, valueList_2);
         System.out.println(significanceLevel);
         // boolean unequal = perform_tTest(valueList_1, valueList_2, significanceLevel);

         boolean unequal = perform_tTest(valueList_1, valueList_2, 0.01);
         System.out.println(unequal);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      // double[] vals1 = new double[] { 1.0, 1.1, 1.2 };
      // double[] vals2 = new double[] { 0.9, 0.8, 0.9 };

      /*
       * Performs a two-sided t-test evaluating the null hypothesis that sample1 and sample2 are drawn from populations with the same mean, with significance level alpha. Returns
       * true iff the null hypothesis that the means are equal can be rejected with confidence 1 - alpha.
       */
      // System.out.println(TestUtils.tTest(vals1, vals2, 0.05));
      // System.out.println(TestUtils.tTest(vals1, vals2, 0.15));
      // System.out.println(TestUtils.tTest(vals1, vals2, 0.036));

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
   private static double getSignificanceLevel(double[] sample_1, double[] sample_2) {

      TTest tTest = new TTest();
      return tTest.tTest(sample_1, sample_2);
   }

   private static boolean perform_tTest(double[] sample_1, double[] sample_2, double significanceLevel) {

      TTest tTest = new TTest();
      return tTest.tTest(sample_1, sample_2, significanceLevel);
   }

}
