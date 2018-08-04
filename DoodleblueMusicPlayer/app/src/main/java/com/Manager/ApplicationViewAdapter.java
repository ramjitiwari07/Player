package com.Manager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.android.db.SongsList;
import com.androidhive.musicplayer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ApplicationViewAdapter extends BaseAdapter {
    int y = 0;
    Context mcontext;
    //List<InvoiceDueDetail> invoiceDueDetails;
    Intent intent;
    List<SongsList> temp_songs;

    public ApplicationViewAdapter(Context context,List<SongsList> songsList_new) {
        this.mcontext = context;
        this.temp_songs=songsList_new;
    }

    @Override
    public int getCount() {
        return temp_songs.size();
    }

    @Override
    public Object getItem(int i) {
        return temp_songs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final LinearLayout invoice_due_parent;
        final int pos = i;
        TextView song_name;
        ImageView song_image;
        final RadioButton first;
        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(mcontext.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.applicant_view_row_list, null);
        song_name = (TextView) convertView.findViewById(R.id.song_name);
        song_image=(ImageView)convertView.findViewById(R.id.song_image);
        song_name.setText(temp_songs.get(pos).getSongName());

        Picasso.with(mcontext)
                .load(temp_songs.get(pos).getThumImage())
                .resize(150, 150)
                .centerInside()
                .into(song_image);

        return convertView;
    }

}



