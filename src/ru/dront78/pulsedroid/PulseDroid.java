package ru.dront78.pulsedroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PulseDroid extends Activity {
	/** Called when the activity is first created. */

	boolean playState = false;
	PulseSoundThread playThread = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// here is onButtonClick handler
		final Button playButton = (Button) findViewById(R.id.ButtonPlay);
		playButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (false == playState) {
					playState = true;
					playButton.setText("Stop");
					if (null != playThread) {
						playThread.Terminate();
						playThread = null;
					}
					final EditText server = (EditText) findViewById(R.id.EditTextServer);
					final EditText port = (EditText) findViewById(R.id.EditTextPort);
					playThread = new PulseSoundThread(server.getText()
							.toString(), port.getText().toString());
					new Thread(playThread).start();

				} else {
					playState = false;
					playButton.setText("Play!");
					if (null != playThread) {
						playThread.Terminate();
						playThread = null;
					}
				}
			}
		});

		findViewById(R.id.ButtonExit).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						playState = false;
						playButton.setText("Play!");
						if (null != playThread) {
							playThread.Terminate();
							playThread = null;
						}
						moveTaskToBack(true);
					}
				});
	}
}