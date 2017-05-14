package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GradeSelectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_select);
        tools.setFullScreen(this);
    }

    void selectGame() {
        startActivity(new Intent(getApplicationContext(), LesssonSelectActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    public void cmd_GradeSelect_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        switch (i) {
            case 1:
                selectGame();
                break;
            case 2:
                selectGame();
                break;
            case 3:
                selectGame();
                break;
        }
    }

    void gotoMain() {
        startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
        gotoMain();
    }
}
