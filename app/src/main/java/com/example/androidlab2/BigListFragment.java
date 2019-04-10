package com.example.androidlab2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class BigListFragment extends Fragment {

    ListItem mListItem;

    public static BigListFragment newInstance(){

        return new BigListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mListItem = bundle.getParcelable("item");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.big_item, containter, false);

        TextView mTitleText = v.findViewById(R.id.title);
        TextView mHelpText = v.findViewById(R.id.info);
        ImageView mContentImageView = v.findViewById(R.id.big_image);

        mTitleText.setText(mListItem.getName());

        if (mListItem.getHelptext() != null)
            mHelpText.setText(mListItem.getHelptext());

        Picasso.get()
                .load(mListItem.getGraphics())
                .placeholder(R.drawable.loading)
                .error(R.drawable.not_found)
                .into(mContentImageView);


        return v;
    }
}
