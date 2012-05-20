package jp.modal.soul.uiTestSample.test;

import jp.modal.soul.uiTestSample.UiTestSampleActivity;
import jp.modal.soul.uiTestSample.UiTestSampleTransitActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.InstrumentationTestCase;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.TextView;

public class UiTestSampleTest extends InstrumentationTestCase {
	

	public void testClickButton1_ƒ{ƒ^ƒ“‚P‚ð‰Ÿ‚·(){
		// Register UiTestSampleActivity...
		Instrumentation mInstrumentation = getInstrumentation();
		Instrumentation.ActivityMonitor monitor = mInstrumentation.addMonitor(UiTestSampleActivity.class.getName(), null, false);

		// Start the UiTestSampleActivity as the first activity...
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setClassName(mInstrumentation.getTargetContext(), UiTestSampleActivity.class.getName());
		mInstrumentation.startActivitySync(intent);
		
		// Wait for it to start...
		Activity mCurrentActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5);
		assertNotNull(mCurrentActivity);
		
		// Remove previously added ActivityMonitor...
		mInstrumentation.removeMonitor(monitor);
		// Register UiTestSampleTransitActivity...
		monitor = mInstrumentation.addMonitor(UiTestSampleTransitActivity.class.getName(), null, false);
		
		// Emulate button click...
		Button button = (Button)mCurrentActivity.findViewById(jp.modal.soul.uiTestSample.R.id.button1);
		TouchUtils.clickView(this, button);
		
		// Wait for the welcome transited page to start...
		mCurrentActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5);
		
		TextView textView = (TextView)mCurrentActivity.findViewById(jp.modal.soul.uiTestSample.R.id.transitedTextView);
		assertEquals(textView.getText().toString(), "Transited.");

		
	}

}
