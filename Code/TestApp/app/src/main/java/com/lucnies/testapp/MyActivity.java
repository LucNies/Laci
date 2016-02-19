package com.lucnies.testapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import java.util.Locale;


public class MyActivity extends Activity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private Button toSpeechButton;
    private int MY_DATA_CHECK_CODE = 0;
    private TextToSpeech speaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        uiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        uiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        setupButtons();
        speaker = new TextToSpeech(getApplicationContext(), this);

    }

    private void setupButtons(){
        toSpeechButton = (Button)findViewById(R.id.speechButton);
        toSpeechButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //assuming only one button
        speaker.speak("Hello World", TextToSpeech.QUEUE_FLUSH, null);
//        Intent startSpeaker = new Intent(MyActivity.this, Speaker.class);
//        startActivity(startSpeaker);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode == MY_DATA_CHECK_CODE){
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                speaker = new TextToSpeech(getApplicationContext(), this);
                speaker.speak("Hello World", TextToSpeech.QUEUE_FLUSH, null);
            }
            else{
                Intent installTTSIntent =  new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }

    }

    @Override
    public void onInit(int status) {
        speaker.setLanguage(Locale.ENGLISH);
        System.out.println("language set");
    }

}
