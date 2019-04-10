package com.example.androidlab2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;

public class ItemPagerActivity extends AppCompatActivity{

    ViewPager viewPager;
    ArrayList<ListItem> mItems;
    String element;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager);

        viewPager = findViewById(R.id.view_pager);
        mItems = getIntent().getParcelableArrayListExtra("items");
        element = getIntent().getStringExtra("element");


        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                ListItem item = mItems.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("item", item);
                Fragment fragment = BigListFragment.newInstance();
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                return mItems.size();
            }
        });

        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i).getName().equals(element)) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
