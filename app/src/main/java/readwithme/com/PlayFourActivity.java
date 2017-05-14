package readwithme.com;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressCustom;

public class PlayFourActivity extends Activity implements RecognitionListener {

    ImageView img_teacher_g1l4, img_subject_g1l4, img_bubble_g1l4, img_highlight1_g1l4, img_highlight2_g1l4, img_highlight3_g1l4;
    int[] drawableItems;
    String[] drawableName_A1, drawableName_A2, drawableName_A3;
    String[] drawableName_E1, drawableName_E2, drawableName_E3;
    String[] drawableName_I1, drawableName_I2, drawableName_I3;
    String[] drawableName_O1, drawableName_O2, drawableName_O3;
    String[] drawableName_U1, drawableName_U2, drawableName_U3;
    Button cmd_next_g1l4;
    boolean isNext = true;

    private ToggleButton cmd_speak;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "PlayFourActivity";
    ACProgressCustom progressDialog;
    int rawIndex = 1;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_four);
        tools.setFullScreen(this);
        drawableItems = new int[]{R.drawable.a_g1l4, R.drawable.e_g1l4, R.drawable.i_g1l4, R.drawable.o_g1l4, R.drawable.u_g1l4};
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });

        tools.speakNow(getApplicationContext(),t1, "Here are some words that have an initial vowel sound. Read them with me");
        cmd_next_g1l4 = (Button) findViewById(R.id.cmd_next_g1l4);
        img_teacher_g1l4 = (ImageView) findViewById(R.id.img_teacher_g1l4);
        img_subject_g1l4 = (ImageView) findViewById(R.id.img_subject_g1l4);
        img_bubble_g1l4 = (ImageView) findViewById(R.id.img_bubble_g1l4);
        img_highlight1_g1l4 = (ImageView) findViewById(R.id.img_highlight1_g1l4);
        img_highlight2_g1l4 = (ImageView) findViewById(R.id.img_highlight2_g1l4);
        img_highlight3_g1l4 = (ImageView) findViewById(R.id.img_highlight3_g1l4);

        progressBar = (ProgressBar) findViewById(R.id.progressBar__g1l4);
        cmd_speak = (ToggleButton) findViewById(R.id.cmd_speak_g1l4);

        img_subject_g1l4.setVisibility(View.INVISIBLE);
        img_highlight1_g1l4.setVisibility(View.INVISIBLE);
        img_highlight2_g1l4.setVisibility(View.INVISIBLE);
        img_highlight3_g1l4.setVisibility(View.INVISIBLE);
        cmd_speak.setVisibility(View.INVISIBLE);


       // progressDialog = new ProgressDialog(this);
       // progressDialog.setTitle("Please wait...");
        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();

        drawableName_A1 = new String[]{"Al", "l", "all", "I'll", "ant", "amp", "aunt", "and"};
        drawableName_A2 = new String[]{"encore", "Oncor", "oncore", "oncore", "oncar", "encore", "Oncor", "hardcore", "Concord", "parkour"};
        drawableName_A3 = new String[]{"ambulance", "imbalance", "amberlands", "Cumberland", "unbalanced", "mbulance", "ambiance", "ambience", "ambulant", "ambient"};

        drawableName_E1 = new String[]{"Acton", "Exxon", "Akron", "actin", "Akon", "eggplant", "egghunt", "Magcon", "Acton", "eggbun"};
        drawableName_E2 = new String[]{"Eskimo", "eskimeaux", "Eskimos", "Eskimo", "eskimeaux", "Eskimos"};
        drawableName_E3 = new String[]{"palpable", "Alamo", "Hannibal", "edible", "fallible"};

        drawableName_I1 = new String[]{"igloo", "eggloo", "egloo", "iglu", "eagle", "igloo", "eggloo", "eagle", "aglow", "egloo"};
        drawableName_I2 = new String[]{"iguana", "eguana", "iguanas"};
        drawableName_I3 = new String[]{"inchworm", "inchworms", "inchworm", "inchworms"};

        drawableName_O1 = new String[]{"office"};
        drawableName_O2 = new String[]{"arcade", "arcades", "orchid", "ArcheAge", "RK", "arcade", "parking", "arcane", "Orkin", "orchid"};
        drawableName_O3 = new String[]{"callMoe", "mobile", "callPaul", "uncle", "callCo", "Apple", "opal", "boat", "oboe"};

        drawableName_U1 = new String[]{"umbrella"};
        drawableName_U2 = new String[]{"unicycle", "unicycler", "unicycles", "unicycling", "unifiedcourt"};
        drawableName_U3 = new String[]{"uniform", "uniforms", "uniformed", "uniforme", "peonyFarm"};


        if (userInfo.teacher == 1) {
            img_teacher_g1l4.setImageResource(R.drawable.mr_favian);
        } else {
            img_teacher_g1l4.setImageResource(R.drawable.ms_sophia);
        }


        ////////////////////////////////////////////
        progressBar.setVisibility(View.INVISIBLE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        cmd_speak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cmd_speak.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                    speech.startListening(recognizerIntent);
                } else {
                    cmd_speak.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.INVISIBLE);
                    speech.stopListening();
                }
            }
        });

    }


    public void cmd_home_Clicked(View view) {
        startActivity(new Intent(this, PauseActivity.class));
        overridePendingTransition(0, 0);
      //  finish();
    }

   /* private void startTimer() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                img_bubble_g1_l2.setImageResource(drawableItems[4]);

            }
        };
        new Handler().postDelayed(runnable, 5000);
    }*/

    public void cmd_next_Clicked(View view) {
        if (isNext == true) {
            cmd_next_g1l4.setVisibility(View.GONE);
            img_bubble_g1l4.setVisibility(View.INVISIBLE);
            img_subject_g1l4.setVisibility(View.VISIBLE);
            cmd_speak.setVisibility(View.VISIBLE);
            imgSetVisible(img_highlight1_g1l4);
        } else {

        }


    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (speech != null) {
            speech.destroy();
            Log.i(LOG_TAG, "destroy");
        }

    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
        progressBar.setIndeterminate(false);
        progressBar.setMax(10);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech");
        progressBar.setIndeterminate(true);
        cmd_speak.setChecked(false);
        progressDialog.show();
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        // Toast.makeText(getApplicationContext(),"Network Connection",Toast.LENGTH_SHORT).show();
        cmd_speak.setVisibility(View.VISIBLE);
        cmd_speak.setChecked(false);
        progressDialog.dismiss();
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        Log.i(LOG_TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.i(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(LOG_TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        cmd_speak.setVisibility(View.VISIBLE);
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        for (String result : matches) {
            try {
                Thread.sleep(200);
                if (rawIndex == 1) {
                    imgSetVisible(img_highlight2_g1l4);
                    checkAnswer(drawableName_A1, result);
                    break;
                } else if (rawIndex == 2) {
                    imgSetVisible(img_highlight3_g1l4);
                    checkAnswer(drawableName_A2, result);
                    break;

                } else if (rawIndex == 3) {
                    imgSetVisible(img_highlight1_g1l4);
                    checkAnswer(drawableName_A3, result);
                    break;

                } else if (rawIndex == 4) {
                    imgSetVisible(img_highlight2_g1l4);
                    checkAnswer(drawableName_E1, result);
                    break;

                } else if (rawIndex == 5) {
                    imgSetVisible(img_highlight3_g1l4);
                    checkAnswer(drawableName_E2, result);
                    break;

                } else if (rawIndex == 6) {
                    imgSetVisible(img_highlight1_g1l4);
                    checkAnswer(drawableName_E3, result);
                    break;

                } else if (rawIndex == 7) {
                    imgSetVisible(img_highlight2_g1l4);
                    checkAnswer(drawableName_I1, result);
                    break;

                } else if (rawIndex == 8) {
                    imgSetVisible(img_highlight3_g1l4);
                    checkAnswer(drawableName_I2, result);
                    break;

                } else if (rawIndex == 9) {
                    imgSetVisible(img_highlight1_g1l4);
                    checkAnswer(drawableName_I3, result);
                    break;

                } else if (rawIndex == 10) {
                    imgSetVisible(img_highlight2_g1l4);
                    checkAnswer(drawableName_O1, result);
                    break;

                } else if (rawIndex == 11) {
                    imgSetVisible(img_highlight3_g1l4);
                    checkAnswer(drawableName_O2, result);
                    break;

                } else if (rawIndex == 12) {
                    imgSetVisible(img_highlight1_g1l4);
                    checkAnswer(drawableName_O3, result);
                    break;

                } else if (rawIndex == 13) {
                    imgSetVisible(img_highlight2_g1l4);
                    checkAnswer(drawableName_U1, result);
                    break;

                } else if (rawIndex == 14) {
                    imgSetVisible(img_highlight3_g1l4);
                    checkAnswer(drawableName_U2, result);
                    break;

                } else if (rawIndex == 15) {
                    checkAnswer(drawableName_U3, result);
                    Thread.sleep(500);
                    startActivity(new Intent(this, FinishActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        progressDialog.dismiss();
        Log.d("result", matches.toString());

    }

    private void checkAnswer(String[] s, String result) {
        List<String> list = Arrays.asList(s);
        if (list.contains(result)) {
            variables.playerScore +=1;
            tools.showImage(this, R.drawable.check);
        } else {
            tools.showImage(this, R.drawable.wrong);
        }
        if(rawIndex == 3){
            img_subject_g1l4.setImageResource(drawableItems[1]);
        }else   if(rawIndex == 6){
            img_subject_g1l4.setImageResource(drawableItems[2]);
        }else   if(rawIndex == 9){
            img_subject_g1l4.setImageResource(drawableItems[3]);
        }else   if(rawIndex == 12){
            img_subject_g1l4.setImageResource(drawableItems[4]);
        }
        rawIndex++;
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }

    void imgSetVisible(ImageView imageView) {
        img_highlight1_g1l4.setVisibility(View.INVISIBLE);
        img_highlight2_g1l4.setVisibility(View.INVISIBLE);
        img_highlight3_g1l4.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), LesssonSelectActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }


}
