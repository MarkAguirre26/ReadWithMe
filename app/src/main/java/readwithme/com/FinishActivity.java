package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FinishActivity extends Activity {
    CountDownTimer cTimer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        tools.setFullScreen(this);
        startTimer();

    }

    void startTimer() {
        cTimer = new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                startActivity(new Intent(getApplicationContext(), LesssonSelectActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        };
        cTimer.start();
    }

    void cancelTimer() {
        if (cTimer != null)
            cTimer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }
}
