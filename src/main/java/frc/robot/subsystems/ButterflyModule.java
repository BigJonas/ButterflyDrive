// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Neo;
import frc.robot.util.ButterflyDriveConstants.Differential;

import static frc.robot.util.ButterflyDriveConstants.*;

/** Add your docs here. */
public class ButterflyModule extends SubsystemBase {
    private final CANSparkMax mMotor;
    private final RelativeEncoder mEncoder;
    private final SparkMaxPIDController mPID;

    public ButterflyModule(CANSparkMax motor) {
        mMotor = motor;
        mEncoder = mMotor.getEncoder();
        mPID = mMotor.getPIDController();
        configPIDControllers();;
    }

    /**
     * Set speed of the module in meters per second
     * @param metersPerSecond Desired speed in meters per second
     */
    public void setSpeed(double metersPerSecond) {
        mPID.setReference(metersPerSecond, ControlType.kVelocity, getPIDSlot(), calculateFeedforward(metersPerSecond));
    }

    /**
     * Manually calculate feedforward
     * @param metersPerSecond Desired meters per second
     * @return Feedforward voltage to achieve meters per second
     */
    public double calculateFeedforward(double metersPerSecond) {
        return getKS() * Math.signum(metersPerSecond) + getKV() * metersPerSecond;
    }

    /**
     * Gets the wheel speed of the module
     * @return
     */
    public double getWheelSpeed() {
        return mMotor.getEncoder().getVelocity();
    }

    /**
     * Sets encoder conversion rate to always be in meters so there doesnt have to be two methods for setting speed
     */
    private void updateEncoderConversion() {
        double rotationPerMeter = getRotationPerMeters(getGearRatio(), getWheelCircumference());
        mEncoder.setPositionConversionFactor(rotationPerMeter);
        mEncoder.setVelocityConversionFactor(rotationPerMeter / 60.0); // Convert RPM to RPS 
    }

    /**
     * Config PID controllers 
     */
    private void configPIDControllers() {
        mPID.setP(Differential.KP, Differential.PID_SLOT);
        mPID.setI(Differential.KI, Differential.PID_SLOT);
        mPID.setD(Differential.KD, Differential.PID_SLOT);
        mPID.setFF(Differential.KF, Differential.PID_SLOT);

        mPID.setP(Mecanum.KP, Mecanum.PID_SLOT);
        mPID.setI(Mecanum.KI, Mecanum.PID_SLOT);
        mPID.setD(Mecanum.KD, Mecanum.PID_SLOT);
        mPID.setFF(Mecanum.KF, Mecanum.PID_SLOT);
    }

    /**
     * Converts neo encoder counts to meters
     * @return 
     */
    private double getRotationPerMeters(double gearRatio, double wheelCircumference) {
        return wheelCircumference / gearRatio / Neo.COUNTS_PER_REVOLUTION;
    }

    @Override
    public void periodic() {
        updateEncoderConversion();
    }
}
