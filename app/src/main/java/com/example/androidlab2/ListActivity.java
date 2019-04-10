package com.example.androidlab2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import java.util.ArrayList;


public class ListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {

        ArrayList<ListItem> mItems = getIntent().getParcelableArrayListExtra("itemsarray");

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("itemsarray", mItems);
        Fragment fragment = ListFragment.newInstance();
        fragment.setArguments(bundle);
        return fragment;
    }
}
