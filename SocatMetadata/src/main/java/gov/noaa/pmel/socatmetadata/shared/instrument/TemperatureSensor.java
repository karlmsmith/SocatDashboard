package gov.noaa.pmel.socatmetadata.shared.instrument;

import com.google.gwt.user.client.rpc.IsSerializable;
import gov.noaa.pmel.socatmetadata.shared.core.Duplicable;
import gov.noaa.pmel.socatmetadata.shared.variable.InstData;

import java.io.Serializable;

/**
 * Basic information about an instrument that is a temperature sensor.  Specific details about values measured
 * by the sensor are part of {@link InstData}.
 */
public class TemperatureSensor extends Analyzer implements Duplicable, Serializable, IsSerializable {

    private static final long serialVersionUID = -1721024204233250085L;

    /**
     * Create with all fields empty
     */
    public TemperatureSensor() {
        super();
    }

    /**
     * Create using as many of the values in the given instrument subclass as possible.
     */
    public TemperatureSensor(Instrument instr) {
        super(instr);
    }

    @Override
    public Object duplicate(Object dup) {
        TemperatureSensor sensor;
        if ( dup == null )
            sensor = new TemperatureSensor();
        else
            sensor = (TemperatureSensor) dup;
        super.duplicate(sensor);
        return sensor;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj )
            return true;
        if ( null == obj )
            return false;
        if ( !(obj instanceof TemperatureSensor) )
            return false;
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString().replaceFirst(super.getSimpleName(), getSimpleName());
    }

    @Override
    public String getSimpleName() {
        return "TemperatureSensor";
    }

}
