package qc.cegep_ste_foy.equipe2.calculatorgs.models;

/**
 * Created by Vicente on 4/28/2015.
 */
public class DeviceOperation {
    private String deviceId;
    private String operation;

    public DeviceOperation(String deviceId, String operation) {
        this.deviceId = deviceId;
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

}
