package com.hook38.sporttimer.controller;

import android.app.Activity;
import android.media.MediaPlayer;
import java.util.Locale;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

import com.hook38.sporttimer.R;

public class SoundController implements OnInitListener{
	private TextToSpeech mTts;
	private boolean useTTS = false;
	private MediaPlayer mp;
	private Activity activity;
	
	public SoundController (Activity activity) {
		this.activity = activity;
	}
	
	private Activity getActivity() {
		return this.activity;
	}
	
	private TextToSpeech getTextToSpeech() {
		if(mTts == null) {
			mTts = new TextToSpeech(this.getActivity(), this);
		}
		return mTts;
	}
	
	/**
	 * Set whether the system use Text to speech function or not
	 * @param tts whether TTS was used or not
	 */
	public void setTTS(boolean tts) {
		this.useTTS = tts;
	}
	
	public void intervalSound() {
		this.beepSound();
	}
	
	public void finishSound() {
		if(useTTS) {
			this.speakFinalVoice();
		} else {
			this.beepFinalSound();
		}
	}
	
	private void beepSound(){
		int resid = R.raw.beep;
   	 	if(mp!=null) {
   	 		mp.release();
   	 	}
   	 	mp = MediaPlayer.create(getActivity(), resid);
   	 	mp.start();  	 
	}
	
	private void beepFinalSound(){
		int resid = R.raw.beep01;
   	 	if(mp!=null) {
   	 		mp.release();
   	 	}
   	 	mp = MediaPlayer.create(getActivity(), resid);
   	 	mp.start();  	 
	}
	
	private void speakFinalVoice() {
		this.getTextToSpeech().speak(
				this.getActivity().getString(R.string.routine_finish_speech), 
				TextToSpeech.QUEUE_FLUSH, 
				null);
	}
	
	public void close() {
		//turn off media player
		if(mp!=null) {
   	 		mp.release();
   	 	}
		//Turn off text to speech
		if(mTts != null) {
			mTts.stop();
			mTts.shutdown();
		}		
	}

	public void onInit(int status) {
		// TODO Auto-generated method stub
		// status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            // Note that a language may not be available, and the result will indicate this.
            int result = mTts.setLanguage(Locale.US);
            // Try this someday for some interesting results.
            // int result mTts.setLanguage(Locale.FRANCE);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED) {
               // Lanuage data is missing or the language is not supported.
            } else {
                speakFinalVoice();
            }
        } else {
            // Initialization failed.
        }
	}
}
