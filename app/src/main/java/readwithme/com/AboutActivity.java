package readwithme.com;

import android.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import readwithme.com.tools;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        tools.setFullScreen(this);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }


}
