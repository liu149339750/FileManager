package com.lw.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.lw.adapter.FileAdapter;
import com.lw.item.BaseItem;
import com.lw.item.FileItem;
import com.lw.item.ItemFactory;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class FileListFragment extends ListFragment{
	private FileAdapter mAdapter;
	private BaseItem mCurrentItem;
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mCurrentItem = new BaseItem(getActivity(), "/mnt/sdcard/");
		mAdapter = new FileAdapter(getActivity(), getListItems(mCurrentItem));
		setListAdapter(mAdapter);
	}
	
	private List<BaseItem> getListItems(BaseItem item) {
		String path = item.getPath();
		File f = new File(path);
		List<BaseItem> results = new ArrayList<BaseItem>();
		for(File file : f.listFiles()){
			BaseItem fitem = ItemFactory.createItem(getActivity(), file.getPath());
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
		}else{
			FileItem fitem = (FileItem) item;
			fitem.open();
		}
	}

}
