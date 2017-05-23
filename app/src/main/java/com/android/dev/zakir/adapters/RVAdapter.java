package com.android.dev.zakir.adapters;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.dev.zakir.R;
import com.android.dev.zakir.Utility.TypeCall;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;


public class RVAdapter extends Adapter<RVAdapter.ViewHolder> {
    private Context context;
    private List<String> lstItem;
    private TypeCall callFrom;

    public RVAdapter(Context context, List<String> lstItem, TypeCall callFrom) {
        this.context = context;
        this.lstItem = lstItem;
        this.callFrom = callFrom;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        if (TypeCall.VIDEO.equals(callFrom)) {
            v = ((Activity)context).getLayoutInflater().inflate(R.layout.row_video, parent, false);
        } else if (TypeCall.IMAGES.equals(callFrom)) {
            v = ((Activity)context).getLayoutInflater().inflate(R.layout.row_image, parent, false);
        } else if (TypeCall.MILESTONE.equals(callFrom)) {
            v = ((Activity)context).getLayoutInflater().inflate(R.layout.row_video, parent, false);
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (TypeCall.VIDEO.equals(callFrom)){
            holder.row_video_path.setText(lstItem.get(position));
            String title = lstItem.get(position).substring(lstItem.get(position)
                    .lastIndexOf("/"))
                    .replace("/","");
            holder.row_video_title.setText(title);
            holder.video_player.setVideoPath(lstItem.get(position));
            holder.video_player.start();
            holder.video_player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    holder.video_player.start();
                }
            });
        }else if (TypeCall.IMAGES.equals(callFrom)){
//           Picasso.with(context).load(new File(lstItem.get(position))).into(holder.row_image);
            Glide.with(context).load(new File(lstItem.get(position))).into(holder.row_image);
        }/*else if (TypeCall.MILESTONE.equals(callFrom)){
            // I am doing nothing here
        }*/
    }

    @Override
    public int getItemCount() {
        return lstItem.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        VideoView video_player;
        TextView row_video_title, row_video_path;
        ImageView row_image;
         ViewHolder(View itemView) {
            super(itemView);
            if (TypeCall.VIDEO.equals(callFrom)){
                video_player = (VideoView) itemView.findViewById(R.id.row_video);
                row_video_title = (TextView) itemView.findViewById(R.id.row_video_title);
                row_video_path = (TextView) itemView.findViewById(R.id.row_video_path);
            }else if (TypeCall.IMAGES.equals(callFrom)){
                row_image = (ImageView) itemView.findViewById(R.id.row_image);
            }/*else if (TypeCall.MILESTONE.equals(callFrom)){
//                I am doing nothing here
            }*/

        }
    }
}
