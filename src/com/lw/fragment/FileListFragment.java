package com.lw.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.filemanager.R;
import com.lw.adapter.FileAdapter;
import com.lw.item.BaseItem;
import com.lw.item.FileItem;
import com.lw.item.ItemFactory;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class FileListFragment extends ListFragment implements OnClickListener{
	private FileAdapter mAdapter;
	private BaseItem mCurrentItem;
	private ImageButton mUpPress;
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		mUpPress = (ImageButton) getActivity().findViewById(R.id.up_press);
		mUpPress.setOnClickListener(this);
		mCurrentItem = new BaseItem(getActivity(), "/mnt/sdcard/");
		mAdapter = new FileAdapter(getActivity(), getListItems(mCurrentItem));
		setListAdapter(mAdapter);
		updataStatus();
	}
	
	private List<BaseItem> getListItems(BaseItem item) {
		String path = item.getPath();
		File f = new File(path);
		List<BaseItem> results = new ArrayList<BaseItem>();
		File files[] = f.listFiles();
		if(files == null)
			return new ArrayList<BaseItem>();
		for(File file : files){
			BaseItem fitem = ItemFactory.createItem(getActivity(), file);
			fitem.setParent(item);
			results.add(fitem);
		}
		return results;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		BaseItem item = (BaseItem) mAdapter.getItem(position);
		if(!item.isFile()){
			mCurrentItem = item;
			mAdapter.changeItems(getListItems(item));
			updataStatus();
		}else{
			FileItem fitem = (FileItem) item;
			fitem.open();
		}
	}

	@Override
	public void onClick(View v) {
		if(v == mUpPress){
			showParent();
		}
	}

	private void showParent() {
		mCurrentItem = mCurrentItem.getParent();
		mAdapter.changeItems(getListItems(mCurrentItem));
		updataStatus();
	}

	private void updataStatus() {
		if(mCurrentItem.getParent() != null)
			mUpPress.setVisibility(View.VISIBLE);
		else
			mUpPress.setVisibility(View.INVISIBLE);
	}
	
	public boolean back(){
		if(mCurrentItem.getParent() != null){
			showParent();
			return true;
		}
		return false;
	}

}
