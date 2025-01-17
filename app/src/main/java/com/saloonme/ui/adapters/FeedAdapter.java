package com.saloonme.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.saloonme.R;
import com.saloonme.model.response.FeedListResponse;
import com.saloonme.model.response.PromotionsResponseData;
import com.saloonme.ui.activities.CommentsActivity;
import com.saloonme.util.PrefUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private Context mContext;
    private  FeedItemListener itemListener;
    private List<FeedListResponse> feedListResponseList;

    public FeedAdapter(Context mContext,FeedItemListener itemListener) {
        this.mContext = mContext;
        this.itemListener=itemListener;

    }


    public void setData(List<FeedListResponse> feedListResponseList) {
        this.feedListResponseList = feedListResponseList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_feed, parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData(feedListResponseList.get(i));
    }

    @Override
    public int getItemCount() {
        return feedListResponseList != null ? feedListResponseList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.userNameTv)
        TextView userNameTv;
        @BindView(R.id.postMsgTv)
        TextView postMsgTv;
        @BindView(R.id.isLikedIv)
        ImageView isLikedIv;
        @BindView(R.id.isLikedFillIv)
        ImageView isLikedFillIv;
        @BindView(R.id.postIv)
        ImageView postIv;


        @OnClick(R.id.isLikedIv)
        void onLikeClick() {
            itemListener.onFavouriteClick(feedListResponseList.get(getAdapterPosition()).getFeedSno(),
                    PrefUtils.getInstance().getUserId());

        }
        @OnClick(R.id.isCommentIv)
        void onCommentClick() {

            itemListener.onCommentClick(feedListResponseList.get(getAdapterPosition()));
        }
        @OnClick(R.id.isForwardIv)
        void onForwardClick() {
            if (itemListener != null) {
                itemListener.onShareClicked(feedListResponseList.get(getAdapterPosition()),postIv);
            }
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(FeedListResponse feedListResponse) {
            userNameTv.setText(feedListResponse.getFeedName());
            postMsgTv.setText(feedListResponse.getFeedDes());
            Glide.with(mContext).load( feedListResponse.getFeedImg())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(postIv);
            if (feedListResponse.getAdd_fav_status()){
             isLikedFillIv.setVisibility(View.VISIBLE);
             isLikedIv.setVisibility(View.GONE);
            }else{
                isLikedFillIv.setVisibility(View.GONE);
                isLikedIv.setVisibility(View.VISIBLE);
            }

        }

    }

    public interface FeedItemListener {

        void onFavouriteClick(String feedSno, String feedUserId);

        void onCommentClick(FeedListResponse feedListResponse);

        void onShareClicked(FeedListResponse feedListResponse,ImageView imageView);
    }
}

