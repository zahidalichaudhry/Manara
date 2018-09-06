package com.itpvt.manara;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerButton;
import com.romainpiel.shimmer.ShimmerTextView;

public class Spash extends AppCompatActivity {
    ImageView imageView;
    Shimmer shimmer,shimmer2;
    ShimmerTextView shimmer_tv;
    ShimmerButton shimmer_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);
        imageView = (ImageView)findViewById(R.id.image);
        shimmer_tv = (ShimmerTextView)findViewById(R.id.shimmer_tv);
        shimmer_btn = (ShimmerButton)findViewById(R.id.shimmer_btn);
        shimmer = new Shimmer();
        shimmer.start(shimmer_tv);
        shimmer2 = new Shimmer();
        shimmer.start(shimmer_tv);
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    if(isNetworkAvailable())
                    {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else
                    {
                        Intent intent = new Intent(getApplicationContext(), Network_Error.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
