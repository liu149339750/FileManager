package com.lw.item;

import com.example.filemanager.R;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class VideoItem extends HasThumbItem{

	public VideoItem(Context context,String path) {
		super(context,path);
	}
	
	@Override
	protected Drawable getdefaultDrawable() {
		// TODO Auto-generated method stub
		return getDrawable(R.drawable.file_icon_video);
	}
}
