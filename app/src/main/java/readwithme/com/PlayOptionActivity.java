package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PlayOptionActivity extends Activity {

    Button cmd_play_option, cmd_quiz_option, cmd_activity_option;
    ImageView img_teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_option);
        tools.setFullScreen(this);
        cmd_play_option = (Button) findViewById(R.id.cmd_play_option);
        cmd_quiz_option = (Button) findViewById(R.id.cmd_quiz_option);
        cmd_activity_option = (Button) findViewById(R.id.cmd_activity_option);
        img_teacher = (ImageView) findViewById(R.id.img_teacher_option);
        if (userInfo.teacher == 1) {
            setTeacher(R.drawable.owl_spead_blue);
        } else {
            setTeacher(R.drawable.owl_spead);
        }


    }

    public void cmd_quit_Clicked(View view) {
        variables.exit = 1;
        startActivity(new Intent(getApplicationContext(), QuitActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    public void cmd_profile_Clicked(View view) {
        startActivity(new Intent(this, ProgressActivity.class));
        overridePendingTransition(0, 0);
    }

    void setTeacher(int drawable) {
        img_teacher.setImageResource(drawable);

    }

    public void cmd_plaOption_Clicked(View view) {


        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        if (i == 1) {

            startActivity(new Intent(getApplicationContext(), LesssonSelectActivity.class));
        } else if (i == 2) {
            variables.PlayOptionIndex = 1;
            startActivity(new Intent(getApplicationContext(), QuizMenuActivity.class));
        } else if (i == 3) {
            variables.PlayOptionIndex = 2;
            startActivity(new Intent(getApplicationContext(), Activity_MenuActivity.class));
        } else {
            gotoBack();
        }
        overridePendingTransition(0, 0);
        finish();
    }


    private void gotoBack() {

        startActivity(new Intent(getApplicationContext(), GreetingActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        gotoBack();
    }
}
