package gov.noaa.pmel.dashboard.client.metadata.instpanels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import gov.noaa.pmel.socatmetadata.shared.instrument.PressureSensor;

import java.util.HashSet;


public class PressureSensorPanel extends GenericSensorPanel {

    interface PressureSensorPanelUiBinder extends UiBinder<ScrollPanel,PressureSensorPanel> {
    }

    private static final PressureSensorPanelUiBinder uiBinder = GWT.create(PressureSensorPanelUiBinder.class);

    /**
     * Creates a FlowPanel associated with the given pressure sensor metadata
     * The {@link #initialize()} method must be called prior to using this FlowPanel.
     *
     * @param instr
     *         associate this panel with this sensor; cannot be null
     * @param header
     *         header that should be updated when appropriate values change; cannot be null
     * @param parentPanel
     *         parent TabPanel controlling this panel
     */
    public PressureSensorPanel(PressureSensor instr, HTML header, InstrumentsTabPanel parentPanel) {
        super(instr, header, parentPanel);

        // Nothing more to add
    }

    @Override
    public void initialize() {
        initWidget(uiBinder.createAndBindUi(this));
        finishInitialization();
    }

    @Override
    protected void finishInitialization() {
        // Nothing more to add

        // Finish initialization, including marking invalid fields
        super.finishInitialization();
    }

    @Override
    protected void markInvalids(HashSet<String> invalids) {
        if ( invalids == null )
            invalids = ((PressureSensor) instr).invalidFieldNames();

        // Nothing more to add

        // Finish marking labels and the tab for this panel
        super.markInvalids(invalids);
    }

}
