package org.firstinspires.ftc.teamcode.roverruckus

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo


class RoverRuckusHardware {
    private lateinit var leftMotor: DcMotor
    private lateinit var rightMotor: DcMotor

    private lateinit var leftFrontServo: Servo
    private lateinit var rightFrontServo: Servo
    private lateinit var leftBackServo: Servo
    private lateinit var rightBackServo: Servo

    var leftMotorPower: Double = 0.0
        // Custom setters for caching system
        set(value) {
            // Only set the power of the motor if the last "frame's" power is
            // different from the current one
            if (value != field) {
                field = value
                leftMotor.power = field
            }
        }

    var rightMotorPower: Double = 0.0
        set(value) {
            if (value != field) {
                field = value
                rightMotor.power = field
            }
        }

    fun init(hardwareMap: HardwareMap){
        leftMotor=hardwareMap.get(DcMotor::class.java, "left_")
        rightMotor = hardwareMap.get(DcMotor::class.java, "right_motor")
        leftFrontServo = hardwareMap.get(Servo::class.java, "left_servo")
        rightFrontServo = hardwareMap.get(Servo::class.java, "left_servo_2")
        leftBackServo = hardwareMap.get(Servo::class.java, "right_servo")
        rightBackServo = hardwareMap.get(Servo::class.java, "right_servo_2")

        leftMotor.power = 0.0
        rightMotor.power = 0.0

        leftMotor.direction = DcMotorSimple.Direction.FORWARD
        rightMotor.direction = DcMotorSimple.Direction.REVERSE

        leftMotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        rightMotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
    }
}