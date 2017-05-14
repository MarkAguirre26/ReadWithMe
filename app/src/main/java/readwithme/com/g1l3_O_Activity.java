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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressCustom;

public class g1l3_O_Activity extends Activity implements RecognitionListener {
    ImageView img_g1l3_I1, img_g1l3_I2, img_g1l3_I3, img_g1l3_I4, img_g1l3_I5, img_g1l3_I6, img_g1l3_I7, img_g1l3_I8, img_g1l3_I9, img_g1l3_I10;
    ACProgressCustom progressDialog;

    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "Lesson_3_Activity_E";
    int rawIndex = 1;
    String[] drawableWords1, drawableWords2, drawableWords3, drawableWords4, drawableWords5, drawableWords6, drawableWords7, drawableWords8, drawableWords9, drawableWords10;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g1l3__o_);
        tools.setFullScreen(this);
        img_g1l3_I1 = (ImageView) findViewById(R.id.img_g1l3_o1);
        img_g1l3_I2 = (ImageView) findViewById(R.id.img_g1l3_o2);
        img_g1l3_I3 = (ImageView) findViewById(R.id.img_g1l3_o3);
        img_g1l3_I4 = (ImageView) findViewById(R.id.img_g1l3_04);
        img_g1l3_I5 = (ImageView) findViewById(R.id.img_g1l3_o5);
        img_g1l3_I6 = (ImageView) findViewById(R.id.img_g1l3_06);
        img_g1l3_I7 = (ImageView) findViewById(R.id.img_g1l3_o7);
        img_g1l3_I8 = (ImageView) findViewById(R.id.img_g1l3_o8);
        img_g1l3_I9 = (ImageView) findViewById(R.id.img_g1l3_09);
        img_g1l3_I10 = (ImageView) findViewById(R.id.img_g1l3_010);

        progressBar = (ProgressBar) findViewById(R.id.progressBar_g1l3_O);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton_lesson_g1l3_O);




        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });
        tools.speakNow(getApplicationContext(),t1, "words with the short and long o sounds");

       // progressDialog = new ProgressDialog(this);
       // progressDialog.setTitle("Wait...");
        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();
        imgSetVisible(img_g1l3_I1);

        drawableWords1 = new String[]{"MO", "bow", "Bo", "low"};
        drawableWords2 = new String[]{"frog", "Prague", "fraud", "fruit", "Brad"};
        drawableWords3 = new String[]{"block", "black", "fly", "blah", "clock"};
        drawableWords4 = new String[]{"Ross", "Rose", "Ralphs", "Lowe's", "Ralph"};
        drawableWords5 = new String[]{"oven", "Alvin", "oval", "open", "often"};
        drawableWords6 = new String[]{"la", "lock", "block", "Loc"};
        drawableWords7 = new String[]{"Lowe's", "Moe's", "mon", "mode", "mobile"};
        drawableWords8 = new String[]{"cop", "top", "pop", "kop", "Cobb"};
        drawableWords9 = new String[]{"lollipop", "lollypop", "lollipops", "lollypop"};
        drawableWords10 = new String[]{"phone", "bone", "home", "Palm", "pause"};


        progressBar.setVisibility(View.INVISIBLE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,                "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    toggleButton.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                    speech.startListening(recognizerIntent);
                } else {
                    toggleButton.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.INVISIBLE);
                    speech.stopListening();
                }
            }
        });
    }


    void imgSetVisible(ImageView imageView) {
        img_g1l3_I1.setVisibility(View.INVISIBLE);
        img_g1l3_I2.setVisibility(View.INVISIBLE);
        img_g1l3_I3.setVisibility(View.INVISIBLE);
        img_g1l3_I4.setVisibility(View.INVISIBLE);
        img_g1l3_I5.setVisibility(View.INVISIBLE);
        img_g1l3_I6.setVisibility(View.INVISIBLE);
        img_g1l3_I7.setVisibility(View.INVISIBLE);
        img_g1l3_I8.setVisibility(View.INVISIBLE);
        img_g1l3_I9.setVisibility(View.INVISIBLE);
        img_g1l3_I10.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.VISIBLE);
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
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Lesson_3_Activity_menu.class));
        overridePendingTransition(0, 0);
        finish();
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
        toggleButton.setChecked(false);
        progressDialog.show();
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        // returnedText.setText(errorMessage);
        toggleButton.setChecked(false);
        progressDialog.dismiss();
        toggleButton.setVisibility(View.VISIBLE);
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
        progressDialog.dismiss();
        toggleButton.setVisibility(View.VISIBLE);
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for (String result : matches) {
            try {
                Thread.sleep(200);
                if (rawIndex == 1) {
                    imgSetVisible(img_g1l3_I1);
                    checkAnswer(drawableWords1, result);
                    break;
                } else if (rawIndex == 2) {
                    imgSetVisible(img_g1l3_I2);
                    checkAnswer(drawableWords2, result);
                    break;

                } else if (rawIndex == 3) {
                    imgSetVisible(img_g1l3_I3);
                    checkAnswer(drawableWords3, result);
                    break;

                } else if (rawIndex == 4) {
                    imgSetVisible(img_g1l3_I4);
                    checkAnswer(drawableWords4, result);
                    break;

                } else if (rawIndex == 5) {
                    imgSetVisible(img_g1l3_I5);
                    checkAnswer(drawableWords7, result);
                    break;

                } else if (rawIndex == 6) {
                    imgSetVisible(img_g1l3_I6);
                    checkAnswer(drawableWords8, result);
                    break;

                } else if (rawIndex == 7) {
                    imgSetVisible(img_g1l3_I7);
                    checkAnswer(drawableWords5, result);
                    break;

                } else if (rawIndex == 8) {
                    imgSetVisible(img_g1l3_I8);
                    checkAnswer(drawableWords6, result);
                    break;

                } else if (rawIndex == 9) {
                    imgSetVisible(img_g1l3_I9);
                    checkAnswer(drawableWords9, result);
                    break;

                }  else if (rawIndex == 10) {
                  //  imgSetVisible(img_g1l3_I10);
                    checkAnswer(drawableWords10, result);
                    Thread.sleep(500);
                    startActivity(new Intent(this, Lesson_3_Activity_menu.class));
                    overridePendingTransition(0, 0);
                    finish();
                    break;

                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
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
}
