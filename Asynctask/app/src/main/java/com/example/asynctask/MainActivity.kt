package com.example.asynctask


import android.os.AsyncTask
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var btn: Button?=null ;
    private var statusText: TextView ?=null;
    private var statusProgress: ProgressBar?=null;
    private var stringBuilder:StringBuilder?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//Get the UI element from layout and initialize the UI element
        btn= findViewById<Button>(R.id.btn_async);
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        statusText=findViewById<TextView>(R.id.statusText)
        statusText?.movementMethod=ScrollingMovementMethod()
        statusProgress=findViewById(R.id.progressBar)
        progressBar.max=2;
        progressBar.progress = 0
        stringBuilder = StringBuilder("---------------------\nAsync task Demo\n---------------------")
        stringBuilder?.append("\nWaiting to start the Async Jobs\n")
        statusText?.text = "${stringBuilder.toString()}"
// Set on Click listener on start async button
        btn?.setOnClickListener(View.OnClickListener {
// Declare the AsyncTask and start the execution
            var task:MyAsyncTask = MyAsyncTask()
            task.execute("www.nplix.com","www.debugandroid.com")
        })
    }
    // Create inner class by extending the AsyncTask
    inner class MyAsyncTask : AsyncTask<String, Int, Int>(){
        //Override the doInBackground method
        override fun doInBackground(vararg params: String?): Int {
            val count:Int=params.size
            var index=0
            while (index<count){
                Log.d("Kotlin","In doInBackground Method and Total parameter passed is:$count " + "and processing $index with value: ${params[index]}")
// Publish the progress
                publishProgress(index+1)
//Sleeping for 1 seconds
                Thread.sleep(1000)
                index++
            }
            return count;
        }
        // Override the onProgressUpdate method to post the update on main thread
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            if(values[0]!=null) {
                statusProgress?.progress = values[0] as Int
                stringBuilder?.append("Publish post called with ${values[0]}\n")
                statusText?.text = stringBuilder.toString()
            }
        }
        // Setup the intial UI before execution of the background task
        override fun onPreExecute() {
            super.onPreExecute()
            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
            stringBuilder?.append("Async task started... \n In PreExecute Method \n")
            statusText?.text = "${stringBuilder.toString()}"
            progressBar.visibility=View.VISIBLE
            progressBar.progress = 0
            Log.d("Kotlin","On PreExecute Method")
        }
        // Update the final status by overriding the OnPostExecute method.
        override fun onPostExecute(result: Int?) {
            super.onPostExecute(result)
            stringBuilder?.append("-------------------------------\nSystem Hacked Sucessfully !\n" +
                    "-------------------------------\n")
            statusText?.text = "${stringBuilder.toString()}"
            Log.d("Kotlin","On Post Execute and size of String is:$result")
        }
    }
}


