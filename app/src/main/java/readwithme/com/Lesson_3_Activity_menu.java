package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Lesson_3_Activity_menu extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_3__menu);
        tools.setFullScreen(this);



    }

    public void cmd_Lesson3_play_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        if (i == 1) {
            variables.activityCameFrom = "PlayThreeActivity";
            startActivity(new Intent(this, PlayThreeActivity.class));

        } else if (i == 2) {
            variables.activityCameFrom = "Lesson_3_Activity_E";
            startActivity(new Intent(this, Lesson_3_Activity_E.class));

        }else if (i == 3) {
            variables.activityCameFrom = "g1l3_I_Activity";
            startActivity(new Intent(this, g1l3_I_Activity.class));

        }else if (i == 4) {
            variables.activityCameFrom = "g1l3_O_Activity";
            startActivity(new Intent(this, g1l3_O_Activity.class));

        }else if (i == 5) {
            variables.activityCameFrom = "g1l3_U_Activity";
            startActivity(new Intent(this, g1l3_U_Activity.class));

        }
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Activity_MenuActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }
}
