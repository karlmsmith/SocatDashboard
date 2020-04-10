package gov.noaa.pmel.socatmetadata.shared.instrument;

import com.google.gwt.user.client.rpc.IsSerializable;
import gov.noaa.pmel.socatmetadata.shared.core.Duplicable;
import gov.noaa.pmel.socatmetadata.shared.variable.DataVar;

import java.io.Serializable;

/**
 * Basic information about an instrument that is a pressure sensor.  Specific details about values measured
 * by the sensor are part of {@link DataVar}.
 */
public class PressureSensor extends Analyzer implements Duplicable, Serializable, IsSerializable {

    private static final long serialVersionUID = -5025452549008862907L;

    @Override
    public Object duplicate(Object dup) {
        PressureSensor sensor;
        if ( dup == null )
            sensor = new PressureSensor();
        else
            sensor = (PressureSensor) dup;
        super.duplicate(sensor);
        return sensor;
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj )
            return true;
        if ( null == obj )
            return false;
        if ( !(obj instanceof PressureSensor) )
            return false;
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString().replaceFirst("Analyzer", "PressureSensor");
    }

}
