package cse.ust.twittermap.test;

import android.widget.TextView;

public class setTextOnTextView implements Runnable {

	private TextView view = null;
	private String str = null;
	
	public setTextOnTextView(TextView view, String str) {
		this.view = view;
		this.str = str;
	}
	
	@Override
	public void run() {
		String cur = view.getText().toString();
		view.setText(cur + str);
	}
}
