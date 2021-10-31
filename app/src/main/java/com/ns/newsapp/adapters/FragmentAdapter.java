package com.ns.newsapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ns.newsapp.activities.MainActivity;
import com.ns.newsapp.fragments.HomeFragment;
import com.ns.newsapp.fragments.SecondFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 1: return new SecondFragment();
        }

        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return MainActivity.TABS.length;
    }
}
