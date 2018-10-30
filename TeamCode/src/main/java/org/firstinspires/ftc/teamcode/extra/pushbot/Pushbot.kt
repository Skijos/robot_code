package org.firstinspires.ftc.teamcode.extra.pushbot

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.Range

@TeleOp(name = "Pushbot", group = "PUSHBOT")
class Pushbot : OpMode() {
    /** The Hardware of the robot */
    private val hardware = PushbotHardware()

    private var clawOffset = 0.0

    override fun init() {
        // Initialize the hardware (all the motors) using the TeleOp's hardware map
        hardware.init(hardwareMap)
    }

    override fun loop() {
        // ==================
        //      MOVEMENT
        // ==================

        /** How much does it want to move forward - using LT */
        val throttle: Double = gamepad1.left_trigger.toDouble()
        /** How much does it want to move forward - using LT */
        val brake: Double = gamepad1.left_trigger.toDouble()

        /** How much does it want move left-right - using the right stick's X axis */
        val horizontalMovement: Double = -gamepad1.right_stick_x.toDouble()

        // Prevent overflow by clipping the values between +1 and -1
        hardware.leftMotorPower = Range.clip(throttle - brake + horizontalMovement, -1.0, 1.0)
        hardware.rightMotorPower = Range.clip(throttle - brake - horizontalMovement, -1.0, 1.0)

        // ==================
        //      GRABBING
        // ==================

        // If A is pressed then move the arm down, if Y is pressed, move it up
        val armPower = when {
            gamepad1.a -> hardware.ARM_POWER
            gamepad1.y -> -hardware.ARM_POWER
            else -> 0.0
        }

        hardware.armMotorPower = armPower

        clawOffset += when {
            gamepad1.right_bumper -> hardware.CLAW_SPEED
            gamepad1.left_bumper -> -hardware.CLAW_SPEED
            else -> 0.0
        }

        clawOffset = Range.clip(clawOffset, -0.5, 0.5)
        hardware.leftClawServo.position = hardware.MID_SERVO + clawOffset
        hardware.rightClawServo.position = hardware.MID_SERVO - clawOffset

        // Debugging data
        telemetry.addData("Movement:", "Forward: ${throttle - brake}, Sideways: $horizontalMovement")
    }
}