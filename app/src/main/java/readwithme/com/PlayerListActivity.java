package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerListActivity extends Activity {

    ListView list;
    PlayerAdapter adapter;
    public PlayerListActivity CustomListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);
        tools.setFullScreen(this);
        list = (ListView) findViewById(R.id.lv_playerList);
        refreshList();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (variables.CustomListViewValuesArr.size() >= 1) {
                    userInfo.cn = Integer.valueOf(((TextView) view.findViewById(R.id.txt1)).getTag().toString());
                    userInfo.name = ((TextView) view.findViewById(R.id.txt1)).getText().toString();
                    PlayerHelper db = new PlayerHelper(getApplicationContext());
                    db.updateAllReamark();
                    db.updatePlayer(userInfo.cn);
                    startActivity(new Intent(getApplicationContext(), AskPlayerActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    refreshList();
                }
            }
        });
    }

    public void cmd_PlayerList_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        switch (i) {
            case 1:
              //  startActivity(new Intent(getApplicationContext(), SelectTeacherActivity.class));
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                overridePendingTransition(0, 0);
                finish();
                break;
            case 2:
                goBack();
                break;
        }
    }

    private void refreshList() {
        CustomListView = this;
        tools.ReadPlayer(this);
        adapter = new PlayerAdapter(CustomListView, variables.CustomListViewValuesArr);
        list.setAdapter(adapter);
    }

    void goBack() {
        startActivity(new Intent(getApplicationContext(), AskPlayerActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBack();
    }
}
