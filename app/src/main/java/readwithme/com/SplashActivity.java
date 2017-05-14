package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tools.setFullScreen(this);
        try {
            Thread.sleep(3000);
        }catch (Exception ex){
            Log.d("err",ex.getMessage());
        }finally {
            startActivity(new Intent(getApplicationContext(),MainMenuActivity.class));
            overridePendingTransition(0,0);
            finish();
        }
    }
}
