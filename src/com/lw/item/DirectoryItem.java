package com.lw.item;

import com.example.filemanager.R;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class DirectoryItem extends BaseItem{

	public DirectoryItem(Context content, String path) {
		super(content, path);
	}

	
	@Override
	protected Drawable getdefaultDrawable() {
		// TODO Auto-generated method stub
		return getDrawable(R.drawable.folder);
	}
}
