package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class AskPlayerActivity extends Activity {

    TextView txt_Askname;
    ImageView img_teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_player);
        tools.setFullScreen(this);
        txt_Askname = (TextView) findViewById(R.id.txt_Askname);
        img_teacher = (ImageView) findViewById(R.id.img_teacher);

        PlayerHelper db = new PlayerHelper(getApplicationContext());
      /*  if (db.getPlayerCount() > 0) {
            db.getPlayerName(1);
        }else{
            userInfo.name = "Juan";
        }*/
        db.getPlayerName(1);

        if (userInfo.teacher == 1) {
            setTeacher(R.drawable.mr_favian);
        } else {
            setTeacher( R.drawable.ms_sophia);
        }
        Log.d("TEACHER",userInfo.teacher+" "+userInfo.name);

    }

    void setTeacher(int drawable) {
        img_teacher.setImageResource(drawable);
        txt_Askname.setText(" "+userInfo.name +"?");
    }

    public void cmd_AskName_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        switch (i) {
            case 1:

                startActivity(new Intent(getApplicationContext(), PlayerListActivity.class));
                overridePendingTransition(0, 0);
                finish();
                break;
            case 2:
               // PlayerHelper db = new PlayerHelper(getApplicationContext());
               /* if (db.getPlayerCount() > 0) {
                    startActivity(new Intent(getApplicationContext(), PlayOptionActivity.class));
                }else{
                    startActivity(new Intent(getApplicationContext(), PlayerListActivity.class));
                }*/
                startActivity(new Intent(getApplicationContext(), GreetingActivity.class));
                overridePendingTransition(0, 0);
                finish();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }
}
