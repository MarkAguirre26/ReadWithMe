package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GreetingActivity extends Activity {
    ImageView img_teacher;
    TextView txt_greename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);
        tools.setFullScreen(this);
        img_teacher = (ImageView) findViewById(R.id.img_teacher_greeting);
        txt_greename = (TextView) findViewById(R.id.txt_greename);


        if (userInfo.teacher == 1) {
            img_teacher.setImageResource(R.drawable.mr_favian);
        } else {
            img_teacher.setImageResource(R.drawable.ms_sophia);
        }
        txt_greename.setText(userInfo.name);
    }


    public void cmd_greeting_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        switch (i) {
            case 1:
                back();
                break;
            case 2:
                startActivity(new Intent(getApplicationContext(), PlayOptionActivity.class));
                overridePendingTransition(0, 0);
                finish();
                break;

        }
    }

    void back() {
        startActivity(new Intent(getApplicationContext(), AskPlayerActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }
}
