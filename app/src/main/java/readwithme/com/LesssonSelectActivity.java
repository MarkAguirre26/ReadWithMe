package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class LesssonSelectActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessson_select);
        tools.setFullScreen(this);
        Button cmd_1, cmd_2, cmd_3, cmd_4, cmd_5, cmd_6, cmd_7, cmd_8, cmd_9, cmd_10;
        cmd_1 = (Button) findViewById(R.id.cmd_lesson_1);
        cmd_2 = (Button) findViewById(R.id.cmd_lesson_2);
        cmd_3 = (Button) findViewById(R.id.cmd_lesson_3);
        cmd_4 = (Button) findViewById(R.id.cmd_lesson_4);
        cmd_5 = (Button) findViewById(R.id.cmd_lesson_5);
        cmd_6 = (Button) findViewById(R.id.cmd_lesson_6);
        cmd_7 = (Button) findViewById(R.id.cmd_lesson_7);
        cmd_8 = (Button) findViewById(R.id.cmd_lesson_8);
        cmd_9 = (Button) findViewById(R.id.cmd_lesson_9);
        cmd_10 = (Button) findViewById(R.id.cmd_lesson_10);

        if (userInfo.grade.contains("1")) {
            cmd_6.setVisibility(View.GONE);
            cmd_7.setVisibility(View.GONE);
            cmd_8.setVisibility(View.GONE);
            cmd_9.setVisibility(View.GONE);
            cmd_10.setVisibility(View.GONE);
        } else if (userInfo.grade.contains("2")) {
            cmd_1.setVisibility(View.GONE);
            cmd_2.setVisibility(View.GONE);
            cmd_3.setVisibility(View.GONE);
            cmd_5.setVisibility(View.GONE);
            cmd_4.setVisibility(View.GONE);
            cmd_6.setVisibility(View.GONE);
            cmd_7.setVisibility(View.GONE);

        } else if (userInfo.grade.contains("3")) {
            cmd_1.setVisibility(View.GONE);
            cmd_2.setVisibility(View.GONE);
            cmd_3.setVisibility(View.GONE);
            cmd_4.setVisibility(View.GONE);
            cmd_5.setVisibility(View.GONE);
            cmd_8.setVisibility(View.GONE);
            cmd_9.setVisibility(View.GONE);
            cmd_10.setVisibility(View.GONE);
        }
    }

    public void cmd_lesson_Clicked(View view) {

        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        if (i == 0) {
            gotoBack();
        } else if (i == 1) {
            startActivity(new Intent(getApplicationContext(), PlayOneActivity.class));

        } else if (i == 2) {
            startActivity(new Intent(getApplicationContext(), PlayTwoActivity.class));


        } else if (i == 3) {
            startActivity(new Intent(getApplicationContext(), PlayThreeActivity.class));

        } else if (i == 4) {
            startActivity(new Intent(getApplicationContext(), PlayFourActivity.class));

        } else if (i == 5) {
            startActivity(new Intent(getApplicationContext(), PlayFiveActivity.class));
        } else if (i == 6) {
            startActivity(new Intent(getApplicationContext(), g3l1Activity.class));


        } else if (i == 7) {
            startActivity(new Intent(getApplicationContext(), g3l2Activity.class));


        }
        /////
        else if (i == 8) {
            startActivity(new Intent(getApplicationContext(), g2l1_Activity.class));
        } else if (i == 9) {
            startActivity(new Intent(getApplicationContext(), g2l2_Activity.class));


        } else if (i == 10) {
            startActivity(new Intent(getApplicationContext(), g2l3_Activity.class));


        }
        overridePendingTransition(0, 0);
        //  finish();

    }

    void gotoBack() {
        startActivity(new Intent(getApplicationContext(), PlayOptionActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        gotoBack();
    }
}
