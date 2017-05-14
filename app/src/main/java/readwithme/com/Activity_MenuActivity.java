package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity_MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__menu);
        tools.setFullScreen(this);
        Button cmd_1, cmd_2, cmd_3, cmd_4, cmd_5;
        cmd_1 = (Button) findViewById(R.id.cmd_activity_1);
        cmd_2 = (Button) findViewById(R.id.cmd_activity_2);
        cmd_3 = (Button) findViewById(R.id.cmd_activity_3);
        cmd_4 = (Button) findViewById(R.id.cmd_activity_4);
        cmd_5 = (Button) findViewById(R.id.cmd_activity_5);
        if (userInfo.grade.contains("2")) {
            cmd_5.setVisibility(View.INVISIBLE);
            cmd_4.setVisibility(View.INVISIBLE);

        } else if (userInfo.grade.contains("3")) {
            cmd_5.setVisibility(View.INVISIBLE);
            cmd_4.setVisibility(View.INVISIBLE);
            cmd_3.setVisibility(View.INVISIBLE);

        }
    }

    public void cmd_activityMenu_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        if (i == 0) {
            back();
        } else if (i == 1) {
            variables.RemarkIndex = "1";
            variables.SetIndex = "Activity 1";
            if (userInfo.grade.contains("1")) {
                variables.activityCameFrom = "LessonActivity";
                startActivity(new Intent(this, LessonActivity.class));
            } else if (userInfo.grade.contains("2")) {
                variables.activityCameFrom = "g2A2Activity";
                startActivity(new Intent(this, g2A2Activity.class));
            } else if (userInfo.grade.contains("3")) {
                variables.activityCameFrom = "g3A1Activity";
                startActivity(new Intent(this, g3A1Activity.class));
            }


        } else if (i == 2) {
            variables.RemarkIndex = "2";
            variables.SetIndex = "Activity 2";
            if (userInfo.grade.contains("1")) {
                variables.activityCameFrom = "g1A2Activity";
                startActivity(new Intent(this, g1A2Activity.class));
            } else if (userInfo.grade.contains("2")) {
                variables.activityCameFrom = "g2A3Activity";
                startActivity(new Intent(this, g2A3Activity.class));
            } else if (userInfo.grade.contains("3")) {
                variables.activityCameFrom = "g3A2Activity";
                startActivity(new Intent(this, g3A2Activity.class));
            }

        } else if (i == 3) {
            variables.RemarkIndex = "3";
            variables.SetIndex = "Activity 3";
            if (userInfo.grade.contains("1")) {
                variables.activityCameFrom = "";
                startActivity(new Intent(this, Lesson_3_Activity_menu.class));
            } else if (userInfo.grade.contains("2")) {
                variables.activityCameFrom = "g2A3subActivity";
                startActivity(new Intent(this, g2A3subActivity.class));
            }

        } else if (i == 4) {
            variables.RemarkIndex = "4";
            variables.SetIndex = "Activity 4";
            variables.activityCameFrom = "g1l4_Activity";
            startActivity(new Intent(this, g1l4_Activity.class));
        } else if (i == 5) {
            variables.RemarkIndex = "5";
            variables.SetIndex = "Activity 5";
            variables.activityCameFrom = "g1l5_Activity";
            startActivity(new Intent(this, g1l5_Activity.class));
        }
        overridePendingTransition(0, 0);
        finish();

    }

    void back() {
        startActivity(new Intent(getApplicationContext(), PlayOptionActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }
}
