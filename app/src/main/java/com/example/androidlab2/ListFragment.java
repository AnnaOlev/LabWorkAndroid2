package com.example.androidlab2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    ArrayList<ListItem> mItems;

    public static ListFragment newInstance() {
        return new ListFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
             mItems = bundle.getParcelableArrayList("itemsarray");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView listRecyclerView = v.findViewById(R.id.list_recycler_view);
        listRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false));
        ListFragment.ItemAdapter adapter = new ListFragment.ItemAdapter(mItems, getActivity());
        listRecyclerView.setAdapter(adapter);
        return v;
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private ImageView mContentImageView;

        ItemHolder(View view) {
            super(view);

            itemView.setOnClickListener(this);
            mTitleTextView = view.findViewById(R.id.number);
            mContentImageView = view.findViewById(R.id.picture);
        }

        public void bindListItem(ListItem item) {

            mTitleTextView.setText(item.toString());
            if (item.getGraphics() != null) {
                Picasso.get()
                        .load(item.getGraphics())
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.not_found)
                        .into(mContentImageView);
            }
        }

        @Override
        public void onClick (View view){

            Intent intent = new Intent(getActivity(), ItemPagerActivity.class);
            intent.putParcelableArrayListExtra("items", mItems);
            intent.putExtra("element", mItems.get(getAdapterPosition()).getName());
            startActivity(intent);
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ListFragment.ItemHolder> {
        private List<ListItem> mListItems;
        private Context context;

        ItemAdapter(List<ListItem> listItems, Context context) {
            mListItems = listItems;
            this.context = context;
        }

        @NonNull
        @Override
        public ListFragment.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_of_list, viewGroup, false );
            return new ListFragment.ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ListFragment.ItemHolder itemHolder, int position) {
            ListItem listItem = mListItems.get(position);
            itemHolder.bindListItem(listItem);
        }

        @Override
        public int getItemCount() {
            return mListItems.size();
        }
    }
}
