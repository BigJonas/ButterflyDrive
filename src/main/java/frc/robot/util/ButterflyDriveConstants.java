// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;

/** Add your docs here. */
public class ButterflyDriveConstants {

    // Constants automatically change based off this state 
    private static ButterflyDriveState mState = ButterflyDriveState.DIFFERENTIAL;

    private ButterflyDriveConstants(){}

    // FIXME: There has to be a better way than this
    public static final class Mecanum {
        public static final double WHEEL_BASE = 0.0;
        public static final double TRACK_WIDTH = 0.0;

        public static final Translation2d FRONT_LEFT_POSITION = new Translation2d(WHEEL_BASE / 2, TRACK_WIDTH / 2);
        public static final Translation2d FRONT_RIGHT_POSITION = new Translation2d(WHEEL_BASE / 2, -TRACK_WIDTH / 2);
        public static final Translation2d BACK_LEFT_POSITION = new Translation2d(-WHEEL_BASE / 2, TRACK_WIDTH / 2);
        public static final Translation2d BACK_RIGHT_POSITION = new Translation2d(-WHEEL_BASE / 2, -TRACK_WIDTH / 2);

        public static final double WHEEL_DIAMETER = Units.inchesToMeters(4.0); // Sorry hayden
        public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;

        public static final double GEAR_RATIO = 0.0;

        public static final int PID_SLOT = 1;

        public static final double KP = 0.0;
        public static final double KI = 0.0;
        public static final double KD = 0.0;
        public static final double KF = 0.0;

        public static final double KS = 0.0;
        public static final double KV = 0.0;
    }

    public static final class Differential {
        public static final double WHEEL_BASE = 0.0;
        public static final double TRACK_WIDTH = 0.0;

        public static final Translation2d FRONT_LEFT_POSITION = new Translation2d(WHEEL_BASE / 2, TRACK_WIDTH / 2);
        public static final Translation2d FRONT_RIGHT_POSITION = new Translation2d(WHEEL_BASE / 2, -TRACK_WIDTH / 2);
        public static final Translation2d BACK_LEFT_POSITION = new Translation2d(-WHEEL_BASE / 2, TRACK_WIDTH / 2);
        public static final Translation2d BACK_RIGHT_POSITION = new Translation2d(-WHEEL_BASE / 2, -TRACK_WIDTH / 2);

        public static final double WHEEL_DIAMETER = Units.inchesToMeters(4.0); // Sorry hayden
        public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;

        public static final double GEAR_RATIO = 0.0;

        public static final int PID_SLOT = 1;

        public static final double KP = 0.0;
        public static final double KI = 0.0;
        public static final double KD = 0.0;
        public static final double KF = 0.0;

        public static final double KS = 0.0;
        public static final double KV = 0.0;
    }

    public static void setState(ButterflyDriveState state) {
        mState = state;
    }

    public static double getWheelBase() {
        if (mState == ButterflyDriveState.DIFFERENTIAL) {
            return Differential.WHEEL_BASE;
        }
        return Mecanum.WHEEL_BASE;
    }

    public static double getTrackWidth() {
        if (mState == ButterflyDriveState.DIFFERENTIAL) {
            return Differential.TRACK_WIDTH;
        }
        return Mecanum.TRACK_WIDTH;
    }

    public static double getWheelCircumference() {
        if (mState == ButterflyDriveState.DIFFERENTIAL) {
            return Differential.WHEEL_CIRCUMFERENCE;
        }
        return Mecanum.WHEEL_CIRCUMFERENCE;
    }

    public static double getGearRatio() {
        if (mState == ButterflyDriveState.DIFFERENTIAL) {
            return Differential.GEAR_RATIO;
        }
        return Mecanum.GEAR_RATIO;
    }

    public static int getPIDSlot() {
        if (mState == ButterflyDriveState.DIFFERENTIAL) {
            return Differential.PID_SLOT;
        }
        return Mecanum.PID_SLOT;
    }

    public static double getKS() {
        if (mState == ButterflyDriveState.DIFFERENTIAL) {
            return Differential.KS;
        }
        return Mecanum.KS;
    }

    public static double getKV() {
        if (mState == ButterflyDriveState.DIFFERENTIAL) {
            return Differential.KV;
        }
        return Mecanum.KS;
    }
}
