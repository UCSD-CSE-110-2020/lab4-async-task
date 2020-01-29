package edu.ucsd.cse110.asynctaskapp;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Boolean isCancelled = false;
    private Button btnCount;
    private Button btnCancel;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCount = findViewById(R.id.buttonCount);
        btnCancel = findViewById(R.id.buttonCancel);
        textResult = findViewById(R.id.textResult);

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCancelled = false;
                CountToTenAsyncTask runner = new CountToTenAsyncTask();
                runner.execute();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              https://developer.android.com/reference/android/os/AsyncTask#cancel(boolean)
                isCancelled = true;
            }
        });
    }
    private class CountToTenAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            for (int i = 0; i < 10 && !isCancelled; i++) {
                publishProgress(String.valueOf(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (isCancelled.equals("true")) {

            } else {
                publishProgress("10");
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result){

        }
        @Override
        protected void onPreExecute(){

        }
        @Override
        protected void onProgressUpdate(String... text){
            textResult.setText(text[0]);
        }
    }
}


