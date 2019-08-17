package com.whackyapps.pallavgrover.pallav_starwars_challenge


import android.content.res.Resources
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.v7.widget.RecyclerView
import android.content.res.Resources.NotFoundException



@LargeTest
@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun searchActivityTest() {
        val textInputEditText = onView(
            allOf(
                withId(R.id.queryEt),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.queryTil),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(click())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.queryEt),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.queryTil),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("han"), closeSoftKeyboard())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.queryEt), withText("han"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.queryTil),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(click())



        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(3000)

        onView(withId(R.id.rv_main_home))
            .check(matches(hasDescendant(withText("Han Solo")))).perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.nameTv))
            .check(matches(withText("Han Solo")))

        onView(withId(R.id.birthYearTv))
            .check(matches(withText("29BBY")))

        onView(withId(R.id.filmsRv))
            .check(matches(hasDescendant(withText("The Empire Strikes Back"))))

        onView(withId(R.id.filmsRv))
            .check(matches(hasDescendant(withText("Return of the Jedi"))))

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}

fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
    return RecyclerViewMatcher(recyclerViewId)
}

class RecyclerViewMatcher(internal val mRecyclerViewId: Int) {

    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            internal var resources: Resources? = null
            internal var childView: View? = null

            override fun describeTo(description: Description) {
                val id = if (targetViewId == -1) mRecyclerViewId else targetViewId
                var idDescription = Integer.toString(id)
                if (this.resources != null) {
                    try {
                        idDescription = this.resources!!.getResourceName(id)
                    } catch (var4: Resources.NotFoundException) {
                        idDescription = String.format("%s (resource name not found)", id)
                    }

                }

                description.appendText("with id: $idDescription")
            }

            public override fun matchesSafely(view: View): Boolean {

                this.resources = view.resources

                if (childView == null) {
                    val recyclerView = view.rootView.findViewById<View>(mRecyclerViewId) as RecyclerView
                    if (recyclerView != null) {

                        childView = recyclerView.findViewHolderForAdapterPosition(position)!!.itemView
                    } else {
                        return false
                    }
                }

                if (targetViewId == -1) {
                    return view === childView
                } else {
                    val targetView = childView!!.findViewById<View>(targetViewId)
                    return view === targetView
                }

            }
        }
    }
}