package com.example.thiago.saraiva.marvelcomics.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thiago.saraiva.marvelcomics.Listeners.ComicsListItemClickListener;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics.MarvelComic;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics.MarvelComicPrice;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Common.MarvelTextObject;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Common.MarvelThumbnail;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Creators.MarvelCreator;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Creators.MarvelCreatorSummary;
import com.example.thiago.saraiva.marvelcomics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thsaraiva on 12/09/2016.
 */
public class ComicsListAdapter extends RecyclerView.Adapter<ComicsListAdapter.MyViewHolder> {
    private List<MarvelComic> mDataset;

    public ComicsListAdapter() {
        mDataset = new ArrayList<MarvelComic>();
    }

    public void setmDataset(List<MarvelComic> mDataset) {
        this.mDataset = mDataset;
        this.notifyDataSetChanged();
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mComicThumbnailImageView;
        public TextView mComicTitleTextView;
        public TextView mComicDescriptionTextView;
        private TextView mComicPagesTextView;
        private TextView mComicPriceTextView;
        private TextView mComicAuthorsTextView;

        public MyViewHolder(View listItemLayoutView) {
            super(listItemLayoutView);
            mComicThumbnailImageView = (ImageView) listItemLayoutView.findViewById(R.id.comic_image);
            mComicTitleTextView = (TextView) listItemLayoutView.findViewById(R.id.comic_title);
            mComicPagesTextView = (TextView) listItemLayoutView.findViewById(R.id.comic_pages);
            mComicPriceTextView = (TextView) listItemLayoutView.findViewById(R.id.comic_price);
            mComicDescriptionTextView = (TextView) listItemLayoutView.findViewById(R.id.comic_description);
            mComicAuthorsTextView = (TextView) listItemLayoutView.findViewById(R.id.comic_author);

            View titleView = listItemLayoutView.findViewById(R.id.titleView);
            final View descriptionView = listItemLayoutView.findViewById(R.id.descriptionView);
            titleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (descriptionView.getVisibility() == View.GONE) {
                        descriptionView.setVisibility(View.VISIBLE);
                    } else if (descriptionView.getVisibility() == View.VISIBLE) {
                        descriptionView.setVisibility(View.GONE);
                    }
                }
            });
        }

        public void setImage(String url) {
            if (url != "" && url != null) {
                Picasso.with(mComicThumbnailImageView.getContext())
                        .load(url)
                        .placeholder(R.drawable.no_image)
                        .error(R.drawable.no_image_error)
                        .into(mComicThumbnailImageView);
            } else {
                Picasso.with(mComicThumbnailImageView.getContext()).load(R.drawable.no_image).into(mComicThumbnailImageView);
                Log.e("ComicsListAdapter", "No image available.Using default");
            }

        }

        public void setTitle(String title) {
            if (title != null) {
                mComicTitleTextView.setText(title);
            } else {
                mComicTitleTextView.setText(R.string.default_title_text);
            }
        }

        public void setPages(String pages) {
            if (pages != null)
                mComicPagesTextView.setText(pages);
            else
                mComicPagesTextView.setText(R.string.default_page_text);
        }

        public void setPrices(String prices) {
            if (prices != null)
                mComicPriceTextView.setText(prices);
            else
                mComicPriceTextView.setText(R.string.default_price_text);
        }

        public void setDescription(String description) {
            if (description != null)
                mComicDescriptionTextView.setText(description);
            else
                mComicDescriptionTextView.setText(R.string.default_description_text);
        }

        public void setAuthors(String authors) {
            if (authors != null)
                mComicAuthorsTextView.setText(authors);
            else
                mComicAuthorsTextView.setText(R.string.default_author_text);
        }
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
        MarvelComic comic = mDataset.get(position);
        MarvelThumbnail thumbnail = comic.getThumbnail();
        String url = "";
        if (thumbnail != null) {
            url = thumbnail.getPath() + "." + thumbnail.getExtension();
        }
        holder.setImage(url);
        holder.setTitle(comic.getTitle());
        holder.setPages(""+comic.getPageCount());
        MarvelComicPrice marvelComicPrice = comic.getPrices()[0];
        if (marvelComicPrice != null){
            holder.setPrices(""+marvelComicPrice.getPrice());
        }else{
            holder.setPrices(null);
        }
        holder.setDescription(comic.getDescription());
        MarvelCreatorSummary creatorsSummary = comic.getCreators();
        int totalAuthorsNumber = creatorsSummary.getReturned();
        MarvelCreator[] authors = creatorsSummary.getItems();
        String authorsNames = "";
        for(int i = 0; i < totalAuthorsNumber;i++){
            String name = authors[i].getName();
            if(name!= null){
                authorsNames+=name;
            }
            if(i != totalAuthorsNumber-1){
                authorsNames+=" / ";
            }
        }
        holder.setAuthors(authorsNames);

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
