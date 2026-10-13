package org.firstinspires.ftc.teamcode.helpers

class GamepadButton {
    var previous = false
    fun justPressed(now: Boolean): Boolean {
        val result = now && !previous
        previous = now
        return result
    }
}