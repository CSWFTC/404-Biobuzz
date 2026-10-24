package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.helpers.Drivetrain;

public class Constants {
    public static MecanumConfig drivetrainConfig = new MecanumConfig(
            c -> {
                c.frontLeftName.set("frontLeft");
                c.backLeftName.set("rearLeft");
                c.frontRightName.set("frontRight");
                c.backRightName.set("rearRight");

                c.frontLeftDirection.set(Drivetrain.flDirection);
                c.backLeftDirection.set(Drivetrain.rlDirection);
                c.frontRightDirection.set(Drivetrain.frDirection);
                c.backRightDirection.set(Drivetrain.rrDirection);
            }
    );

    public static Follower create(HardwareMap h) {
        // return new Follower(Drivetrain, Localizer, Foresight);
        return null;
    }
}