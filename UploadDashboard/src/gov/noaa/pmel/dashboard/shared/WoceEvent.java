/**
 * 
 */
package gov.noaa.pmel.dashboard.shared;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Represents a WOCE event occurring on a number of values in a data column. 
 * Note that the inherited id field is ignored in the hashCode and equals methods.
 * 
 * @author Karl Smith
 */
public class WoceEvent extends DashboardEvent implements Serializable, IsSerializable {

	private static final long serialVersionUID = 8325545745794951530L;

	public static final String PI_PROVIDED_WOCE_COMMENT_START = "PI provided WOCE-";

	// flags for WOCE events of current cruises
	public static final Character WOCE_GOOD = '2';
	public static final Character WOCE_NOT_CHECKED = '2';
	public static final Character WOCE_QUESTIONABLE = '3';
	public static final Character WOCE_BAD = '4';
	public static final Character WOCE_NO_DATA = '9';

	// flags for WOCE events of cruises that has been updated
	public static final Character OLD_WOCE_GOOD = 'G';
	public static final Character OLD_WOCE_NOT_CHECKED = 'G';
	public static final Character OLD_WOCE_QUESTIONABLE = 'Q';
	public static final Character OLD_WOCE_BAD = 'B';
	public static final Character OLD_WOCE_NO_DATA = 'M';

	// flag for cruise rename WOCE events
	public static final Character WOCE_RENAME = 'R';

	Character flag;
	String varName;
	ArrayList<DataLocation> locations;

	/**
	 * Creates an empty WOCE event with flag {@link #WOCE_NOT_CHECKED}
	 */
	public WoceEvent() {
		super();
		flag = WOCE_NOT_CHECKED;
		varName = DashboardUtils.STRING_MISSING_VALUE;
		locations = new ArrayList<DataLocation>();
	}

	/**
	 * @return 
	 * 		the flag; 
	 * 		never null
	 */
	public Character getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 * 		the flag to set; 
	 * 		if null, {@link #WOCE_NOT_CHECKED} is assigned
	 */
	public void setFlag(Character flag) {
		if ( flag == null )
			this.flag = WOCE_NOT_CHECKED;
		else
			this.flag = flag;
	}

	/**
	 * @return 
	 * 		the data variable name in the DSG file;
	 * 		never null, but may be {@link DashboardUtils#STRING_MISSING_VALUE}
	 */
	public String getVarName() {
		return varName;
	}

	/**
	 * @param varName 
	 * 		the data variable name in the DSG file to set;
	 * 		if null, {@link DashboardUtils#STRING_MISSING_VALUE} is assigned
	 */
	public void setVarName(String varName) {
		if ( varName == null )
			this.varName = DashboardUtils.STRING_MISSING_VALUE;
		else
			this.varName = varName;
	}

	/**
	 * @return 
	 * 		the list of locations associated with this WOCE flag;
	 * 		never null, but may be empty.
	 * 		The actual ArrayList in this object is returned.
	 */
	public ArrayList<DataLocation> getLocations() {
		return locations;
	}

	/**
	 * @param locations 
	 * 		the locations to set;  if null, the set of locations 
	 * 		is cleared.  The actual objects in the provided list 
	 * 		are reused (shallow copy).
	 */
	public void setLocations(ArrayList<DataLocation> locations) {
		this.locations.clear();
		if ( locations != null )
			this.locations.addAll(locations);
	}

	@Override
	public int hashCode() {
		final int prime = 37;
		int result = super.hashCode();
		result = result * prime + flag.hashCode();
		result = result * prime + varName.hashCode();
		result = result * prime + locations.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;

		if ( ! (obj instanceof WoceEvent) )
			return false;
		WoceEvent other = (WoceEvent) obj;

		if ( ! super.equals(other) )
			return false;
		if ( ! flag.equals(other.flag) )
			return false;
		if ( ! varName.equals(other.varName) )
			return false;
		if ( ! locations.equals(other.locations) )
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "WoceEvent" +
				"[\n    id=" + id.toString() +
				",\n    flag='" + flag.toString() + "'" +
				",\n    flagDate=" + flagDate.toString() + 
				",\n    expocode=" + expocode + 
				",\n    version=" + version.toString() + 
				",\n    varName=" + varName.toString() + 
				",\n    locations=" + locations.toString() + 
				",\n    username=" + username + 
				",\n    realname=" + realname + 
				",\n    comment=" + comment + 
				"]";
	}

}
