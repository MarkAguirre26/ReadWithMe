package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuitActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quit);
        tools.setFullScreen(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // stopService(new Intent(this, BackgroundAudioService.class));
    }

    public void cmd_quit_clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        switch (i) {
            case 1:
                stopService(new Intent(getApplicationContext(), BackgroundAudioService.class));
                finish();
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                break;
            case 2:
                goBack();
                break;
        }
    }

    void goBack() {
        if (variables.exit == 0) {
            startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
        } else if (variables.exit == 1) {
            startActivity(new Intent(getApplicationContext(), PlayOptionActivity.class));
        }
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        goBack();
    }
}
