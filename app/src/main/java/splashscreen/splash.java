package splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import heisenber737.ukpolice.MainActivity;
import heisenber737.ukpolice.R;

public class splash extends AppCompatActivity {
     int splashtime=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splash.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        },splashtime);

    }
}
