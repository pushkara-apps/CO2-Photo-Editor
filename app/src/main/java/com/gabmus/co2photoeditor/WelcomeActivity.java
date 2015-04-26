package com.gabmus.co2photoeditor;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;


public class WelcomeActivity extends FragmentActivity {


    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        mPager = (ViewPager) findViewById(R.id.welcome_pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private final int[] slidesPics = {R.drawable.rms1, R.drawable.rms2, R.drawable.rms3, R.drawable.demo_icon, -1};

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            WelcomeFragment w = new WelcomeFragment();
            Bundle args = new Bundle();
            args.putInt("currentSlide", slidesPics[position]);
            w.setArguments(args);
            return w;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


    //fragment
    public static class WelcomeFragment extends Fragment {

        private ImageView imgVw;
        private int slide;

        public WelcomeFragment() {}



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
            slide= getArguments().getInt("currentSlide");
            imgVw = (ImageView) rootView.findViewById(R.id.imageViewWelcome);
            if (slide==-1) {
                imgVw.setVisibility(View.GONE);
                ((ScrollView) rootView.findViewById(R.id.cont_tutorial_complete)).setVisibility(View.VISIBLE);
            }
            else {
                imgVw.setImageDrawable(getResources().getDrawable(slide));
            }

            return rootView;
        }
    }
}
