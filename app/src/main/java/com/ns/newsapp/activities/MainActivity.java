package com.ns.newsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.ns.newsapp.R;
import com.ns.newsapp.adapters.FragmentAdapter;

public class MainActivity extends AppCompatActivity {
    private static final boolean d = true;
    private static final String TAG = "dMainAc";

    TabLayout mTabs;
    ViewPager2 mViewPager;
    FragmentAdapter mFragAdapt;

    public static final String[] TABS = new String[] {
            "Home", "CAT1"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabs = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.view_pager);

        FragmentManager fm = getSupportFragmentManager();
        mFragAdapt = new FragmentAdapter(fm, getLifecycle());

        mViewPager.setAdapter(mFragAdapt);

        for (String tab : TABS)
            mTabs.addTab(mTabs.newTab().setText(tab));

        mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                if (d) Log.d(TAG, "onTabSelected()");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (d) Log.d(TAG, "onTabUnelected()");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (d) Log.d(TAG, "onTabReselected()");
            }
        });


        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTabs.selectTab(mTabs.getTabAt(position));
            }
        });

    }
}