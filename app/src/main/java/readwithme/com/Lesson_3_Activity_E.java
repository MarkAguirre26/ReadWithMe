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
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressCustom;

public class Lesson_3_Activity_E extends Activity implements RecognitionListener {
    ImageView g1l31_highlight, g1l32_highlight, g1l33_highlight, g1l34_highlight, g1l35_highlight;
    ACProgressCustom progressDialog;
    private String returnedText;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "Lesson_3_Activity_E";
    int rawIndex = 1;
    String[] drawableWords1, drawableWords2, drawableWords3, drawableWords4, drawableWords5;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_3___e);
        tools.setFullScreen(this);

        g1l31_highlight = (ImageView) findViewById(R.id.g1l31_highlight);
        g1l32_highlight = (ImageView) findViewById(R.id.g1l32_highlight);
        g1l33_highlight = (ImageView) findViewById(R.id.g1l33_highlight);
        g1l34_highlight = (ImageView) findViewById(R.id.g1l34_highlight);
        g1l35_highlight = (ImageView) findViewById(R.id.g1l35_highlight);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_lesson_3E);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton_lesson_3E_play);


        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });
        tools.speakNow(getApplicationContext(),t1, "");


       // progressDialog = new ProgressDialog(this);
       // progressDialog.setTitle("Wait...");
        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();
        imgSetVisible(g1l31_highlight);

        drawableWords1 = new String[]{"dress","press","tres","Chris","rest"};
        drawableWords2 = new String[]{"men","Bell","Ben","bed","Belle"};
        drawableWords3 = new String[]{"shell","shed","share","Cher","head"};
        drawableWords4 = new String[]{"jet","death","debt","Jett","Jets"};
        drawableWords5 = new String[]{"Ben","fan","been","pan","ban"};


        progressBar.setVisibility(View.INVISIBLE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                "en");
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
        g1l31_highlight.setVisibility(View.INVISIBLE);
        g1l32_highlight.setVisibility(View.INVISIBLE);
        g1l33_highlight.setVisibility(View.INVISIBLE);
        g1l34_highlight.setVisibility(View.INVISIBLE);
        g1l35_highlight.setVisibility(View.INVISIBLE);
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
        toggleButton.setVisibility(View.VISIBLE);
        Log.i(LOG_TAG, "onResults");
        progressDialog.dismiss();
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for (String result : matches) {
            try {
                Thread.sleep(200);
                if (rawIndex == 1) {
                    imgSetVisible(g1l32_highlight);
                    checkAnswer(drawableWords1, result);
                    break;
                } else if (rawIndex == 2) {
                    imgSetVisible(g1l33_highlight);
                    checkAnswer(drawableWords2, result);
                    break;

                } else if (rawIndex == 3) {
                    imgSetVisible(g1l34_highlight);
                    checkAnswer(drawableWords3, result);
                    break;

                } else if (rawIndex == 4) {
                    imgSetVisible(g1l35_highlight);
                    checkAnswer(drawableWords4, result);
                    break;

                }else if (rawIndex == 5) {
                  //  imgSetVisible(g1l35_highlight);
                    checkAnswer(drawableWords5, result);
                    Thread.sleep(500);
                    startActivity(new Intent(this, Lesson_3_Activity_E_Long.class));
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
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,Lesson_3_Activity_menu.class));
        overridePendingTransition(0,0);
        finish();
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
