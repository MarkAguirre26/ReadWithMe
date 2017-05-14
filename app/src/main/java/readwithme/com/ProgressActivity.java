package readwithme.com;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;

public class ProgressActivity extends Activity {


    ListView lv1, lv2;
    ImageView img_avatar;
    TextView txt_name_progress, txt_age_progress, txt_grade_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        tools.setFullScreen(this);
        lv1 = (ListView) findViewById(R.id.lv_progress1);
        lv2 = (ListView) findViewById(R.id.lv_progress2);
        img_avatar = (ImageView) findViewById(R.id.img_avatar);
        txt_name_progress = (TextView) findViewById(R.id.txt_name_progress);
        txt_age_progress = (TextView) findViewById(R.id.txt_age_progress);
        txt_grade_progress = (TextView) findViewById(R.id.txt_grade_progress);
        txt_name_progress.setText(userInfo.name);
        txt_age_progress.setText(userInfo.age);
        txt_grade_progress.setText(userInfo.grade);

        FillList<progress_model> f = new FillList<progress_model>();
        f.FillSListview(getApplicationContext(), new Object[]{R.id.txt_set, R.id.txt_set_score}, new String[]{"Set", "Score"}, R.layout.row_progress, lv1, progress_model.class, url.url_Select, Request.Method.POST, new String[]{"Player_name", "Category_cn"}, new String[]{userInfo.name, "2"});
        f.FillSListview(getApplicationContext(), new Object[]{R.id.txt_set, R.id.txt_set_score}, new String[]{"Set", "Score"}, R.layout.row_progress, lv2, progress_model.class, url.url_Select, Request.Method.POST, new String[]{"Player_name", "Category_cn"}, new String[]{userInfo.name, "1"});
    }

    public void cmd_tab_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        if (i == 1) {
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
