package com.hook38.sporttimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
public class TextInputActivity extends Activity implements OnClickListener {
	EditText textInput;
	String originalText = "";
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_input);
        textInput = (EditText)findViewById(R.id.input_editText);
        
        Intent sender=getIntent();
        try{
        	//set the time input fields for edit
        	originalText = sender.getExtras().getString("text");
        	textInput.setText(originalText);
        	
        }catch(NullPointerException e){
        	
        }
        
        Button button = (Button)findViewById(R.id.text_input_button);
        button.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case(R.id.text_input_button):
			Intent intent=new Intent();
	    	intent.putExtra("text", textInput.getText().toString());
	    	intent.putExtra("originaltext", originalText);
	    	setResult(RESULT_OK, intent);
	    	finish();
		}
		
	}
}
