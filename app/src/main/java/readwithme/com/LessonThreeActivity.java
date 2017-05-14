package readwithme.com;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LessonThreeActivity extends Activity implements RecognitionListener {

    ImageView lesson_three_arrow, lesson_three_ant, lesson_three_mad, lesson_three_van, img_bad, lesson_three_apple;
    //  int[] drawableItems_highligh;
    List<String> drawableNames;

    //private String returnedText;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "LessonThreeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_three);
        tools.setFullScreen(this);
        lesson_three_arrow = (ImageView) findViewById(R.id.lesson_three_arrow);
        lesson_three_ant = (ImageView) findViewById(R.id.lesson_three_ant);
        lesson_three_mad = (ImageView) findViewById(R.id.lesson_three_mad);
        lesson_three_van = (ImageView) findViewById(R.id.lesson_three_van);
        img_bad = (ImageView) findViewById(R.id.img_bad);
        lesson_three_apple = (ImageView) findViewById(R.id.lesson_three_apple);

        // drawableItems = new int[]{R.drawable.lesson_three_arrow, R.drawable.lesson_three_ant, R.drawable.lesson_three_mad, R.drawable.lesson_three_van, R.drawable.lesson_three_bad, R.drawable.lesson_three_apple};
        //  drawableItems_highligh = new int[]{R.drawable.lesson_three_arrow_selected, R.drawable.lesson_three_ant_selected, R.drawable.lesson_three_mad_selected, R.drawable.lesson_three_van_selected, R.drawable.lesson_three_bad_selected, R.drawable.lesson_three_apple_selected};

        drawableNames = Arrays.asList("arrow", "Arrow", "ant", "on", "n", "an", "mad", "mod", "mud", "van", "bad", "Apple","Auto","Arrow","Otto","idle","Aero","Auto","Arrow","Otto","Lotto","Oto");


        progressBar = (ProgressBar) findViewById(R.id.progressBarThree);
        toggleButton = (ToggleButton) findViewById(R.id.cmd_speakLessonThree);

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

    void gotback() {
       userInfo.teacher = 0;
        startActivity(new Intent(getApplicationContext(), LesssonSelectActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        gotback();
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
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        //  Toast.makeText(getApplicationContext(),"Network Connection",Toast.LENGTH_SHORT).show();
        toggleButton.setChecked(false);
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

        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        for (String result : matches) {
            //  text += result + "\n";

            try {
                if (variables.playerScore == 5) {
                    variables.playerScore += 1;
                    variables.activityCameFrom = "LessonThreeActivity";
                    startActivity(new Intent(getApplicationContext(),ResultActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                } else {
                    Thread.sleep(200);
                    if (drawableNames.contains(result)) {
                        variables.playerScore += 1;
                        if (result.contains("Arrow") || result.contains("Otto") || result.contains("Aero") || result.contains("Lotto")) {
                            variables.playerScore +=1;
                            lesson_three_arrow.setImageResource(R.drawable.lesson_three_arrow_selected);
                            break;
                        } else if (result.contains("ant") || result.contains("n") || result.contains("an") || result.contains("on")) {
                            variables.playerScore +=1;
                            lesson_three_ant.setImageResource(R.drawable.lesson_three_ant_selected);
                            break;
                        } else if (result.contains("mad") || result.contains("mud") || result.contains("mod")) {
                            variables.playerScore +=1;
                            lesson_three_mad.setImageResource(R.drawable.lesson_three_mad_selected);
                            break;
                        } else if (result.contains("van")) {
                            variables.playerScore +=1;
                            lesson_three_van.setImageResource(R.drawable.lesson_three_van_selected);
                            break;
                        } else if (result.contains("bad")) {
                            variables.playerScore +=1;
                            img_bad.setImageResource(R.drawable.lesson_three_bad_selected);
                            break;
                        } else if (result.contains("Apple")) {
                            variables.playerScore +=1;
                            lesson_three_apple.setImageResource(R.drawable.lesson_three_apple_selected);
                            break;
                        }
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        Log.d("Result",matches.toString());
        // returnedText.setText(text);
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

}
