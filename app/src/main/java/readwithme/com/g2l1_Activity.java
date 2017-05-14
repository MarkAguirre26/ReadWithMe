package readwithme.com;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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

import static readwithme.com.R.id.img_bubble_g1l5;

public class g2l1_Activity extends Activity implements RecognitionListener {

    ImageView img_teacher, img_subject,img_bubble_g1l5;
    int[] drawableItems;
    String[] drawableWords_1, drawableWords_2, drawableWords_3, drawableWords_4, drawableWords_5, drawableWords_6, drawableWords_7, drawableWords_8, drawableWords_9, drawableWords_10, drawableWords_11, drawableWords_12, drawableWords_13, drawableWords_14, drawableWords_15, drawableWords_16, drawableWords_17, drawableWords_18, drawableWords_19, drawableWords_20, drawableWords_21, drawableWords_22, drawableWords_23, drawableWords_24, drawableWords_25, drawableWords_26;

    Button cmd_next_g1l5;
    boolean isNext = true;

    private ToggleButton cmd_speak;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "g2l1_Activity";
    ACProgressCustom progressDialog;
    int rawIndex = 0;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g2l1_);
        tools.setFullScreen(this);

        img_bubble_g1l5 = (ImageView) findViewById(R.id.img_bubble_g1l5);
        cmd_next_g1l5 = (Button) findViewById(R.id.cmd_next_g2l1);
        img_teacher = (ImageView) findViewById(R.id.img_teacher_g2l1);
        img_subject = (ImageView) findViewById(R.id.img_subject_g2l1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_g2l1);
        cmd_speak = (ToggleButton) findViewById(R.id.cmd_speak_g2l1);

        img_subject.setVisibility(View.INVISIBLE);
        cmd_speak.setVisibility(View.INVISIBLE);


        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });
        tools.speakNow(getApplicationContext(),t1, "Each letter of the alphabets has a unique sound");

       // progressDialog = new ProgressDialog(this);
      //  progressDialog.setTitle("Please wait...");
        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();
        drawableItems = new int[]{R.drawable.g2l1_a, R.drawable.g2l1_b, R.drawable.g2l1_c, R.drawable.g2l1_d, R.drawable.g2l1_e, R.drawable.g2l1_f, R.drawable.g2l1_g, R.drawable.g2l1_h, R.drawable.g2l1_i, R.drawable.g2l1_j, R.drawable.g2l1_k, R.drawable.g2l1_l, R.drawable.g2l1_m, R.drawable.g2l1_n, R.drawable.g2l1_o, R.drawable.g2l1_p, R.drawable.g2l1_q, R.drawable.g2l1_r, R.drawable.g2l1_s, R.drawable.g2l1_t, R.drawable.g2l1_u, R.drawable.g2l1_v, R.drawable.g2l1_w, R.drawable.g2l1_x, R.drawable.g2l1_y, R.drawable.g2l1_z};
        drawableWords_1 = new String[] {"a","ant", "aunt", "amp", "and", "anthem"};
        drawableWords_2 = new String[] {"b","bus", "Bass", "Mass", "boss", "bath"};
        drawableWords_3 = new String[] {"c","cat", "Kat", "jet", "cap"};
        drawableWords_4 = new String[] {"d","day", "they", "the", "de", "gay"};
        drawableWords_5 = new String[] {"e","n", "in", "m", "and"};
        drawableWords_6 = new String[] {"f","fine", "Vine", "find", "fight", "Pine"};
        drawableWords_7 = new String[] {"g","game", "games", "gain", "gay", "gang"};
        drawableWords_8 = new String[] {"h","hot", "hat", "hi", "Hut", "ha"};
        drawableWords_9 = new String[] {"i","inch", "ink", "Inc", "pink", "bench"};
        drawableWords_10 = new String[]{"j","dope", "Dell", "jokes", "joke", "don't"};
        drawableWords_11 = new String[]{"k","Pride", "price", "Skype", "crime", "tripe"};
        drawableWords_12 = new String[]{"l","result:love", "Lowe's", "Luv", "loved", "loud"};
        drawableWords_13 = new String[]{"m","man", "mine", "mom", "men", "Mann"};
        drawableWords_14 = new String[]{"n","map", "not", "math", "knot"};
        drawableWords_15 = new String[]{"o","oval", "oven", "ovale", "Alvin", "Louisville"};
        drawableWords_16 = new String[]{"p","k", "gay", "hey", "pay"};
        drawableWords_17 = new String[]{"q","Grill", "Gra", "trailer", "krill", "grills"};
        drawableWords_18 = new String[]{"r","brain", "rain", "train", "reign", "frame"};
        drawableWords_19 = new String[]{"s","sick", "thick", "6", "sic"};
        drawableWords_20 = new String[]{"t","sorry", "diet", "Thai", "toy"};
        drawableWords_21 = new String[]{"u","Honda", "under", "panda", "Thunder", "Hyundai"};
        drawableWords_22 = new String[]{"v","vet", "death", "vets", "Vette", "jet"};
        drawableWords_23 = new String[]{"w","wait", "we", "weight", "way", "Wii"};
        drawableWords_24 = new String[]{"x","textRey", "x-ray", "textRay", "xray", "textGray"};
        drawableWords_25 = new String[]{"y","Yelp", "help", "Geo", "yep"};
        drawableWords_26 = new String[]{"z","Zone", "phone", "home", "XOne", "song"};

        if (userInfo.teacher == 1) {
            img_teacher.setImageResource(R.drawable.mr_favian);
        } else {
            img_teacher.setImageResource(R.drawable.ms_sophia);
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


    public void cmd_next_Clicked(View view) {
        if (isNext == true) {
            img_bubble_g1l5.setVisibility(View.GONE);
            cmd_next_g1l5.setVisibility(View.GONE);
            img_subject.setVisibility(View.VISIBLE);
            cmd_speak.setVisibility(View.VISIBLE);
            //imgSetVisible(img_highlight1_g1l5);
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
        progressDialog.dismiss();
        for (String result : matches) {
            try {
                Thread.sleep(200);
                if (rawIndex == 0) {
                    checkAnswer(drawableWords_1, result);

                    break;
                } else if (rawIndex == 1) {
                    checkAnswer(drawableWords_2, result);
                    break;

                } else if (rawIndex == 2) {
                    checkAnswer(drawableWords_3, result);
                    break;

                } else if (rawIndex == 3) {
                    checkAnswer(drawableWords_4, result);
                    break;

                } else if (rawIndex == 4) {
                    checkAnswer(drawableWords_5, result);
                    break;

                } else if (rawIndex == 5) {
                    checkAnswer(drawableWords_6, result);
                    break;

                } else if (rawIndex == 6) {
                    checkAnswer(drawableWords_7, result);
                    break;

                } else if (rawIndex == 7) {
                    checkAnswer(drawableWords_8, result);
                    break;

                } else if (rawIndex == 8) {
                    checkAnswer(drawableWords_9, result);
                    break;

                } else if (rawIndex == 9) {
                    checkAnswer(drawableWords_10, result);
                    break;

                } else if (rawIndex == 11) {
                    checkAnswer(drawableWords_12, result);
                    break;

                } else if (rawIndex == 13) {
                    checkAnswer(drawableWords_14, result);
                    break;

                } else if (rawIndex == 14) {
                    checkAnswer(drawableWords_15, result);
                    break;

                } else if (rawIndex == 16) {
                    checkAnswer(drawableWords_17, result);
                    break;

                } else if (rawIndex == 17) {
                    checkAnswer(drawableWords_18, result);
                    break;

                } else if (rawIndex == 19) {
                    checkAnswer(drawableWords_20, result);
                    break;

                } else if (rawIndex == 21) {
                    checkAnswer(drawableWords_22, result);
                    break;

                } else if (rawIndex == 23) {
                    checkAnswer(drawableWords_24, result);
                    break;

                } else if (rawIndex == 24) {
                    checkAnswer(drawableWords_25, result);
                    break;

                } else if (rawIndex == 25) {
                    checkAnswer(drawableWords_4, result);
                    Thread.sleep(500);
                    startActivity(new Intent(this, FinishActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                    break;

                }
               // img_subject.setImageResource(drawableItems[rawIndex]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

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
        rawIndex++;
        img_subject.setImageResource(drawableItems[rawIndex]);
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


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), LesssonSelectActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }
}
