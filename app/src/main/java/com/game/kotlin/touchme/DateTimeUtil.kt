package com.game.kotlin.touchme

import java.util.concurrent.TimeUnit

/**
 * Created by KS115 on 3/14/18.
 */
fun toReadableTime(millis: Long): String {
    return String.format("%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)))
}