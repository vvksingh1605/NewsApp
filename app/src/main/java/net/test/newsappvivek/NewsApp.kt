package net.test.newsappvivek;

import android.app.Application
import androidx.work.*
import net.test.newsappvivek.worker.DataWorker
import java.util.concurrent.TimeUnit


class NewsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        val refreshWork = PeriodicWorkRequest.Builder(
            DataWorker::class.java, 2, TimeUnit.HOURS
        )
        val constraints= Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val data = Data.Builder()
        //data.putString("delete", "deleteTable")// can be customised
        refreshWork.setInputData(data.build())
        refreshWork.setConstraints(constraints)
        WorkManager.getInstance(applicationContext).enqueue(refreshWork.build())
    }

}
