/**
 * 
 */
package gov.noaa.pmel.dashboard.test.shared;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gov.noaa.pmel.dashboard.shared.DashboardCruiseWithData;
import gov.noaa.pmel.dashboard.shared.DashboardUtils;
import gov.noaa.pmel.dashboard.shared.UserWoce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

/**
 * @author Karl Smith
 */
public class DashboardCruiseWithDataTest {

	/**
	 * Test method for {@link gov.noaa.pmel.dashboard.shared.DashboardCruiseWithData#DashboardCruiseWithData()}.
	 */
	@Test
	public void testDashboardCruiseWithData() {
		DashboardCruiseWithData cruiseData = new DashboardCruiseWithData();
		assertNotNull( cruiseData );
	}

	/**
	 * Test method for {@link gov.noaa.pmel.dashboard.shared.DashboardCruise#getOwner()}
	 * and {@link gov.noaa.pmel.dashboard.shared.DashboardCruise#setOwner(java.lang.String)}.
	 */
	@Test
	public void testSetGetOwner() {
		String myUsername = "SocatUser";
		DashboardCruiseWithData cruiseData = new DashboardCruiseWithData();
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getOwner());
		cruiseData.setOwner(myUsername);
		assertEquals(myUsername, cruiseData.getOwner());
		cruiseData.setOwner(null);
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getOwner());
	}

	/**
	 * Test method for {@link gov.noaa.pmel.dashboard.shared.DashboardCruise#getUploadFilename()}
	 * and {@link gov.noaa.pmel.dashboard.shared.DashboardCruise#setUploadFilename(java.lang.String)}.
	 */
	@Test
	public void testSetGetFilename() {
		String myFilename = "agsk20031205_revised.tsv";
		DashboardCruiseWithData cruiseData = new DashboardCruiseWithData();
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getUploadFilename());
		cruiseData.setUploadFilename(myFilename);
		assertEquals(myFilename, cruiseData.getUploadFilename());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getOwner());
		cruiseData.setUploadFilename(null);
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getUploadFilename());
	}

	/**
	 * Test method for {@link gov.noaa.pmel.dashboard.shared.DashboardCruise#getVersion()}
	 * and {@link gov.noaa.pmel.dashboard.shared.DashboardCruise#setVersion(java.lang.String)}.
	 */
	@Test
	public void testSetGetVersion() {
		String myVersion = "3.0";
		DashboardCruiseWithData cruiseData = new DashboardCruiseWithData();
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getVersion());
		cruiseData.setVersion(myVersion);
		assertEquals(myVersion, cruiseData.getVersion());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getUploadFilename());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getOwner());
		cruiseData.setVersion(null);
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getVersion());
	}

	/**
	 * Test method for {@link gov.noaa.pmel.dashboard.shared.DashboardCruise#getExpocode()}
	 * and {@link gov.noaa.pmel.dashboard.shared.DashboardCruise#setExpocode(java.lang.String)}.
	 */
	@Test
	public void testSetGetExpocode() {
		String myExpocode = "AGSK20031205";
		DashboardCruiseWithData cruiseData = new DashboardCruiseWithData();
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getExpocode());
		cruiseData.setExpocode(myExpocode);
		assertEquals(myExpocode, cruiseData.getExpocode());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getVersion());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getUploadFilename());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getOwner());
		cruiseData.setExpocode(null);
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getExpocode());
	}

	/**
	 * Test method for {@link gov.noaa.pmel.dashboard.shared.DashboardCruiseWithData#getPreamble()}
	 * and {@link gov.noaa.pmel.dashboard.shared.DashboardCruiseWithData#setPreamble(java.util.ArrayList)}.
	 */
	@Test
	public void testSetGetPreamble() {
		ArrayList<String> myPreamble = new ArrayList<String>(Arrays.asList(
				"Cruise Expocode: AGSK20031205",
				"Cruise Name: SKO313",
				"Ship/Vessel Name: Skogafoss",
				"Principal Investigator(s): Rik Wanninkhof"
		));
		DashboardCruiseWithData cruiseData = new DashboardCruiseWithData();
		assertEquals(0, cruiseData.getPreamble().size());
		cruiseData.setPreamble(myPreamble);
		assertEquals(myPreamble, cruiseData.getPreamble());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getExpocode());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getVersion());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getUploadFilename());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getOwner());
		cruiseData.setPreamble(null);
		assertEquals(0, cruiseData.getPreamble().size());
	}

	/**
	 * Test method for {@link gov.noaa.pmel.dashboard.shared.DashboardCruiseWithData#getDataValues()}
	 * and {@link gov.noaa.pmel.dashboard.shared.DashboardCruiseWithData#setDataValues(java.util.ArrayList)}.
	 */
	@Test
	public void testSetGetDataValues() {
		String[][] observations = {
				{ "2003-12-05 22:12", "337.28101", "64.10700", "26.910", 
					"5.410", "5.700", null, "1026.500", "373.740" },
				{ "2003-12-05 22:18", "337.23901", "64.09700", "28.360", 
					"5.390", "5.680", null, "1026.100", "374.390" },
				{ "2003-12-05 22:24", "337.20499", "64.08300", "28.700", 
					"5.440", "5.730", null, "1026.100", "374.510" },
				{ "2003-12-05 22:30", "337.17499", "64.06900", "28.690", 
					"5.630", "5.920", null, "1025.800", "372.710" },
				{ "2003-12-05 22:36", "337.14499", "64.05500", "28.750", 
					"5.710", "6.000", null, "1025.900", "370.480" }
		};
		ArrayList<ArrayList<String>> dataValues = 
				new ArrayList<ArrayList<String>>(observations.length);
		for (int k = 0; k < observations.length; k++)
			dataValues.add(new ArrayList<String>(Arrays.asList(observations[k])));

		DashboardCruiseWithData cruiseData = new DashboardCruiseWithData();
		assertEquals(0, cruiseData.getDataValues().size());
		cruiseData.setDataValues(dataValues);
		assertEquals(dataValues, cruiseData.getDataValues());
		assertEquals(0, cruiseData.getPreamble().size());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getExpocode());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getVersion());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getUploadFilename());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getOwner());
		cruiseData.setDataValues(null);
		assertEquals(0, cruiseData.getDataValues().size());
	}

	/**
	 * Test method for {@link gov.noaa.pmel.dashboard.shared.DashboardCruise#getColWoceThrees()}
	 * and {@link gov.noaa.pmel.dashboard.shared.DashboardCruise#setColWoceThrees(java.util.ArrayList)}.
	 */
	@Test
	public void testSetGetWoceThreeRowIndices() {
		UserWoce[][] woceThreeIndices = {
				{ new UserWoce(2, "WOCE_CO2_water"), new UserWoce(6, "WOCE_CO2_water") },
				{ },
				{ new UserWoce(6, "WOCE_CO2_water") },
				{ new UserWoce(3, "WOCE_CO2_water"), new UserWoce(0, "WOCE_CO2_water"), new UserWoce(6, "WOCE_CO2_water") },
				{ }
		};
		ArrayList<HashSet<UserWoce>> woceThreeRowSets = 
				new ArrayList<HashSet<UserWoce>>(woceThreeIndices.length);
		for (int k = 0; k < woceThreeIndices.length; k++)
			woceThreeRowSets.add(new HashSet<UserWoce>(Arrays.asList(woceThreeIndices[k])));

		DashboardCruiseWithData cruiseData = new DashboardCruiseWithData();
		assertEquals(0, cruiseData.getColWoceThrees().size());
		cruiseData.setColWoceThrees(woceThreeRowSets);
		assertEquals(woceThreeRowSets, cruiseData.getColWoceThrees());
		assertEquals(0, cruiseData.getDataValues().size());
		assertEquals(0, cruiseData.getPreamble().size());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getExpocode());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getVersion());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getUploadFilename());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getOwner());
		cruiseData.setColWoceThrees(null);
		assertEquals(0, cruiseData.getColWoceThrees().size());
	}

	/**
	 * Test method for {@link gov.noaa.pmel.dashboard.shared.DashboardCruise#getColWoceFours()}
	 * and {@link gov.noaa.pmel.dashboard.shared.DashboardCruise#setColWoceFours(java.util.ArrayList)}.
	 */
	@Test
	public void testSetGetWoceFourRowIndices() {
		UserWoce[][] woceFourIndices = {
				{ },
				{ new UserWoce(1, "WOCE_CO2_water"), new UserWoce(4, "WOCE_CO2_water") },
				{ new UserWoce(5, "WOCE_CO2_water"), new UserWoce(2, "WOCE_CO2_water"), new UserWoce(8, "WOCE_CO2_water") },
				{ new UserWoce(3, "WOCE_CO2_water") },
				{ }
		};
		ArrayList<HashSet<UserWoce>> woceFourRowSets = 
				new ArrayList<HashSet<UserWoce>>(woceFourIndices.length);
		for (int k = 0; k < woceFourIndices.length; k++)
			woceFourRowSets.add(new HashSet<UserWoce>(Arrays.asList(woceFourIndices[k])));

		DashboardCruiseWithData cruiseData = new DashboardCruiseWithData();
		assertEquals(0, cruiseData.getColWoceFours().size());
		cruiseData.setColWoceFours(woceFourRowSets);
		assertEquals(woceFourRowSets, cruiseData.getColWoceFours());
		assertEquals(0, cruiseData.getColWoceThrees().size());
		assertEquals(0, cruiseData.getDataValues().size());
		assertEquals(0, cruiseData.getPreamble().size());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getExpocode());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getVersion());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getUploadFilename());
		assertEquals(DashboardUtils.STRING_MISSING_VALUE, cruiseData.getOwner());
		cruiseData.setColWoceFours(null);
		assertEquals(0, cruiseData.getColWoceFours().size());
	}

	/**
	 * Test method for {@link gov.noaa.pmel.dashboard.shared.DashboardCruiseWithData#hashCode()}
	 * and {@link gov.noaa.pmel.dashboard.shared.DashboardCruiseWithData#equals(java.lang.Object)}.
	 */
	@Test
	public void testHashCodeEquals() {
		String myOwner = "SocatUser";
		String myFilename = "agsk20031205_revised.tsv";
		String myVersion = "3.0";
		String myExpocode = "AGSK20031205";
		ArrayList<String> myPreamble = new ArrayList<String>(Arrays.asList(
				"Cruise Expocode: AGSK20031205",
				"Cruise Name: SKO313",
				"Ship/Vessel Name: Skogafoss",
				"Principal Investigator(s): Rik Wanninkhof"
		));
		String[][] observations = {
				{ "2003-12-05 22:12", "337.28101", "64.10700", "26.910", 
					"5.410", "5.700", null, "1026.500", "373.740" },
				{ "2003-12-05 22:18", "337.23901", "64.09700", "28.360", 
					"5.390", "5.680", null, "1026.100", "374.390" },
				{ "2003-12-05 22:24", "337.20499", "64.08300", "28.700", 
					"5.440", "5.730", null, "1026.100", "374.510" },
				{ "2003-12-05 22:30", "337.17499", "64.06900", "28.690", 
					"5.630", "5.920", null, "1025.800", "372.710" },
				{ "2003-12-05 22:36", "337.14499", "64.05500", "28.750", 
					"5.710", "6.000", null, "1025.900", "370.480" }
		};
		ArrayList<ArrayList<String>> dataValues = 
				new ArrayList<ArrayList<String>>(observations.length);
		for (int k = 0; k < observations.length; k++)
			dataValues.add(new ArrayList<String>(Arrays.asList(observations[k])));
		UserWoce[][] woceThreeIndices = {
				{ new UserWoce(2, "WOCE_CO2_water"), new UserWoce(6, "WOCE_CO2_water") },
				{ },
				{ new UserWoce(6, "WOCE_CO2_water") },
				{ new UserWoce(3, "WOCE_CO2_water"), new UserWoce(0, "WOCE_CO2_water"), new UserWoce(6, "WOCE_CO2_water") },
				{ }
		};
		ArrayList<HashSet<UserWoce>> woceThreeRowSets = 
				new ArrayList<HashSet<UserWoce>>(woceThreeIndices.length);
		for (int k = 0; k < woceThreeIndices.length; k++)
			woceThreeRowSets.add(new HashSet<UserWoce>(Arrays.asList(woceThreeIndices[k])));
		UserWoce[][] woceFourIndices = {
				{ },
				{ new UserWoce(1, "WOCE_CO2_water"), new UserWoce(4, "WOCE_CO2_water") },
				{ new UserWoce(5, "WOCE_CO2_water"), new UserWoce(2, "WOCE_CO2_water"), new UserWoce(8, "WOCE_CO2_water") },
				{ new UserWoce(3, "WOCE_CO2_water") },
				{ }
		};
		ArrayList<HashSet<UserWoce>> woceFourRowSets = 
				new ArrayList<HashSet<UserWoce>>(woceFourIndices.length);
		for (int k = 0; k < woceFourIndices.length; k++)
			woceFourRowSets.add(new HashSet<UserWoce>(Arrays.asList(woceFourIndices[k])));

		DashboardCruiseWithData firstData = new DashboardCruiseWithData();
		assertFalse( firstData.equals(null) );
		assertFalse( firstData.equals(myPreamble) );
		DashboardCruiseWithData secondData = new DashboardCruiseWithData();
		assertEquals(firstData.hashCode(), secondData.hashCode());
		assertEquals(firstData, secondData);

		firstData.setOwner(myOwner);
		assertTrue( firstData.hashCode() != secondData.hashCode() );
		assertFalse( firstData.equals(secondData) );
		secondData.setOwner(myOwner);
		assertEquals(firstData.hashCode(), secondData.hashCode());
		assertEquals(firstData, secondData);

		firstData.setUploadFilename(myFilename);
		assertTrue( firstData.hashCode() != secondData.hashCode() );
		assertFalse( firstData.equals(secondData) );
		secondData.setUploadFilename(myFilename);
		assertEquals(firstData.hashCode(), secondData.hashCode());
		assertEquals(firstData, secondData);

		firstData.setVersion(myVersion);
		assertTrue( firstData.hashCode() != secondData.hashCode() );
		assertFalse( firstData.equals(secondData) );
		secondData.setVersion(myVersion);
		assertEquals(firstData.hashCode(), secondData.hashCode());
		assertEquals(firstData, secondData);

		firstData.setExpocode(myExpocode);
		assertTrue( firstData.hashCode() != secondData.hashCode() );
		assertFalse( firstData.equals(secondData) );
		secondData.setExpocode(myExpocode);
		assertEquals(firstData.hashCode(), secondData.hashCode());
		assertEquals(firstData, secondData);

		firstData.setPreamble(myPreamble);
		assertTrue( firstData.hashCode() != secondData.hashCode() );
		assertFalse( firstData.equals(secondData) );
		secondData.setPreamble(myPreamble);
		assertEquals(firstData.hashCode(), secondData.hashCode());
		assertEquals(firstData, secondData);

		firstData.setDataValues(dataValues);
		assertTrue( firstData.hashCode() != secondData.hashCode() );
		assertFalse( firstData.equals(secondData) );
		secondData.setDataValues(dataValues);
		assertEquals(firstData.hashCode(), secondData.hashCode());
		assertEquals(firstData, secondData);

		firstData.setColWoceThrees(woceThreeRowSets);
		assertTrue( firstData.hashCode() != secondData.hashCode() );
		assertFalse( firstData.equals(secondData) );
		secondData.setColWoceThrees(woceThreeRowSets);
		assertEquals(firstData.hashCode(), secondData.hashCode());
		assertEquals(firstData, secondData);

		firstData.setColWoceFours(woceFourRowSets);
		assertTrue( firstData.hashCode() != secondData.hashCode() );
		assertFalse( firstData.equals(secondData) );
		secondData.setColWoceFours(woceFourRowSets);
		assertEquals(firstData.hashCode(), secondData.hashCode());
		assertEquals(firstData, secondData);
	}
}
