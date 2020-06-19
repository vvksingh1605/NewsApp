package net.test.newsappvivek.worker;

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import net.test.newsappvivek.db.AppDatabase
import java.lang.Exception
//Coroutine worker to delete the store data after 2 hours
class DataWorker(val context: Context,workerParams: WorkerParameters): CoroutineWorker(context,workerParams) {
     override suspend fun doWork(): Result {
         try {

             val mdB by lazy {
                 AppDatabase.getInstance(context)
             }
             mdB.articlesDao().delete()
             Log.d("data deleted","true");

         }catch (e:Exception){
             Log.d("data deleted","false");

             e.printStackTrace()
             return Result.failure(workDataOf() )
         }
         return Result.success(workDataOf() )


     }

 }