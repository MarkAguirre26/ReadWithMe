package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Mark on 2/1/2017.
 */

public class PauseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);
        tools.setFullScreen(this);
    }

    public void cmd_pause_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        if (i == 1) {
            // Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
            // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //  startActivity(intent);
            startActivity(new Intent(getApplicationContext(), PlayOptionActivity.class));
            overridePendingTransition(0, 0);
            finish();
        } else if (i == 2) {
            finish();
        } else if (i == 3) {
            if (variables.activityCameFrom.contains("LessonActivity")) {
                startActivity(new Intent(getApplicationContext(), LessonActivity.class));
            } else if (variables.activityCameFrom.contains("g2A1Activity")) {
                startActivity(new Intent(getApplicationContext(), g2A1Activity.class));
            } else if (variables.activityCameFrom.contains("g3A1Activity")) {
                startActivity(new Intent(getApplicationContext(), g3A1Activity.class));
            } else if (variables.activityCameFrom.contains("g1A2Activity")) {
                startActivity(new Intent(getApplicationContext(), g1A2Activity.class));
            } else if (variables.activityCameFrom.contains("g2A3Activity")) {
                startActivity(new Intent(getApplicationContext(), g2A3Activity.class));
            } else if (variables.activityCameFrom.contains("g1l4_Activity")) {
                startActivity(new Intent(getApplicationContext(), g1l4_Activity.class));
            } else if (variables.activityCameFrom.contains("g1l5_Activity")) {
                startActivity(new Intent(getApplicationContext(), g1l5_Activity.class));
            } else if (variables.activityCameFrom.contains("g2A1Activity")) {
                startActivity(new Intent(getApplicationContext(), g2A1Activity.class));
            } else if (variables.activityCameFrom.contains("g2A2Activity")) {
                startActivity(new Intent(getApplicationContext(), g2A2Activity.class));
            } else if (variables.activityCameFrom.contains("g1l4_Activity")) {
                startActivity(new Intent(getApplicationContext(), g1l4_Activity.class));
            } else if (variables.activityCameFrom.contains("PlayThreeActivity")) {
                startActivity(new Intent(getApplicationContext(), PlayThreeActivity.class));
            } else if (variables.activityCameFrom.contains("Lesson_3_Activity_E ")) {
                startActivity(new Intent(getApplicationContext(), Lesson_3_Activity_E.class));
            } else if (variables.activityCameFrom.contains("g1l3_I_Activity ")) {
                startActivity(new Intent(getApplicationContext(), g1l3_I_Activity.class));
            } else if (variables.activityCameFrom.contains("g1l3_O_Activity ")) {
                startActivity(new Intent(getApplicationContext(), g1l3_O_Activity.class));
            } else if (variables.activityCameFrom.contains("g1l3_U_Activity")) {
                startActivity(new Intent(getApplicationContext(), g1l3_U_Activity.class));
            } else if (variables.activityCameFrom.contains("g2A3subActivity")) {
                startActivity(new Intent(getApplicationContext(), g2A3subActivity.class));
            } else if (variables.activityCameFrom.contains("g3A2Activity")) {
                startActivity(new Intent(getApplicationContext(), g3A2Activity.class));
            }
            overridePendingTransition(0, 0);
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
