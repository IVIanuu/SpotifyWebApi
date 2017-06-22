package com.ivianuu.spotifywebapi.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivianuu.spotifywebapi.model.AlbumSimple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manuel Wrage (IVIanuu)
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.TrackHolder> {

    private List<AlbumSimple> albums = new ArrayList<>();

    @Override
    public TrackHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrackHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false));
    }

    public void addAlbums(List<AlbumSimple> newAlbums) {
        int oldSize = albums.size();
        albums.addAll(newAlbums);
        notifyItemRangeInserted(oldSize, newAlbums.size());
    }

    @Override
    public void onBindViewHolder(TrackHolder holder, int position) {
        holder.title.setText(albums.get(position).name());
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    static class TrackHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public TrackHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
