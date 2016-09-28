package com.example.thiago.saraiva.marvelcomics.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thiago.saraiva.marvelcomics.Listeners.ComicsListItemClickListener;
import com.example.thiago.saraiva.marvelcomics.R;

import java.util.List;

/**
 * Created by thsaraiva on 12/09/2016.
 */
public class ComicsListAdapter extends RecyclerView.Adapter<ComicsListAdapter.MyViewHolder> {
    //TODO: what object should this list hold?
    private List<String> mDataset;
    private static ComicsListItemClickListener mListener;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mComicThumbnailImageView;
        public TextView mComicTitleTextView;
        public TextView mComicDescriptionTextView;

        public MyViewHolder(View listItemLayoutView) {
            super(listItemLayoutView);
            mComicThumbnailImageView = (ImageView) listItemLayoutView.findViewById(R.id.comic_image);
            mComicTitleTextView = (TextView) listItemLayoutView.findViewById(R.id.comic_title);
            mComicDescriptionTextView = (TextView) listItemLayoutView.findViewById(R.id.comic_description);

            mComicThumbnailImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onButtonClick(v, getAdapterPosition());
                }
            });
        }
    }

    public ComicsListAdapter(List<String> dataset, ComicsListItemClickListener listener) {
        mDataset = dataset;
        mListener = listener;
    }

    @Override
    public ComicsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comic_list_item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        Called by RecyclerView to display the data at the specified position
        //TODO: set thumbnail
        // set title
        //set description
//        holder.mTextView.setText(mDataset.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
