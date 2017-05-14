package readwithme.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SignupActivity extends Activity {

    RelativeLayout rel;
    View view;
    int tagIndexGrade = 1, tagIndexTeacher =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        tools.setFullScreen(this);

        rel = (RelativeLayout) findViewById(R.id.activity_signup);
        view = getLayoutInflater().inflate(R.layout.layout_signup, null);
        rel.removeAllViews();
        rel.addView(view);


    }

    void addViews(int i) {
        view = getLayoutInflater().inflate(i, null);
        rel.removeAllViews();
        rel.addView(view);
    }

    public void cmd_signup_Namenext_Clicked(View view) {
        EditText txt_name_signup = (EditText) findViewById(R.id.txt_name_signup);
        final PlayerHelper db = new PlayerHelper(this);
        String n = txt_name_signup.getText().toString();
        if (db.getCheckDuplicate(n) >= 1) {
            Toast.makeText(getApplicationContext(), "Already Exist", Toast.LENGTH_SHORT).show();
        } else {
            userInfo.name = n;
            addViews(R.layout.layout_age);
        }

    }

    public void cmd_age_left_Clicked(View view) {
        userInfo.age = "";
        addViews(R.layout.layout_signup);
    }

    public void cmd_age_right_Clicked(View view) {
        EditText txt_age_singup = (EditText) findViewById(R.id.txt_age_singup);
        userInfo.age = txt_age_singup.getText().toString();
        addViews(R.layout.layout_grade);
    }


    public void cmd_grade_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        if (i == 1) {
            userInfo.grade = "";
            addViews(R.layout.layout_age);
        }  else if (i == 3) {
            tagIndexGrade = 1;
            addViews(R.layout.layout_teacher);
            Toast.makeText(this, "Grade 1 Selected", Toast.LENGTH_SHORT).show();
        } else if (i == 4) {
            tagIndexGrade = 2;
            addViews(R.layout.layout_teacher);
            Toast.makeText(this, "Grade 2 Selected", Toast.LENGTH_SHORT).show();
        } else if (i == 5) {
            tagIndexGrade = 3;
            addViews(R.layout.layout_teacher);
            Toast.makeText(this, "Grade 3 Selected", Toast.LENGTH_SHORT).show();
        }
        userInfo.grade = tagIndexGrade+"";
    }


    public void cmd_teacher_signUp(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        if (i == 1) {

            addViews(R.layout.layout_grade);
        } else if (i == 3) {
            tagIndexTeacher = 1;
            addViews(R.layout.layout_save);
            Toast.makeText(this, "Mr. Favian Selected", Toast.LENGTH_SHORT).show();
        } else if (i == 4) {
            tagIndexTeacher = 2;
            addViews(R.layout.layout_save);
            Toast.makeText(this, "Ms. Sophia Selected", Toast.LENGTH_SHORT).show();
        }
        userInfo.teacher = tagIndexTeacher;
        //Toast.makeText(this, userInfo.teacher+"", Toast.LENGTH_SHORT).show();
    }

    public void cmd_save_sign_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        if (i == 1) {

          //  Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            final PlayerHelper db = new PlayerHelper(this);
            db.updateAllReamark();
            db.addPlayer(new Player(0, userInfo.name, userInfo.age, userInfo.grade, userInfo.teacher, "1"));
            Intent intent = new Intent(getApplicationContext(), GreetingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
            this.overridePendingTransition(0, 0);
            this.finish();
         //   gotoMain();

        } else if (i == 2) {
            gotoMain();
        }
    }

    void gotoMain() {
        startActivity(new Intent(getApplicationContext(), PlayerListActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoMain();
    }
}
