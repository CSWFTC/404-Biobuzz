package org.firstinspires.ftc.teamcode.helpers

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.time.Duration.Companion.milliseconds

@Configurable
class Catapult(hardwareMap: HardwareMap) {
    companion object {
        @JvmField
        var ticksPerDeg = 537.7 / 720.0

        @JvmField
        var launchAngle = 45.0

        @JvmField
        var startAngle = -60.0

        @JvmField
        var marginDeg = 5

        @JvmField
        var dir1 = DcMotorSimple.Direction.REVERSE

        @JvmField
        var dir2 = DcMotorSimple.Direction.FORWARD
    }

    private val motor1 = hardwareMap.get("catapult1") as DcMotor
    private val motor2 = hardwareMap.get("catapult2") as DcMotor

    init {
        motor1.direction = dir1
        motor2.direction = dir2
        motor1.power = -1.0
        motor2.power = -1.0
        motor1.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        motor2.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
    }

    var active = false
        private set

    suspend fun fire() {
        if (active)
            return
        active = true
        motor1.power = 1.0
        motor2.power = 1.0
        while (((motor1.currentPosition / ticksPerDeg) + startAngle) < launchAngle)
            yield()
        motor1.power = -1.0
        motor2.power = -1.0
        while (motor1.currentPosition > marginDeg * ticksPerDeg)
            yield()
        active = false
    }

    suspend fun reset() {
        if (active)
            return
        motor1.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor2.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        delay(1000.milliseconds)
        motor1.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        motor2.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
    }
}