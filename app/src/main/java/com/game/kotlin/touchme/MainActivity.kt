package com.game.kotlin.touchme

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var totalCount: Int = 0
    var gameStateFlag: Boolean = false
    var mTimer: CountDownTimer? = null

    companion object {
        const val TOTAL_TIME: Long = 10000
        const val INTERVAL_LIMIT: Long = 1000
        const val INITIAL_TIMER_VALUE: String = "00:00"
        const val RESET_COUNTER_VALUE:String = "0"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    /**
     * Set listener to play area also updated the touch counter
     */
    fun setClickListenerToPlayArea(isClickable: Boolean) {
        if (isClickable) {
            touchArea.setOnClickListener {
                totalCount++
                tvTouchCounter.text = totalCount.toString()
            }
        } else {
            resetPlayArea()
        }
    }

    /**
     * Remove touch listener and reset the time
     */
    private fun resetPlayArea() {
        touchArea.setOnClickListener(null)
        tvTimer.text = INITIAL_TIMER_VALUE
    }

    /**
     * Start the counting till 10 sec
     */
    private fun startCounter() {
        mTimer = object : CountDownTimer(TOTAL_TIME, INTERVAL_LIMIT) {
            override fun onFinish() {
                setClickListenerToPlayArea(false)
            }

            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = toReadableTime(millisUntilFinished)
            }
        }

        mTimer?.start()
    }

    fun toggleState(view: View) {
        if (!gameStateFlag) {
            //start counter reset the touch count
            btnStart.text = getString(R.string.btn_stop_label)
            tvTouchCounter.text = RESET_COUNTER_VALUE
            totalCount = 0
            tvTimer.text = INITIAL_TIMER_VALUE
            gameStateFlag = true
            setClickListenerToPlayArea(true)
            startCounter()
        } else {
            //TODO stop counter and show the result
            gameStateFlag = false
            btnStart.text = getString(R.string.btn_start_label)
            setClickListenerToPlayArea(false)
            stopCounter()
        }
    }

    private fun stopCounter() {
        mTimer?.cancel()
    }
}
