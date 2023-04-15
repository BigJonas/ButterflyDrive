// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;

public class ButterflyDriveKinematics {

    private final DifferentialDriveKinematics mDifferentialKinematics;
    private final MecanumDriveKinematics mMecanumKinematics;

    private Translation2d mCenterOfRotation;
    private ButterflyDriveState mState;

    /**
     * Creates a butterfly drive kinematics that switches between Differential and Mecanum kinematics
     * @param differentialKinematics Differential drive kinematics
     * @param mecanumKinematics Mecanum drive kinematics
     */
    public ButterflyDriveKinematics(DifferentialDriveKinematics differentialKinematics, MecanumDriveKinematics mecanumKinematics) {
        mDifferentialKinematics = differentialKinematics;
        mMecanumKinematics = mecanumKinematics;

        mCenterOfRotation = new Translation2d(); // Default is 0.0, 0.0 (center of the robot)
        mState = ButterflyDriveState.DIFFERENTIAL; // Differential is the default state according to me
    }

    /**
     * Converts desired chassis speeds to wheel speeds and desaturates them
     * @param chassisSpeeds Desired chassis speeds
     * @param maxWheelSpeed Max wheel speed
     * @return 
     */
    public ButterflyDriveWheelSpeeds toWheelSpeeds(ChassisSpeeds chassisSpeeds, double maxWheelSpeed) {
        ButterflyDriveWheelSpeeds wheelSpeeds;
        if (mState == ButterflyDriveState.DIFFERENTIAL) {
            DifferentialDriveWheelSpeeds differentialSpeeds = mDifferentialKinematics.toWheelSpeeds(chassisSpeeds);
            differentialSpeeds.desaturate(maxWheelSpeed);
            wheelSpeeds = new ButterflyDriveWheelSpeeds(differentialSpeeds);
        } else {
            MecanumDriveWheelSpeeds mecanumSpeeds = mMecanumKinematics.toWheelSpeeds(chassisSpeeds, mCenterOfRotation);
            mecanumSpeeds.desaturate(maxWheelSpeed);
            wheelSpeeds = new ButterflyDriveWheelSpeeds(mecanumSpeeds);
        }
        return wheelSpeeds;
    }

    /**
     * Sets the center of rotation for mecanum drive
     * @param centerOfRotation Desired center of rotation
     */
    public void setCenterOfRotation(Translation2d centerOfRotation) {
        mCenterOfRotation = centerOfRotation;
    }

    /**
     * Sets the state 
     * @param state Desired state 
     */
    public void setState(ButterflyDriveState state) {
        mState = state;
    }
}
