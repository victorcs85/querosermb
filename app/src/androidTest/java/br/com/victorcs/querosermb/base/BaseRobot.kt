package br.com.victorcs.querosermb.base

import android.app.Activity
import android.app.Instrumentation
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import org.hamcrest.CoreMatchers
import org.koin.test.KoinTest

abstract class BaseRobot : KoinTest {
    fun mockResultOKIntent() {
        Intents.intending(
            CoreMatchers.not(
                IntentMatchers.isInternal(),
            ),
        ).respondWith(
            Instrumentation.ActivityResult(
                Activity.RESULT_OK,
                null,
            ),
        )
    }
}
