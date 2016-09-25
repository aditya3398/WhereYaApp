package wya.whereyaat;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class OnboardingActivity extends FragmentActivity {

    int previousColor;
    int currentColor;
    RelativeLayout relativeLayout;
    ViewPager viewPager;
    MyPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        previousColor = ContextCompat.getColor(this ,R.color.onboardingbg1);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_onboarding);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        setStatusBarColor(findViewById(R.id.statusBarBackground), ContextCompat.getColor(this, R.color.transparent));


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        currentColor = ContextCompat.getColor(getApplicationContext(), R.color.onboardingbg1);
                        break;
                    case 1:
                        currentColor = ContextCompat.getColor(getApplicationContext(), R.color.onboardingbg2);
                        break;
                    case 2:
                        currentColor = ContextCompat.getColor(getApplicationContext(), R.color.onboardingbg3);
                        break;
                    case 3:
                        currentColor = ContextCompat.getColor(getApplicationContext(), R.color.onboardingbg4);
                        break;
                    case 4:
                        currentColor = ContextCompat.getColor(getApplicationContext(), R.color.onboardingbg5);
                        break;
                }

                ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        // Use animation position to blend colors.
                        float position = animation.getAnimatedFraction();

                        // Apply blended color to the ActionBar.
                        int blended = blendColors(previousColor, currentColor, position);
                        relativeLayout.setBackgroundColor(blended);

                        // Apply blended color to the ActionBar.
                        setStatusBarColor(findViewById(R.id.statusBarBackground), blended);

                        if(position == 1){
                            previousColor = currentColor;
                        }
                    }
                });
                anim.setDuration(250).start();
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    private int blendColors(int from, int to, float ratio) {
        final float inverseRatio = 1f - ratio;

        final float r = Color.red(to) * ratio + Color.red(from) * inverseRatio;
        final float g = Color.green(to) * ratio + Color.green(from) * inverseRatio;
        final float b = Color.blue(to) * ratio + Color.blue(from) * inverseRatio;

        return Color.rgb((int) r, (int) g, (int) b);
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        private final int NUM_PAGES = 5;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            OnboardingFragment onboardingFragment = new OnboardingFragment();
            onboardingFragment.newInstance(position);
            return onboardingFragment;
        }
    }

    public void setStatusBarColor(View statusBar, int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //status bar height
            int actionBarHeight = getActionBarHeight();
            int statusBarHeight = getStatusBarHeight();
            //action bar height
            statusBar.getLayoutParams().height = actionBarHeight + statusBarHeight;
            statusBar.setBackgroundColor(color);
        }
    }

    public int getActionBarHeight() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
