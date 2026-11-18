package org.firstinspires.ftc.teamcode.helpers

import kotlinx.coroutines.yield

class GamepadButton(val button: () -> Boolean) {
    var justPressed = false
        private set
    var pressed = false
        private set

    suspend fun loop() {
        var previous = false
        while (true) {
            pressed = button()
            justPressed = pressed && !previous
            previous = pressed
            yield()
        }
    }
}