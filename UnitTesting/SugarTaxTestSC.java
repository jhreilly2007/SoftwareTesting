/**Name : Janice Reilly
 * Task : Assignment 1 CS608
 * ID : 18971025
 * SugarTaxTestSC: Statement Coverage White Box Testing including Black Box Testing
 */

package cs608;

import static org.testng.Assert.*;
import org.testng.annotations.*;
import cs608.SugarTax.TaxBand;
import static cs608.SugarTax.TaxBand.*;

public class SugarTaxTestSC {

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
		   //Combinations
	      {"T3.1",  			  6500, false,    NONE},
	      {"T3.2",  			  2500, false,    NONE},
	      //SC
	      {"T4.1", 				   7234, true,     LOW},

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
