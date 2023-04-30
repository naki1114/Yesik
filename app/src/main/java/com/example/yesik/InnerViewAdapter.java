package com.example.yesik;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class InnerViewAdapter extends RecyclerView.Adapter<InnerViewAdapter.InnerViewViewHolder> {

    private ArrayList<InnerViewItem> innerViewImageList;

    InnerViewViewHolder innerViewViewHolder;

    Context contextActivity;

    public InnerViewAdapter (ArrayList<InnerViewItem> innerView) {
        innerViewImageList = innerView;
    }

    public class InnerViewViewHolder extends RecyclerView.ViewHolder {

        ImageButton innerViewImage;

        public InnerViewViewHolder(Context context, View itemView) {
            super(itemView);
            contextActivity = context;
            innerViewImage = itemView.findViewById(R.id.innerViewImage);

            innerViewImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    if (mListener != null) {
                        mListener.onImageClick(view, position);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public InnerViewViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.inner_view, parent, false);

        innerViewViewHolder = new InnerViewAdapter.InnerViewViewHolder(context, view);

        return innerViewViewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull InnerViewAdapter.InnerViewViewHolder holder, int position) {
        Glide.with(contextActivity)
                .load(innerViewImageList.get(position).getInnerView())
                .into(holder.innerViewImage);
    }

    @Override
    public int getItemCount() {
        return (null != innerViewImageList ? innerViewImageList.size() : 0);
    }

    public void addData(InnerViewItem innerViewItem) {
        innerViewImageList.add(0, innerViewItem);
    }

    public interface OnImageClickListener {
        void onImageClick(View v, int position);
    }

    private OnImageClickListener mListener = null;

    public void setOnImageClickListener(OnImageClickListener listener) {
        this.mListener = listener;
    }

}
