package cse.ust.twittermap.test;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import cse.ust.twittermap.R;
import cse.ust.twittermap.activities.MainActivity;
import cse.ust.twittermap.activities.ProfilePageActivity;

@SuppressLint("NewApi")
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
	
	private MainActivity activityWeTest = null; 
	private final int WAIT_TIME = 20000;
	
	@SuppressWarnings("deprecation")
	public MainActivityTest() {
		super("cse.ust.twittermap",MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activityWeTest = this.getActivity();
	}
	
	public void testSearchMenuItem() {
		
		SystemClock.sleep(WAIT_TIME);
		
		Menu menu = null;
		while (menu == null)
			menu = activityWeTest.getMenu();
		MenuItem searchItem = null;
		while (searchItem == null)
			searchItem = menu.findItem(R.id.menu_search);
		searchItem.collapseActionView();
		SearchView searchView = null;
		while (searchView == null)
			searchView = (SearchView) searchItem.getActionView();
		searchView.setQuery("david", true);
		
		SystemClock.sleep(WAIT_TIME);
	}

	
	private Tab tab1 = null;
	public void testFollowerTabMessage() {
		
		ListView list = null;
		ImageView messageButton = null;
		ActionBar actionBar = null;
		FragmentManager fragmentManager = null;
		Fragment fragment = null;
		View view1 = null;
		View view2 = null;
		DialogFragment dialogFragment = null;
		Dialog dialog = null;
		TextView dm_message = null;
		Button sendButton = null;
		
		SystemClock.sleep(WAIT_TIME);
		
		while (actionBar == null)
			actionBar = activityWeTest.getActionBar();
		while (tab1 == null)
			tab1 = actionBar.getTabAt(1);
		
		activityWeTest.runOnUiThread(new Runnable(){
			@Override
			public void run() {	
				tab1.select();
			}
		});	
		
		SystemClock.sleep(WAIT_TIME);
		
		while (fragmentManager == null) 
			fragmentManager = activityWeTest.getFragManager();
		while (fragment == null)
			fragment = fragmentManager.findFragmentByTag("CURRENTFRAGMENT");
		while (view1 == null)
			view1 = fragment.getView();
		while (list == null)
			list =  (ListView) view1.findViewById(R.id.list);
		while (view2 == null)
			view2 = list.getChildAt(1);
		while (messageButton == null)
			messageButton = (ImageView) view2.findViewById(R.id.button);
		
		activityWeTest.runOnUiThread(new performClickOnView(messageButton));
		
		SystemClock.sleep(WAIT_TIME);
		
		fragmentManager = null;
		while (fragmentManager == null) 
			fragmentManager = activityWeTest.getFragManager();
		while (dialogFragment == null)
			dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag("dialog");
		while (dialog == null)
			dialog = dialogFragment.getDialog();
		while (dm_message == null)
			dm_message = (TextView) dialog.findViewById(R.id.dm_message);
		while (sendButton == null)
			sendButton =  (Button) dialog.findViewById(R.id.send_button);
		
		activityWeTest.runOnUiThread(new setTextOnTextView(dm_message, randomString(10)));
		activityWeTest.runOnUiThread(new performClickOnView(sendButton));
		
		SystemClock.sleep(WAIT_TIME);
	}
	
	@SuppressLint("NewApi")
	public void testDeleteBtn() {
		
		FragmentManager fragmentManager = null;
		DialogFragment dialogFragment = null;
		Dialog dialog = null;
		TextView postMessage = null;
		Button sendButton = null;
		AlertDialog alertDialog = null;
		Button positiveButton = null;
		
		SystemClock.sleep(WAIT_TIME);
		
		getInstrumentation().invokeMenuActionSync(activityWeTest, R.id.menu_post, 0);
		
		SystemClock.sleep(WAIT_TIME);
		
		while (fragmentManager == null)
			fragmentManager = activityWeTest.getFragManager();
		while (dialogFragment == null)
			dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag("dialog");
		while (dialog == null)
			dialog = dialogFragment.getDialog();
		while (postMessage == null)
			postMessage = (TextView) dialog.findViewById(R.id.tweetEdit);
		while (sendButton == null)
			sendButton = (Button) dialog.findViewById(R.id.dialog_positive_btn);
		
		activityWeTest.runOnUiThread(new setTextOnTextView(postMessage, randomString(10)));
		activityWeTest.runOnUiThread(new performClickOnView(sendButton));
		
		SystemClock.sleep(WAIT_TIME);
		
		Fragment fragment = null;
		View view1 = null;
		View view2 = null;
		ListView list = null;
		ImageButton deleteButton = null;
		
		fragmentManager = null;
		while (fragmentManager == null) 
			fragmentManager = activityWeTest.getFragManager();
		while (fragment == null)
			fragment = fragmentManager.findFragmentByTag("CURRENTFRAGMENT");
		while (view1 == null)
			view1 = fragment.getView();
		while (list == null)
			list =  (ListView) view1.findViewById(R.id.list);
		while (view2 == null)
			view2 = list.getChildAt(0);
		while (deleteButton == null)
			deleteButton = (ImageButton) view2.findViewWithTag("delete_button");
		activityWeTest.runOnUiThread(new performClickOnView(deleteButton));
				
		SystemClock.sleep(WAIT_TIME);
		
		fragmentManager = null;
		while (fragmentManager == null)
			fragmentManager = activityWeTest.getFragManager();
		dialogFragment = null;
		while (dialogFragment == null)
			dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag("dialog");
		while (alertDialog == null)
			alertDialog = (AlertDialog) dialogFragment.getDialog();
		while (positiveButton == null)
			positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
		activityWeTest.runOnUiThread(new performClickOnView(positiveButton));

		SystemClock.sleep(WAIT_TIME);
	}

	private Tab tab2 = null;
	public void testFavoriteTabRetweetAndFavor() {
		
		ListView list = null;
		ActionBar actionBar = null;
		FragmentManager fragmentManager = null;
		Fragment fragment = null;
		View view1 = null;
		View view2 = null;
		ImageButton retweetButton = null;
		ImageButton favorButton = null;
		
		while (actionBar == null)
			actionBar = activityWeTest.getActionBar();
		while (tab2 == null)
			tab2 = actionBar.getTabAt(2);
		
		activityWeTest.runOnUiThread(new Runnable(){
			@Override
			public void run() {	
				tab2.select();
			}
		});	
		
		SystemClock.sleep(WAIT_TIME);
		
		while (fragmentManager == null) 
			fragmentManager = activityWeTest.getFragManager();
		while (fragment == null)
			fragment = fragmentManager.findFragmentByTag("CURRENTFRAGMENT");
		while (view1 == null)
			view1 = fragment.getView();
		while (list == null)
			list =  (ListView) view1.findViewById(R.id.list);
		for (int i = 0; ; ++i) {
			view2 = null;
			while (view2 == null)
				view2 = list.getChildAt(i);
			retweetButton = null;
			while (retweetButton == null)
				retweetButton = (ImageButton) view2.findViewById(R.id.retweetButton);
			if (retweetButton.isEnabled())
				break;
			else
				continue;
		}
		while (favorButton == null)
			favorButton = (ImageButton) view2.findViewById(R.id.favoriteButton);

		activityWeTest.runOnUiThread(new performClickOnView(favorButton));
		SystemClock.sleep(WAIT_TIME);
		activityWeTest.runOnUiThread(new performClickOnView(favorButton));
		SystemClock.sleep(WAIT_TIME);
		activityWeTest.runOnUiThread(new performClickOnView(retweetButton));
		SystemClock.sleep(WAIT_TIME);
		activityWeTest.runOnUiThread(new performClickOnView(retweetButton));
		SystemClock.sleep(WAIT_TIME);
	}
	
	public void testReplyButton() {
		
		ListView list = null;
		FragmentManager fragmentManager = null;
		Fragment fragment = null;
		View view1 = null;
		View view2 = null;
		ImageButton replyButton = null;
		DialogFragment dialogFragment = null;
		Dialog dialog = null;
		TextView postMessage = null;
		Button sendButton = null;
		
		SystemClock.sleep(WAIT_TIME);
		
		while (fragmentManager == null) 
			fragmentManager = activityWeTest.getFragManager();
		while (fragment == null)
			fragment = fragmentManager.findFragmentByTag("CURRENTFRAGMENT");
		while (view1 == null)
			view1 = fragment.getView();
		while (list == null)
			list =  (ListView) view1.findViewById(R.id.list);
		while (view2 == null)
			view2 = list.getChildAt(0);
		while (replyButton == null)
			replyButton = (ImageButton) view2.findViewById(R.id.replyButton);

		activityWeTest.runOnUiThread(new performClickOnView(replyButton));
		SystemClock.sleep(WAIT_TIME);

		fragmentManager = null;
		while (fragmentManager == null)
			fragmentManager = activityWeTest.getFragManager();
		dialogFragment = null;
		while (dialogFragment == null)
			dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag("dialog");
		while (dialog == null)
			dialog = dialogFragment.getDialog();
		while (postMessage == null)
			postMessage = (TextView) dialog.findViewById(R.id.tweetEdit);
		while (sendButton == null)
			sendButton = (Button) dialog.findViewById(R.id.dialog_positive_btn);

		activityWeTest.runOnUiThread(new setTextOnTextView(postMessage, randomString(10)));
		activityWeTest.runOnUiThread(new performClickOnView(sendButton));
		
		SystemClock.sleep(WAIT_TIME);
	}
	
	public void testProfileButton() {
		
		SystemClock.sleep(WAIT_TIME);

		FragmentManager fragmentManager = null;
		Fragment mainFragment = null;
		View mainView = null;
		ListView list = null;
		View childView = null;
		ImageView image = null;
		
		while (fragmentManager == null)
			fragmentManager = activityWeTest.getFragManager();
		while(mainFragment == null)
			mainFragment = fragmentManager.findFragmentByTag("CURRENTFRAGMENT");
		while(mainView == null)
			mainView = mainFragment.getView();
		while(list == null)
			list = (ListView) mainView.findViewById(R.id.list);
		while(childView == null)
			childView = (View) list.getChildAt(0);
		while(image == null)
			image =  (ImageView) childView.findViewById(R.id.list_image);
		
		//yzhangad: the following codes cause my tests to hang on my workspace...so I delete them
		//and instead add a new file called ProfilePageActivityTest to test the profile page.
		/*activityWeTest.runOnUiThread(new performClickOnView(image));
		
		SystemClock.sleep(WAIT_TIME);
	
		activityWeTest.finishActivity(ProfilePageActivity.class.hashCode());
		
		SystemClock.sleep(WAIT_TIME);*/
	}

	public static String randomString(int length) {
		String tweet = "";
		for (int i = 0; i < length; i++) {
			Random r = new Random();
			int ascii = r.nextInt(26) + 65;
			tweet += Character.toString((char)ascii);
		}
		return tweet;
	}
}
