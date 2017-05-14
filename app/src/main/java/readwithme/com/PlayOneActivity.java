package readwithme.com;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressCustom;

public class PlayOneActivity extends Activity implements RecognitionListener {

    ImageView img_teacher_play_1, img_bubble_play_1, img_lesson_consonant, img_lesson_1_play_consonant, img_lesson_1_play_check;
    Button cmd_yes_Play_lesson_1, cmd_back_Play_lesson1, cmd_next_Play_lesson1;

    int drawableIndex = 0;
    int[] drawableItems;
    RelativeLayout rel_bubble;
    RelativeLayout activity_play_one;

    List<String> drawableName;

    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "PlayOneActivity";
    ACProgressCustom progressDialog;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_one);
        tools.setFullScreen(this);
        activity_play_one = (RelativeLayout) findViewById(R.id.activity_play_one);
        img_teacher_play_1 = (ImageView) findViewById(R.id.img_teacher_play_1);
        img_bubble_play_1 = (ImageView) findViewById(R.id.img_bubble_play_1);
        img_lesson_consonant = (ImageView) findViewById(R.id.img_lesson_consonant);
        img_lesson_1_play_check = (ImageView) findViewById(R.id.img_lesson_1_play_check);
        rel_bubble = (RelativeLayout) findViewById(R.id.rel_bubble);
        drawableItems = new int[]{R.drawable.lesson_1_play_boat, R.drawable.lesson_1_play_crab, R.drawable.lesson_1_play_dolphin, R.drawable.lesson_1_play_fish, R.drawable.lesson_1_play_gold, R.drawable.lesson_1_play_hippo, R.drawable.lesson_1_play_jellyfish, R.drawable.lesson_1_play_key, R.drawable.lesson_1_play_lake, R.drawable.lesson_1_play_man, R.drawable.lesson_1_play_net, R.drawable.lesson_1_play_pen, R.drawable.lesson_1_play_quilt, R.drawable.lesson_1_play_reef, R.drawable.lesson_1_play_sea, R.drawable.lesson_1_play_turtle, R.drawable.lesson_1_play_vase, R.drawable.lesson_1_play_water, R.drawable.lesson_1_play_xylophone, R.drawable.lesson_1_play_yarn, R.drawable.lesson_1_play_zebra};
        drawableName = Arrays.asList("Keith", "King", "cake", "Kik", "problem", "prom", "problems", "Club", "boat", "Bolt", "plate", "plague", "Blake", "fan", "pan", "pal", "panda", "Phantom", "Lake", "click", "boats", "vault", "vote", "boat", "Bolt", "vault", "volt", "ball", "parking", "Belkin", "barking", "falcon", "belcan", "Club", "cloud", "collab", "globe", "crab", "crab", "trap", "whatup", "Club", "rap", "dolphin", "tarpon", "Philippine", "propane", "Butterbean", "ballpeen", "LARPing", "Philippine", "dolphin", "Philippines", "fish", "Phish", "face", "gold", "Google", "bold", "bored", "board", "Google", "call", "goal", "gold", "people", "Capo", "cable", "hippo", "PPL", "people", "PPL", "cable", "Capo", "Apple", "jellyfish", "jellyfish", "bettafish", "JJFish", "JerryFish", "key", "p", "t", "tea", "G", "Ki", "p", "Lake", "late", "Blake", "man", "mom", "Mann", "mine", "men", "map", "met", "net", "Matt", "n", "Ben", "Penn", "pen", "in", "Grill", "Grille", "Grimm", "gril", "green", "rape", "rave", "Vape", "array", "fave", "safe", "leaf", "rape", "play", "free", "C", "sea", "see", "Burton", "turtle", "battle", "birthday", "Bethel", "beef", "faith", "fave", "Bape", "Bass", "water", "weather", "Lotto", "Lottery", "Slaughter", "pineapple", "xylophone", "playphone", "Tylerpoem", "playpoem", "yarn", "yard", "yarde", "learn", "yar", "zibra", "zebra", "Libra", "zebras", "Libras");


        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });
        tools.speakNow(getApplicationContext(),t1, "");
        img_lesson_1_play_consonant = (ImageView) findViewById(R.id.img_lesson_1_play_consonant);
        cmd_back_Play_lesson1 = (Button) findViewById(R.id.cmd_back_Play_lesson1);
        cmd_next_Play_lesson1 = (Button) findViewById(R.id.cmd_next_Play_lesson1);

        cmd_yes_Play_lesson_1 = (Button) findViewById(R.id.cmd_yes_Play_lesson_1);
        //  cmd_no_Play_lesson_1 = (Button) findViewById(R.id.cmd_no_Play_lesson_1);

        progressDialog = new ACProgressCustom.Builder(this).useImages(R.drawable.p1, R.drawable.p2, R.drawable.p3).build();


        progressBar = (ProgressBar) findViewById(R.id.progressBar_lesson_1_play);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton__lesson_1_play);

        if (userInfo.teacher == 1) {
            img_teacher_play_1.setImageResource(R.drawable.mr_favian);
        } else {
            img_teacher_play_1.setImageResource(R.drawable.ms_sophia);
        }



        //   cmd_no_Play_lesson_1.setVisibility(View.INVISIBLE);
        cmd_yes_Play_lesson_1.setVisibility(View.INVISIBLE);
        img_lesson_consonant.setVisibility(View.INVISIBLE);

        img_lesson_1_play_consonant.setVisibility(View.INVISIBLE);
        cmd_back_Play_lesson1.setVisibility(View.INVISIBLE);
        cmd_next_Play_lesson1.setVisibility(View.INVISIBLE);
        img_lesson_1_play_check.setVisibility(View.INVISIBLE);
        toggleButton.setVisibility(View.INVISIBLE);


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

        startTimer();


    }



    public void cmd_home_Clicked(View view) {
        startActivity(new Intent(this, PauseActivity.class));
        overridePendingTransition(0, 0);
      //  finish();
    }


    public void cmd_play_1_Clicked(View view) {
        Button b = (Button) view;
        int i = Integer.valueOf(b.getTag().toString());
        if (i == 1) {

            img_lesson_1_play_consonant.setImageResource(drawableItems[drawableIndex]);
            img_lesson_1_play_consonant.setVisibility(View.VISIBLE);
            // cmd_back_Play_lesson1.setVisibility(View.VISIBLE);
            // cmd_next_Play_lesson1.setVisibility(View.VISIBLE);
            toggleButton.setVisibility(View.VISIBLE);

            rel_bubble.setVisibility(View.INVISIBLE);
            img_bubble_play_1.setVisibility(View.INVISIBLE);
            cmd_yes_Play_lesson_1.setVisibility(View.INVISIBLE);
            img_lesson_consonant.setVisibility(View.INVISIBLE);


        } else if (i == 3) {

            if (drawableIndex >= 1) {
                drawableIndex -= 1;
                img_lesson_1_play_consonant.setImageResource(drawableItems[drawableIndex]);
            }
        } else if (i == 4) {
            if (drawableIndex <= drawableItems.length - 1) {

                img_lesson_1_play_consonant.setImageResource(drawableItems[drawableIndex]);
                drawableIndex += 1;
            }
        }
    }

    private void startTimer() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                img_bubble_play_1.setImageResource(R.drawable.lesson_1_play_2nd_bubble);
                img_lesson_consonant.setVisibility(View.VISIBLE);
                cmd_yes_Play_lesson_1.setVisibility(View.VISIBLE);
            }
        };
        new Handler().postDelayed(runnable, 3000);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onStart() {
        super.onStart();

        startTimer();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        startActivity(new Intent(getApplicationContext(), LesssonSelectActivity.class));
        overridePendingTransition(0, 0);
        finish();
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
        // returnedText = errorMessage;
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
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for (String result : matches) {
            //   speechResultsArray.add(result);
            if (drawableIndex >= drawableItems.length - 1) {
               // Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), FinishActivity.class));
                overridePendingTransition(0, 0);
                finish();
            } else {
                try {
                    Thread.sleep(200);
                    if (drawableName.contains(result)) {
                        variables.playerScore++;

                        img_lesson_1_play_check.setImageResource(R.drawable.check);
                        img_lesson_1_play_check.setVisibility(activity_play_one.getRootView().VISIBLE);
                        progressDialog.dismiss();
                        setDrawable();
                        break;
                    } else {
                        img_lesson_1_play_check.setImageResource(R.drawable.wrong);
                        img_lesson_1_play_check.setVisibility(activity_play_one.getRootView().VISIBLE);
                        progressDialog.dismiss();
                        setDrawable();
                        break;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }


        Log.d("result", matches.toString());
    }

    void setDrawable() {
        if (drawableIndex <= drawableItems.length - 1) {
            drawableIndex += 1;
            img_lesson_1_play_consonant.setImageResource(drawableItems[drawableIndex]);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    img_lesson_1_play_check.setVisibility(activity_play_one.getRootView().INVISIBLE);

                }
            };
            //  new Thread(runnable).start();
            new Handler().postDelayed(runnable, 500);
        }

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
