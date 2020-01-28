/**Name : Janice Reilly
 * Task : Assignment 1 
 * SugarTaxTestBVA: EP & BVA Black Box Testing
 */

package cs608;

import static org.testng.Assert.*;
import org.testng.annotations.*;
import cs608.SugarTax.TaxBand;
import static cs608.SugarTax.TaxBand.*;

public class SugarTaxTestBVA {

   private static Object[][] testData = new Object[][] {
	      //    id, 			sugar, taxable,  expected
		//EP Tests
	      {"T1.1",  			  2500,  true,    NONE},
	      {"T1.2", 			      6500,  true,     LOW},
	      {"T1.3", 				 10000,  true,    HIGH},
	      {"T1.4",  			  -100, false, INVALID},
	     //BV Tests 
	      {"T2.1",  		 		 0,  true,    NONE},
	      {"T2.2",   			  4999,  true,    NONE},
	      {"T2.3",   			  5000,  true,     LOW},
	      {"T2.4",   			  7999,  true,     LOW},
	      {"T2.5",   			  8000,  true,    HIGH},
	      {"T2.6",   Integer.MAX_VALUE, false,    NONE},
	      {"T2.7",   Integer.MIN_VALUE, false, INVALID},
	      {"T2.8",   			    -1, false, INVALID},
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
