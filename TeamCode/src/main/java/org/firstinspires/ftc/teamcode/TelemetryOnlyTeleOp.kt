package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime

@TeleOp(name = "TelemetryOnlyTeleOp", group = "TestOpMode")
class TelemetryOnlyTeleOp() : OpMode() {
    private val stopwatch: ElapsedTime = ElapsedTime()

    override fun init() {
        telemetry.addData("Telemetry:", "Test-Data")
        stopwatch.reset()
    }

    override fun loop() {
        telemetry.addData("Telemetry:", "Elapsed time: (%.2f)", stopwatch.seconds())
    }

}