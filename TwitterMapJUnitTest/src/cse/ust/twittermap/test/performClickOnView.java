package cse.ust.twittermap.test;

import android.view.View;

public class performClickOnView implements Runnable {

	private View view = null;
	
	public performClickOnView(View view) {
		this.view = view;
	}
	
	@Override
	public void run() {
		view.performClick();
	}

}
