package com.example.medicloud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Health_tips extends AppCompatActivity {

//    long queueid;
//    DownloadManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);

        Button btn3 = (Button) findViewById(R.id.button9);
        Button btn4 = (Button) findViewById(R.id.button11);
        Button btn5 = (Button) findViewById(R.id.button12);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(Health_tips.this, Physical_activity.class);
                startActivity(int3);
            }
        });
         btn4.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent int4 = new Intent(Health_tips.this, diet.class);
                 startActivity(int4);
             }
         });
         btn5.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent int5 = new Intent(Health_tips.this, Tobacco_usage.class);
                 startActivity(int5);
             }
         });

//        BroadcastReceiver receiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String action = intent.getAction();
//                if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)){
//                    DownloadManager.Query query= new DownloadManager.Query();
//                    query.setFilterById(queueid);
//                }
//            }
//        };
    }

//    public void Download_Click(View v)
//    {
//        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//        DownloadManager.Request request = new DownloadManager.Request(Uri.parse("https://apps.who.int/iris/bitstream/handle/10665/205887/B5084.pdf;jsessionid=268BE2D923B303AD314BA891B7CB70AD?sequence=1"));
//        queueid = dm.enqueue(request);
//    }

    public void Browser3 (View view){
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://apps.who.int/iris/bitstream/handle/10665/205887/B5084.pdf;jsessionid=268BE2D923B303AD314BA891B7CB70AD?sequence=1"));
        startActivity(browserIntent);
    }
    public void browser4 (View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://symptoms.webmd.com/default.htm"));
        startActivity(browserIntent);
    }
}
