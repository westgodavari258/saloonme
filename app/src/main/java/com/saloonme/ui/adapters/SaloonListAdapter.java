package com.saloonme.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.saloonme.model.response.SaloonListResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaloonListAdapter extends RecyclerView.Adapter<SaloonListAdapter.ViewHolder> {
    private final Context context;
    private final ItemListener itemListener;
    private final boolean showBookNowOption;
    private List<SaloonListResponseData> saloonListResponseDataList;

    public SaloonListAdapter(Context context, ItemListener itemListener, boolean showBookNowOption) {
        this.context = context;
        this.itemListener = itemListener;
        this.showBookNowOption = showBookNowOption;
    }

    public void setData(List<SaloonListResponseData> saloonListResponseDataList) {
        this.saloonListResponseDataList = saloonListResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.saloon_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(saloonListResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return saloonListResponseDataList != null ? saloonListResponseDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.iv_saloon)
        ImageView iv_saloon;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_saloon)
        TextView tv_saloon;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_location)
        TextView tv_location;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_bookNow)
        TextView tv_bookNow;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_rating)
        TextView tv_rating;
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_openTimings)
        TextView tv_openTimings;

        @SuppressLint("NonConstantResourceId")
        @OnClick(R.id.card_menu)
        void onSaloonClick() {
            if (itemListener != null) {
                itemListener.onItemClick(saloonListResponseDataList.get(getAdapterPosition()));
            }
        }

        @OnClick(R.id.tv_bookNow)
        void onBookNowClick() {
            if (itemListener != null) {
                itemListener.onBookNowClick(saloonListResponseDataList.get(getAdapterPosition()));
            }
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setData(SaloonListResponseData saloonListResponseData) {
            if (showBookNowOption) {
                tv_bookNow.setVisibility(View.VISIBLE);
            } else {
                tv_bookNow.setVisibility(View.INVISIBLE);
            }
            Glide.with(context).load(saloonListResponseData.getStoreImg())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_saloon);
            tv_saloon.setText(saloonListResponseData.getStoreName());
            tv_location.setText("Distance : " + saloonListResponseData.getDistance() + " Km");
            tv_openTimings.setText("Timings : " + saloonListResponseData.getNew_salon_time_slot());
        }
    }

    public interface ItemListener {
        void onItemClick(SaloonListResponseData saloonListResponseData);

        void onBookNowClick(SaloonListResponseData saloonListResponseData);
    }
}
