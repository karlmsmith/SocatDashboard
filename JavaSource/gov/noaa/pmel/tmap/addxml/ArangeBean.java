package gov.noaa.pmel.tmap.addxml;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * <p>Title: addXML</p>
 *
 * <p>Description: Reads local or OPeNDAP netCDF files and generates LAS XML
 * configuration information.</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: NOAA/PMEL/TMAP</p>
 *
 * @author RHS
 * @version 1.0
 */
public class ArangeBean extends LasBean {
  private String start;
  private String step;
  private String size;
  
  private static Logger log = LogManager.getLogger(ArangeBean.class.getName());

  public ArangeBean() {
  }

  public void setStart(String start) {
    this.start = start;
  }

  public void setStep(String step) {
    this.step = step;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getStart() {
    return start;
  }

  public String getStep() {
    return step;
  }

  public String getSize() {
    return size;
  }

  public String toString() {
     return "start="+start+" step="+step+" size="+size;
  }

@Override
public boolean equals(LasBean bean) {
	ArangeBean b = null;
	if ( !( bean instanceof ArangeBean ) ) {
		return false;
	} else { 
		b = (ArangeBean) bean;
	}
	if ( !size.equals(b.getSize() )) {
		log.debug("Size compare failed");
		return false;
	}
	if ( !step.equals(b.getStep() )) {
		log.debug("Step compare failed.");
		return false;
	}
	if ( !start.equals(b.getStart() )) {
		log.debug("Start compare failed.");
		return false;
	}
	return true;
}
}
