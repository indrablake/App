package indrablake.com.directoryapps;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
        /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

        @Override
        public void run() {
            // This method will be executed once the timer is over
            // Start your app main activity
            Intent i = new Intent(Splash.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }, SPLASH_TIME_OUT);

    }
}
