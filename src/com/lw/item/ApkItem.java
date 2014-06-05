package com.lw.item;

import com.example.filemanager.R;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class ApkItem extends HasThumbItem{

	public ApkItem(Context content,String path) {
		super(content,path);
	}

	
	@Override
	protected Drawable getdefaultDrawable() {
		return getDrawable(R.drawable.file_icon_default);
	}
}
