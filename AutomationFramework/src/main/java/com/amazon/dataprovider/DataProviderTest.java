package com.amazon.dataprovider;

import com.amazon.constants.FrameworkConstants;
import static io.github.sskorol.data.TestDataReader.use;
import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.JsonReader;
import one.util.streamex.StreamEx;

/**
 * Class containing all Data Providers to be applied for the Tests in Framework.
 * 
 * @since 15-Oct-2022
 * 
 * @author Suganya Jothiramalingam
 * @see FrameworkConstants
 */
public class DataProviderTest {
	/**
	 * Data Provider to retrieve the Amazon Test data from Json file using the interface {@link DataSupplier}  
	 * @return Stream of the Test Data POJO class
	 */
	@DataSupplier(runInParallel = true)
	public StreamEx<AmazonTestData> getAmazonJSONDataSupplier() {
		
		return use(JsonReader.class)
				.withTarget(AmazonTestData.class)
				.withSource(FrameworkConstants.getAMAZONJSONFILEPATH())
				.read();
	}
	
}