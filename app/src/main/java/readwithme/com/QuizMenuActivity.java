package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);
        tools.setFullScreen(this);
        Button cmd_1, cmd_2, cmd_3, cmd_4, cmd_5;
        cmd_1 = (Button) findViewById(R.id.cmd_quiz_1);
        cmd_2 = (Button) findViewById(R.id.cmd_quiz_2);
        cmd_3 = (Button) findViewById(R.id.cmd_quiz_3);
        cmd_4 = (Button) findViewById(R.id.cmd_quiz_4);
        cmd_5 = (Button) findViewById(R.id.cmd_quiz_5);
        if (userInfo.grade.contains("2")) {
            cmd_5.setVisibility(View.INVISIBLE);
            cmd_4.setVisibility(View.INVISIBLE);
        } else if (userInfo.grade.contains("3")) {
            cmd_5.setVisibility(View.INVISIBLE);
            cmd_4.setVisibility(View.INVISIBLE);
            cmd_3.setVisibility(View.INVISIBLE);
        }
    }

    public void cmd_quizMenu_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        if (i == 0) {
            back();
        } else if (i == 1) {

            if (userInfo.grade.contains("1")) {
                variables.activityCameFrom = "g1q1_Activity";
                startActivity(new Intent(this, g1q1_Activity.class));
            } else if (userInfo.grade.contains("2")) {

                variables.activityCameFrom = "g2A1Activity";
                startActivity(new Intent(this, g2A1Activity.class));
            } else if (userInfo.grade.contains("3")) {

                variables.activityCameFrom = "g3Q1Activity";
                startActivity(new Intent(this, g3Q1Activity.class));
            }
            variables.RemarkIndex = "1";
            variables.SetIndex = "Quiz 1";
        } else if (i == 2) {
            if (userInfo.grade.contains("1")) {
                variables.activityCameFrom = "LessonTwoActivity";
                startActivity(new Intent(this, LessonTwoActivity.class));
            } else if (userInfo.grade.contains("2")) {
                variables.activityCameFrom = "g2q2Activity";
                startActivity(new Intent(this, g2q2Activity.class));
            } else if (userInfo.grade.contains("3")) {
                variables.activityCameFrom = "g3Q2Activity";
                startActivity(new Intent(this, g3Q2Activity.class));
            }
            variables.RemarkIndex = "2";
            variables.SetIndex = "Quiz 2";
        } else if (i == 3) {
            if (userInfo.grade.contains("1")) {
                variables.activityCameFrom = "g1q3_Activity";
                startActivity(new Intent(this, g1q3_Activity.class));
            } else if (userInfo.grade.contains("2")) {
                variables.activityCameFrom = "g2q3Activity";
                startActivity(new Intent(this, g2q3Activity.class));
            }
            variables.RemarkIndex = "3";
            variables.SetIndex = "Quiz 3";
        } else if (i == 4) {
            variables.activityCameFrom = "g1q4_Activity";
            startActivity(new Intent(this, g1q4_Activity.class));
            variables.RemarkIndex = "4";
            variables.SetIndex = "Quiz 4";
        } else if (i == 5) {
            variables.activityCameFrom = "g1q5_Activity";
            startActivity(new Intent(this, g1q5_Activity.class));
            variables.RemarkIndex = "5";
            variables.SetIndex = "Quiz 5";
        }
        overridePendingTransition(0, 0);
        //finish();
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
