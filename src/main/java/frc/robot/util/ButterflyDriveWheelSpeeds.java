// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;

/**
 * Wheel Speeds class that encapsulates Differential and Mecanum wheel speeds
 */
public class ButterflyDriveWheelSpeeds {
    public double frontLeftMetersPerSecond;

    public double frontRightMetersPerSecond;

    public double backLeftMetersPerSecond;

    public double backRightMetersPerSecond;

    /**
     * Convert MecanumDriveWheelSpeeds to ButterflyDriveWheelSpeeds
     * @param wheelSpeeds MecanumDriveWheelSpeeds
     */
    public ButterflyDriveWheelSpeeds(MecanumDriveWheelSpeeds wheelSpeeds) {
        frontLeftMetersPerSecond = wheelSpeeds.frontLeftMetersPerSecond;
        frontRightMetersPerSecond = wheelSpeeds.frontRightMetersPerSecond;
        backLeftMetersPerSecond = wheelSpeeds.rearLeftMetersPerSecond;
        backRightMetersPerSecond = wheelSpeeds.rearRightMetersPerSecond;
    }

    /**
     * Convert DifferentialDriveWheelSpeeds to ButterflyDriveWheelSpeeds
     * @param wheelSpeeds DifferentialDriveWheelSpeeds
     */
    public ButterflyDriveWheelSpeeds(DifferentialDriveWheelSpeeds wheelSpeeds) {
        frontLeftMetersPerSecond = wheelSpeeds.leftMetersPerSecond;
        frontRightMetersPerSecond = wheelSpeeds.rightMetersPerSecond;
        backLeftMetersPerSecond = wheelSpeeds.leftMetersPerSecond;
        backRightMetersPerSecond = wheelSpeeds.rightMetersPerSecond;
    }


}
