// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.util.ButterflyDriveState;

/** Add your docs here. */
public class ButterflyShifter {
    private final DoubleSolenoid mShifter;
    private final Value mDifferentialValue;
    private final Value mMecanumValue;

    public ButterflyShifter(DoubleSolenoid shifter, Value differentialValue, Value mecanumValue) {
        mShifter = shifter;
        mDifferentialValue = differentialValue;
        mMecanumValue = mecanumValue;
    }

    /**
     * Sets the state of the shifter
     * @param state Desired state of the shifter
     */
    public void setState(ButterflyDriveState state) {
        if (state == ButterflyDriveState.DIFFERENTIAL) {
            mShifter.set(mDifferentialValue);
        }
        mShifter.set(mMecanumValue);
    }

}
