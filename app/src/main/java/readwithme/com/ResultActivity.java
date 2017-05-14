package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends Activity {

    // LinearLayout star_Layout;
    // ImageView img_star;
    String TAG = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tools.setFullScreen(this);
        // star_Layout = (LinearLayout) findViewById(R.id.star_layout);
        // img_star = (ImageView) findViewById(R.id.img_star);
        int s = variables.playerScore;
        if (s <= 2) {
            s = 1;
        } else if (s <= 4) {
            s = 2;
        } else if (s == 5) {
            s = 5;
        }


        InsertData(userInfo.cn+"",userInfo.name,variables.PlayOptionIndex+"",variables.SetIndex,s+"",variables.RemarkIndex);

        LinearLayout ll = (LinearLayout) findViewById(R.id.star_layout);
        for (int i = 0; i < s; i++) {
            {
                ImageView ii = new ImageView(this);
                ii.setBackgroundResource(R.drawable.star_yellow);
                ll.addView(ii);
            }

        }
    }

    public void InsertData(final String PlayerCn, final String PlayerName, final String CategoryCn, final String Set, final String Score, final String Remark) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url.url_Insrt,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("ID1", PlayerCn);
                        params.put("ID2", PlayerName);
                        params.put("ID3", CategoryCn);
                        params.put("ID4", Set);
                        params.put("ID5", Score);
                        params.put("ID6", Remark);
                        return params;
                    }
                };
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

            }
        };
        new Thread(runnable).start();
    }

    public void cmd_result_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        switch (i) {

            case 1:

                if (variables.activityCameFrom.contains("LessonActivity")) {
                    startActivity(new Intent(getApplicationContext(), LessonActivity.class));
                } else if (variables.activityCameFrom.contains("g2A1Activity")) {
                    startActivity(new Intent(getApplicationContext(), g2A1Activity.class));
                } else if (variables.activityCameFrom.contains("g1A2Activity")) {
                    startActivity(new Intent(getApplicationContext(), g1A2Activity.class));
                } else if (variables.activityCameFrom.contains("g2A2Activity")) {
                    startActivity(new Intent(getApplicationContext(), g2A2Activity.class));
                } else if (variables.activityCameFrom.contains("g2A3Activity")) {
                    startActivity(new Intent(getApplicationContext(), g2A3Activity.class));
                } else if (variables.activityCameFrom.contains("g1l4_Activity")) {
                    startActivity(new Intent(getApplicationContext(), g1l4_Activity.class));
                } else if (variables.activityCameFrom.contains("g1l5_Activity")) {
                    startActivity(new Intent(getApplicationContext(), g1l5_Activity.class));
                } else if (variables.activityCameFrom.contains("LessonActivity")) {
                    startActivity(new Intent(getApplicationContext(), LessonActivity.class));
                } else if (variables.activityCameFrom.contains("g2A1Activity")) {
                    startActivity(new Intent(getApplicationContext(), g2A1Activity.class));
                } else if (variables.activityCameFrom.contains("g1A2Activity ")) {
                    startActivity(new Intent(getApplicationContext(), g1A2Activity.class));
                } else if (variables.activityCameFrom.contains("g2A2Activity")) {
                    startActivity(new Intent(getApplicationContext(), g2A2Activity.class));
                } else if (variables.activityCameFrom.contains("g2A3Activity")) {
                    startActivity(new Intent(getApplicationContext(), g2A3Activity.class));
                } else if (variables.activityCameFrom.contains("g1l4_Activity")) {
                    startActivity(new Intent(getApplicationContext(), g1l4_Activity.class));
                } else if (variables.activityCameFrom.contains("PlayThreeActivity")) {
                    startActivity(new Intent(getApplicationContext(), PlayThreeActivity.class));
                } else if (variables.activityCameFrom.contains("Lesson_3_Activity_E ")) {
                    startActivity(new Intent(getApplicationContext(), Lesson_3_Activity_E.class));
                } else if (variables.activityCameFrom.contains("g1l3_I_Activity ")) {
                    startActivity(new Intent(getApplicationContext(), g1l3_I_Activity.class));
                } else if (variables.activityCameFrom.contains("g1l3_O_Activity ")) {
                    startActivity(new Intent(getApplicationContext(), g1l3_O_Activity.class));
                } else if (variables.activityCameFrom.contains("g1l3_U_Activity")) {
                    startActivity(new Intent(getApplicationContext(), g1l3_U_Activity.class));
                }
                variables.playerScore = 0;
                variables.currentLessonIndex = 0;
                overridePendingTransition(0, 0);
                this.finish();
                break;
            case 2:
                variables.playerScore = 0;
                variables.currentLessonIndex = 0;

                startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                overridePendingTransition(0, 0);
                this.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
