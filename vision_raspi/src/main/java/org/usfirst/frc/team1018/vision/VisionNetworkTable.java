package org.usfirst.frc.team1018.vision;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * @author Ryan Blue
 */
public class VisionNetworkTable {

    private static VisionNetworkTable instance;

    private NetworkTable visionTable;

    private VisionNetworkTable() {
        NetworkTable.setServerMode();
        //setClientMode();
        NetworkTable.setTeam(1018);
        NetworkTable.initialize();
        visionTable = getVisionTable();

    }

    private static NetworkTable getVisionTable() {
        return NetworkTable.getTable(Constants.VISION_DATA_TABLE);
    }

    public static VisionNetworkTable getInstance() {
        if(instance == null) instance = new VisionNetworkTable();
        return instance;
    }

    public void putTargetFound(boolean targetFound) {
        visionTable.putBoolean("IsTargetFound", targetFound);
    }

    public void putXCenter(double xCenter) {
        visionTable.putNumber("XCenter", xCenter);
    }

    public void putDistanceToTarget(double distToTarget) {
        visionTable.putNumber("DistToTarget", distToTarget);
    }

    public void putAngleSetpoint(double angleSetpoint) {
        visionTable.putNumber("AngleSetpoint", angleSetpoint);
    }

}
