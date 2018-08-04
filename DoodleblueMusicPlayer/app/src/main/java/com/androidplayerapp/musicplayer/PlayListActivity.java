package com.androidplayerapp.musicplayer;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.Manager.AllPackageManager;
import com.Manager.ApplicationViewAdapter;
import com.android.db.SongsList;
import com.androidhive.musicplayer.R;

public class PlayListActivity extends Activity {
	// Songs list
	List<SongsList> songsList_new;
	ListView listview;
	ApplicationViewAdapter applicationViewAdapter;
	Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_form);
		context=this;
		listview=(ListView)findViewById(R.id.listview);
		songsList_new=new AllPackageManager(getApplicationContext()).getSongList();
		Log.i("respo_getSongsLst_size=",""+songsList_new.size());
		applicationViewAdapter=new ApplicationViewAdapter(context,songsList_new);
		listview.setAdapter(applicationViewAdapter);
		// listening to single listitem click
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting listitem index
				int songIndex = position;
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(),
						MainActivity.class);
				// Sending songIndex to PlayerActivity
				in.putExtra("songIndex", songIndex);
				setResult(100, in);
				// Closing PlayListView
				finish();
			}
		});

	}
}
