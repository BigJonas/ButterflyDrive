// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.util.ButterflyDriveConstants.*;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Neo;
import frc.robot.util.ButterflyDriveConstants;
import frc.robot.util.ButterflyDriveKinematics;
import frc.robot.util.ButterflyDriveState;
import frc.robot.util.ButterflyDriveWheelSpeeds;

public class ButterflyDrivetrain extends SubsystemBase {
  private final ButterflyModule mFrontLeft;
  private final ButterflyModule mFrontRight;
  private final ButterflyModule mBackLeft;
  private final ButterflyModule mBackRight;
  private final ButterflyShifter[] mShifters;
  private final ButterflyDriveKinematics mKinematics;

  private final AHRS mGyro;

  private boolean mFieldCentriActive;

  public ButterflyDrivetrain(
    ButterflyModule frontLeft, 
    ButterflyModule frontRight, 
    ButterflyModule backLeft,
    ButterflyModule backRight, ButterflyShifter shifters[], AHRS gyro, ButterflyDriveKinematics kinematics) {
    // Set everthing to differential mode on init 
    setState(ButterflyDriveState.DIFFERENTIAL);
    mFrontLeft = frontLeft;
    mFrontRight = frontRight;
    mBackLeft = backLeft;
    mBackRight = backRight;

    mShifters = shifters;

    mGyro = gyro;
    mKinematics = kinematics;
  }

  /**
   * Drives the drivetrain
   * @param fwd Forward velocity
   * @param str Strafe velocity
   * @param rot Angular velocity
   */
  public void drive(double fwd, double str, double rot) {
    fwd = MathUtil.clamp(fwd, -1, 1) * getMaxWheelSpeed();
    str = MathUtil.clamp(str, -1, 1) * getMaxWheelSpeed();
    rot = MathUtil.clamp(rot, -1, 1) * getMaxAngularVelocity();
    ChassisSpeeds chassisSpeeds = new ChassisSpeeds(fwd, str, rot);
    if (mFieldCentriActive) chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(chassisSpeeds, getRotation()); 
    drive(chassisSpeeds);
  }

  /**
   * Drive the drivetrain
   * @param chassisSpeeds Desired chassis speeds of the drivetrain
   */
  public void drive(ChassisSpeeds chassisSpeeds) {
    ButterflyDriveWheelSpeeds wheelSpeeds = mKinematics.toWheelSpeeds(chassisSpeeds, getMaxWheelSpeed());
    drive(wheelSpeeds); 
  }

  /**
   * Drives the drivetrain 
   * @param wheelSpeeds Desired wheel speeds of the drivetrain
   */
  public void drive(ButterflyDriveWheelSpeeds wheelSpeeds) {
    mFrontLeft.setSpeed(wheelSpeeds.frontLeftMetersPerSecond);
    mFrontRight.setSpeed(wheelSpeeds.frontRightMetersPerSecond);
    mBackLeft.setSpeed(wheelSpeeds.backLeftMetersPerSecond);
    mBackRight.setSpeed(wheelSpeeds.backRightMetersPerSecond);
  }

  /**
   * Sets the state of the drive
   * @param state Desired state of the drive
   */
  public void setState(ButterflyDriveState state) {
    for (int i = 0; i < mShifters.length; i++) {
      mShifters[i].setState(state);
    }
    mKinematics.setState(state);
    ButterflyDriveConstants.setState(state);
  }

  /**
   * Gets the max wheel speed
   * @return Max wheel speed 
   */
  public double getMaxWheelSpeed() {
    return Neo.FREE_SPIN / 60.0 * getGearRatio() * getWheelCircumference();
  }

  /**
   * Gets the max angular velocity
   * @return Max angular velocity
   */
  public double getMaxAngularVelocity() {
    return getMaxWheelSpeed() / Math.hypot(getWheelBase(), getTrackWidth());
  }

  /**
   * Gets rotation of the robot
   * @return Measured rotation of the robot
   */
  public Rotation2d getRotation() {
    return mGyro.getRotation2d();
  }

  /**
   * Gets angle of the robot in radians
   * @return Accum angle of the robot in radians
   */
  public double getAngle() {
    return Units.degreesToRadians(mGyro.getAngle());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
