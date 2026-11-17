package org.firstinspires.ftc.teamcode.helpers

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import kotlinx.coroutines.yield
import kotlin.time.DurationUnit
import kotlin.time.TimeSource

@Configurable
class Outtake(hardwareMap: HardwareMap) {
    companion object {
        @JvmField
        var targetSpeed = 2800.0

        // todo: tune
        @JvmField
        var kP = 0.01

        @JvmField
        var kI = 0.0

        @JvmField
        var kD = 0.0

        @JvmField
        var direction = DcMotorSimple.Direction.REVERSE
    }

    private val motor = hardwareMap.get("outtake") as DcMotor
    var on = false

    init {
        motor.direction = direction
    }

    suspend fun loop() {
        var integral = 0.0
        var prevE = 0.0
        var prevPos = 0
        val ts = TimeSource.Monotonic
        var prevTime = ts.markNow()
        while (true) {
            val pos = motor.currentPosition
            val now = ts.markNow()
            val dt = (now - prevTime).toDouble(DurationUnit.SECONDS)
            val vel = (pos - prevPos) / dt
            prevPos = pos
            prevTime = now
            val error = if (on) {
                targetSpeed
            } else {
                0.0
            } - vel
            val dedt = (error - prevE) / dt
            integral += error * dt
            prevE = error
            motor.power = kP * error + kI * integral + kD * dedt
            yield()
        }
    }

    fun toggle() {
        on = !on
    }
}