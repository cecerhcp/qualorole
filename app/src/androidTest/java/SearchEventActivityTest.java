
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import engsoft.projects.role.R;
import engsoft.projects.role.activities.SearchEventActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchEventActivityTest {

    private String mStringToBeTyped;

    @Rule
    public ActivityTestRule<SearchEventActivity> mActivityRule = new ActivityTestRule<>(
            SearchEventActivity.class);

    @Before
    public void initValidString() {
        mStringToBeTyped = "engsoft";
    }

    @Test
    public void userInterfaceDemoTest() {

        onView(withId(R.id.cbBaladas)).perform(click());
        onView(withId(R.id.cbBaladas)).check(matches(isNotChecked()));
        onView(withId(R.id.txtSearch)).perform(typeText(mStringToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.scrollLayout)).check(matches(not(isDisplayed())));
        onView(withId(R.id.btSearch)).perform(click());
        onView(withId(R.id.scrollLayout)).check(matches(isDisplayed()));
    }
}