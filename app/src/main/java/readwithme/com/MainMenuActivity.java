package readwithme.com;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        tools.setFullScreen(this);


        //startService(new Intent(getApplicationContext(), BackgroundAudioService.class));

         /* if(variables.PlayerName.length()>1){
                    startActivity(new Intent(getApplicationContext(), AskPlayerActivity.class));
                }else{
                    startActivity(new Intent(getApplicationContext(), PlayerListActivity.class));
                }*/

    }



    public void cmd_Menu_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        switch (i) {
            case 1:
                PlayerHelper db = new PlayerHelper(getApplicationContext());
                if (db.getPlayerCount() > 0) {
                    db.getPlayerName(1);
                    startActivity(new Intent(getApplicationContext(), AskPlayerActivity.class));
                }else{
                    startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                }


                overridePendingTransition(0, 0);
                finish();
                break;
            case 2:
                variables.exit = 0;
                startActivity(new Intent(getApplicationContext(), QuitActivity.class));
                overridePendingTransition(0, 0);
                finish();
                break;
            case 3:
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                overridePendingTransition(0, 0);
                finish();
                break;
            case 8:
                startActivity(new Intent(getApplicationContext(), ProgressActivity.class));
                overridePendingTransition(0, 0);
                finish();
                break;

        }
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
