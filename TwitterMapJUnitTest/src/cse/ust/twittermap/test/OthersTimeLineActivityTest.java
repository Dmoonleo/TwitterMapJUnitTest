package cse.ust.twittermap.test;

import twitter4j.Twitter;
import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import cse.ust.twittermap.Key;
import cse.ust.twittermap.R;
import cse.ust.twittermap.TwitterFactory;
import cse.ust.twittermap.Verification;
import cse.ust.twittermap.activities.OthersTimeLineActivity;
import cse.ust.twittermap.fragments.HomeFragment;

@SuppressLint("CommitTransaction")
public class OthersTimeLineActivityTest extends ActivityInstrumentationTestCase2<OthersTimeLineActivity> {

	OthersTimeLineActivity activityWeTest;
	Twitter twitter;
	
	@SuppressWarnings("deprecation")
	public OthersTimeLineActivityTest() {
		super("cse.ust.twittermap",OthersTimeLineActivity.class);
	}

	@UiThreadTest
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent i = new Intent();
		i.putExtra("screenName", "leungyingying");
		this.setActivityIntent(i);
		activityWeTest = this.getActivity();
		twitter = TwitterFactory.get4jInstance();
	}
	
	@SuppressLint("NewApi")
	@UiThreadTest
	public void testOthersTimeline() {

		SystemClock.sleep(5000);
		final String screenName = "leungyingying";
		HomeFragment newFragment = new HomeFragment(screenName);
		FragmentTransaction transaction = activityWeTest.getFragmentManager().beginTransaction();
		transaction.replace(R.id.content_fragment, newFragment);
		SystemClock.sleep(5000);
	}

	@SuppressWarnings("unused")
	@UiThreadTest
	@SuppressLint("NewApi") 
	public void testSupplementary() {
		Key key = new Key();
		Verification verification = new Verification();
		TwitterFactory twitterFactory = new TwitterFactory();
	}
}
