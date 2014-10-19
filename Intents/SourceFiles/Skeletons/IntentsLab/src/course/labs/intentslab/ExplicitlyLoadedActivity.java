package course.labs.intentslab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ExplicitlyLoadedActivity extends Activity {

	static private final String TAG = "Lab-Intents";

	private EditText mEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.explicitly_loaded_activity);

		// Get a reference to the EditText field
		mEditText = (EditText) findViewById(R.id.editText);

		// Declare and setup "Enter" button
		Button enterButton = (Button) findViewById(R.id.enter_button);
		enterButton.setOnClickListener(new OnClickListener() {

			// Call enterClicked() when pressed

			@Override
			public void onClick(View v) {

				enterClicked();
			
			}
		});

	}

	// Sets result to send back to calling Activity and finishes
	
	private void enterClicked() {

		Log.i(TAG,"Entered enterClicked()");

        String thing;

		// TODO - Save user provided input from the EditText field
        thing = mEditText.getText().toString();

		// TODO - Create a new intent and save the input from the EditText field as an extra
		Intent sendIntent = new Intent(ExplicitlyLoadedActivity.this, ActivityLoaderActivity.class);
        sendIntent.putExtra("stuff", thing);

		// TODO - Set Activity's result with result code RESULT_OK
        setResult(RESULT_OK,sendIntent);
		
		// TODO - Finish the Activity
        //startActivity(sendIntent);
        finish();

	}
}
