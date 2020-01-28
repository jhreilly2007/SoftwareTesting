/**Name : Janice Reilly
 * Task : Assignment 1 CS608
 * ID : 18971025
 * SugarTaxTestEP: Equivalence Partitions Black Box Testing
 */

package cs608;

import static org.testng.Assert.*;
import org.testng.annotations.*;
import cs608.SugarTax.TaxBand;
import static cs608.SugarTax.TaxBand.*;

public class SugarTaxTestEP {

   private static Object[][] testData = new Object[][] {
	      //    id, 			sugar, taxable,  expected
		//EP Tests
	      {"T1.1",  			  2500,  true,    NONE},
	      {"T1.2", 			      6500,  true,     LOW},
	      {"T1.3", 				 10000,  true,    HIGH},
	      {"T1.4",  			  -100, false, INVALID},
   };

   @DataProvider(name="testdata")
   public Object[][] getTestData() {
      return testData;
   }

   @Test(dataProvider = "testdata")
   public void test1( String id, int sugar, boolean taxable, TaxBand expected ) {
      assertEquals( SugarTax.calculateTaxBand(sugar,taxable), expected);
   }

}
