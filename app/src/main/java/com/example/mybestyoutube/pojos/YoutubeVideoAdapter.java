package com.example.mybestyoutube.pojos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mybestyoutube.R;

import java.util.List;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeVideoViewHolder> {
    private final List<YoutubeVideo> youtubeVideoList;
    private final OnItemClickListener listener;
    public class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {
        public TextView Title;
        public TextView Category;
        public TextView Description;

        public YoutubeVideoViewHolder(View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.title);
            Description = itemView.findViewById(R.id.description);
        }

        public void bind(final YoutubeVideo item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public YoutubeVideoAdapter(List<YoutubeVideo> youtubeVideoList, OnItemClickListener listener) {
        this.youtubeVideoList = youtubeVideoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public YoutubeVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.youtube_video_item, parent, false);
        return new YoutubeVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeVideoViewHolder holder, int position) {
        YoutubeVideo youtubeVideo = youtubeVideoList.get(position);
        holder.Title.setText(youtubeVideo.getTitle());
        holder.Description.setText(youtubeVideo.getDescription());
        holder.bind(youtubeVideo, listener);

    }

    @Override
    public int getItemCount() {
        return youtubeVideoList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(YoutubeVideo item);
    }
}
