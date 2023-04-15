// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ButterflyDrivetrain;

public class DefaultDrive extends CommandBase {
  private final ButterflyDrivetrain mDrive;
  private final DoubleSupplier mFwd;
  private final DoubleSupplier mStr;
  private final DoubleSupplier mRot;  

  /** Creates a new DefaultDrive. */
  public DefaultDrive(ButterflyDrivetrain drive, DoubleSupplier fwd, DoubleSupplier str, DoubleSupplier rot) {
    mDrive = drive;
    mFwd = fwd;
    mStr = str;
    mRot = rot;
    addRequirements(drive);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double fwd = mFwd.getAsDouble();
    double str = mStr.getAsDouble();
    double rot = mRot.getAsDouble();

    drive(fwd, str, rot);
  }

  protected void drive(double fwd, double str, double rot) {
    mDrive.drive(fwd, str, rot);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
