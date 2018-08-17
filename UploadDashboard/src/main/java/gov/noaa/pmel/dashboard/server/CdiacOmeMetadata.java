package gov.noaa.pmel.dashboard.server;

import org.jdom2.Document;
import uk.ac.uea.socat.omemetadata.OmeMetadata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

public class CdiacOmeMetadata implements OmeMetadataInterface {

    private static final SimpleDateFormat TIME_PARSER;
    private static final SimpleDateFormat DATE_STAMPER;

    static {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        TIME_PARSER = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        TIME_PARSER.setTimeZone(utc);
        DATE_STAMPER = new SimpleDateFormat("yyyyMMdd");
        DATE_STAMPER.setTimeZone(utc);
    }

    private OmeMetadata mdata;

    public CdiacOmeMetadata() {
        mdata = new OmeMetadata("");
    }

    @Override
    public Document createDocument() {
        return mdata.createOmeXmlDoc();
    }

    @Override
    public void assignFromDocument(Document doc) throws IllegalArgumentException {
        try {
            mdata.assignFromOmeXmlDoc(doc);
        } catch ( Exception ex ) {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public OmeMetadataInterface merge(OmeMetadataInterface other) throws IllegalArgumentException {
        if ( !(other instanceof CdiacOmeMetadata) )
            throw new IllegalArgumentException("Unsupported class of other OME object for CdiacOmeMetadata.merge");
        CdiacOmeMetadata otherMData = (CdiacOmeMetadata) other;

        CdiacOmeMetadata merged = new CdiacOmeMetadata();
        try {
            merged.mdata = OmeMetadata.merge(mdata, otherMData.mdata);
        } catch ( Exception ex ) {
            throw new IllegalArgumentException(ex);
        }

        return merged;
    }

    @Override
    public boolean isAcceptable() {
        return mdata.isAcceptable();
    }

    @Override
    public String getDatasetId() {
        String expocode = mdata.getExpocode();
        if ( OmeMetadata.CONFLICT_STRING.equals(expocode) )
            expocode = null;
        return expocode;
    }

    @Override
    public void setDatasetId(String newId) {
        String value = (newId != null) ? newId : "";
        mdata.setExpocode(value);
    }

    @Override
    public String getDatasetName() {
        String datasetName = mdata.getExperimentName();
        if ( OmeMetadata.CONFLICT_STRING.equals(datasetName) )
            datasetName = null;
        return datasetName;
    }

    @Override
    public void setDatasetName(String datasetName) {
        String value = (datasetName != null) ? datasetName : "";
        try {
            mdata.replaceValue(OmeMetadata.EXPERIMENT_NAME_STRING, value, -1);
        } catch ( Exception ex ) {
            // Should never happen
        }
    }

    @Override
    public String getPlatformName() {
        String platformName = mdata.getVesselName();
        if ( OmeMetadata.CONFLICT_STRING.equals(platformName) )
            platformName = null;
        return platformName;
    }

    @Override
    public void setPlatformName(String platformName) {
        String value = (platformName != null) ? platformName : "";
        try {
            mdata.replaceValue(OmeMetadata.VESSEL_NAME_STRING, value, -1);
        } catch ( Exception ex ) {
            // Should never happen
        }
    }

    @Override
    public String getPlatformType() {
        String platformType;
        try {
            platformType = mdata.getValue(OmeMetadata.PLATFORM_TYPE_STRING);
            if ( OmeMetadata.CONFLICT_STRING.equals(platformType) )
                platformType = null;
        } catch ( Exception ex ) {
            // Should never happen
            platformType = null;
        }
        return platformType;
    }

    @Override
    public void setPlatformType(String platformType) {
        String value = (platformType != null) ? platformType : "";
        try {
            mdata.replaceValue(OmeMetadata.PLATFORM_TYPE_STRING, value, -1);
        } catch ( Exception ex ) {
            // Should never happen
        }
    }

    @Override
    public ArrayList<String> getInvestigators() {
        return mdata.getInvestigators();
    }

    @Override
    public ArrayList<String> getOrganizations() {
        return mdata.getOrganizations();
    }

    @Override
    public void setInvestigatorsAndOrganizations(List<String> investigators, List<String> organizations) {
        try {
            int numNames = investigators.size();
            if ( organizations.size() != numNames )
                throw new Exception("number of organizations does not match the number of investigators");
            mdata.clearCompositeValueList(OmeMetadata.INVESTIGATOR_COMP_NAME);
            for (int k = 0; k < numNames; k++) {
                Properties props = new Properties();
                props.setProperty(OmeMetadata.NAME_ELEMENT_NAME, investigators.get(k));
                props.setProperty(OmeMetadata.ORGANIZATION_ELEMENT_NAME, organizations.get(k));
                mdata.storeCompositeValue(OmeMetadata.INVESTIGATOR_COMP_NAME, props, -1);
            }
        } catch ( Exception ex ) {
            throw new IllegalArgumentException("problems updating names: " + ex.getMessage(), ex);
        }
    }

    @Override
    public String getDatasetRefs() {
        String datasetRefs;
        try {
            // Could be null
            datasetRefs = mdata.getValue(OmeMetadata.DATA_SET_REFS_STRING);
            if ( OmeMetadata.CONFLICT_STRING.equals(datasetRefs) )
                datasetRefs = null;
        } catch ( Exception ex ) {
            // Should never happen
            datasetRefs = null;
        }
        return datasetRefs;
    }

    @Override
    public void setDatasetRefs(String datasetRefs) {
        String value = (datasetRefs != null) ? datasetRefs : "";
        try {
            mdata.replaceValue(OmeMetadata.DATA_SET_REFS_STRING, value, -1);
        } catch ( Exception ex ) {
            // Should never happen
        }
    }

    @Override
    public String getDatasetDOI() {
        // TODO: DOI not stored in CDIAC OME XML
        return null;
    }

    @Override
    public void setDatasetDOI(String datasetDOI) {
        // TODO: DOI not stored in CDIAC OME XML
    }

    @Override
    public Double getWesternLongitude() {
        Double value;
        try {
            value = Double.parseDouble(mdata.getWestmostLongitude());
            if ( (value >= -540.0) && (value <= 540.0) ) {
                while ( value <= -180.0 ) {
                    value += 360.0;
                }
                while ( value > 180.0 ) {
                    value -= 360.0;
                }
            }
            else {
                value = null;
            }
        } catch ( Exception ex ) {
            return null;
        }
        return value;
    }

    @Override
    public void setWesternLongitude(Double westernLongitude) {
        String value;
        try {
            value = String.format("%#.3f", westernLongitude);
        } catch ( Exception ex ) {
            value = "";
        }
        try {
            mdata.replaceValue(OmeMetadata.WEST_BOUND_STRING, value, -1);
        } catch ( Exception ex ) {
            // Should never happen
        }
    }

    @Override
    public Double getEasternLongitude() {
        Double value;
        try {
            value = Double.parseDouble(mdata.getEastmostLongitude());
            if ( (value >= -540.0) && (value <= 540.0) ) {
                while ( value <= -180.0 ) {
                    value += 360.0;
                }
                while ( value > 180.0 ) {
                    value -= 360.0;
                }
            }
            else {
                value = null;
            }
        } catch ( Exception ex ) {
            value = null;
        }
        return value;
    }

    @Override
    public void setEasternLongitude(Double easternLongitude) {
        String value;
        try {
            value = String.format("%#.3f", easternLongitude);
        } catch ( Exception ex ) {
            value = "";
        }
        try {
            mdata.replaceValue(OmeMetadata.EAST_BOUND_STRING, value, -1);
        } catch ( Exception ex ) {
            // Should never happen
        }
    }

    @Override
    public Double getSouthernLatitude() {
        Double value;
        try {
            value = Double.parseDouble(mdata.getSouthmostLatitude());
            if ( !((value >= -90.0) && (value <= 90.0)) )
                value = null;
        } catch ( Exception ex ) {
            value = null;
        }
        return value;
    }

    @Override
    public void setSouthernLatitude(Double southernLatitude) {
        String value;
        try {
            value = String.format("%#.3f", southernLatitude);
        } catch ( Exception ex ) {
            value = "";
        }
        try {
            mdata.replaceValue(OmeMetadata.SOUTH_BOUND_STRING, value, -1);
        } catch ( Exception ex ) {
            // Should never happen
        }
    }

    @Override
    public Double getNorthernLatitude() {
        Double value;
        try {
            value = Double.parseDouble(mdata.getNorthmostLatitude());
            if ( !((value >= -90.0) && (value <= 90.0)) )
                value = null;
        } catch ( Exception ex ) {
            value = null;
        }
        return value;
    }

    @Override
    public void setNorthernLatitude(Double northernLatitude) {
        String value;
        try {
            value = String.format("%#.3f", northernLatitude);
        } catch ( Exception ex ) {
            value = "";
        }
        try {
            mdata.replaceValue(OmeMetadata.NORTH_BOUND_STRING, value, -1);
        } catch ( Exception ex ) {
            // Should never happen
        }
    }

    @Override
    public Double getDataStartTime() {
        // CDIAC OME only stores the date, in "yyyyMMdd" format
        String dateString = mdata.getTemporalCoverageStartDate();
        if ( (dateString == null) || dateString.isEmpty() || OmeMetadata.CONFLICT_STRING.equals(dateString) )
            return null;
        try {
            Date startTime = TIME_PARSER.parse(dateString + " 00:00:00");
            return startTime.getTime() / 1000.0;
        } catch ( Exception ex ) {
            return null;
        }
    }

    @Override
    public void setDataStartTime(Double dataStartTime) {
        // CDIAC OME only stores the date, in "yyyyMMdd" format
        Date startTime = new Date(Math.round(dataStartTime * 1000.0));
        String value = DATE_STAMPER.format(startTime);
        try {
            mdata.replaceValue(OmeMetadata.TEMP_START_DATE_STRING, value, -1);
        } catch ( Exception ex ) {
            // Should not happen
        }
    }

    @Override
    public Double getDataEndTime() {
        // CDIAC OME only stores the date, in "yyyyMMdd" format
        String dateString = mdata.getTemporalCoverageEndDate();
        if ( (dateString == null) || dateString.isEmpty() || OmeMetadata.CONFLICT_STRING.equals(dateString) )
            return null;
        try {
            Date endTime = TIME_PARSER.parse(dateString + " 23:59:59");
            return endTime.getTime() / 1000.0;
        } catch ( Exception ex ) {
            return null;
        }
    }

    @Override
    public void setDataEndTime(Double dataEndTime) {
        // CDIAC OME only stores the date, in "yyyyMMdd" format
        Date endTime = new Date(Math.round(dataEndTime * 1000.0));
        String value = DATE_STAMPER.format(endTime);
        try {
            mdata.replaceValue(OmeMetadata.TEMP_END_DATE_STRING, value, -1);
        } catch ( Exception ex ) {
            // Should not happen
        }
    }

}

