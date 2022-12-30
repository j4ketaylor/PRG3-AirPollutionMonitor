package com.example.PRG3AirPollutionMonitor;

import android.os.IBinder;
import android.view.WindowManager;

import androidx.test.espresso.Root;

import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;

// Reference 1 - taken from http://www.qaautomated.com/2016/01/how-to-test-toast-message-using-espresso.html
public class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    public void describeTo(Description description) {
        description.appendText("is toast");
    }

    @Override
    public boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        System.out.println("type"+type);
        if (type == WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();
            System.out.println("windowtoken"+windowToken);
            System.out.println("apptoken"+appToken);
            if (windowToken == appToken) {
                return true;
            }
        }
        return false;
    }
}
// End of Reference 1
