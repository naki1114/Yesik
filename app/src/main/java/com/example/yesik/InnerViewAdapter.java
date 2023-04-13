package com.example.yesik;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class InnerViewAdapter extends RecyclerView.Adapter<InnerViewAdapter.InnerViewViewHolder> {

    private ArrayList<InnerViewItem> innerViewImageList;

    InnerViewViewHolder innerViewViewHolder;

    public InnerViewAdapter (ArrayList<InnerViewItem> innerView) {
        innerViewImageList = innerView;
    }

    public class InnerViewViewHolder extends RecyclerView.ViewHolder {

        ImageButton innerViewImage;

        public InnerViewViewHolder(Context context, View itemView) {
            super(itemView);
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
        holder.innerViewImage.setImageBitmap(innerViewImageList.get(position).getInnerView());
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
