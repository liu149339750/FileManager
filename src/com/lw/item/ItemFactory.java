package com.lw.item;

import java.io.File;

import com.example.filemanager.MediaFile;

import android.content.Context;

public class ItemFactory {

	
	public static BaseItem createItem(Context context,String path){
		File file = new File(path);
		BaseItem item = null;
		if(file.isDirectory())
			item = new DirectoryItem(context, path);
		else {
			int fileType = MediaFile.getFormatCode(path, null);
			if(MediaFile.isAudioFileType(fileType))
				item = new AudioItem(context, path);
			else if(MediaFile.isImageFileType(fileType))
				item = new ImageItem(context, path);
			else if(MediaFile.isVideoFileType(fileType))
				item = new VideoItem(context, path);
			else if(fileType == MediaFile.FILE_TYPE_APK)
				item = new ApkItem(context,path);
			else 
				item = new FileItem(context, path);
			item.setMimeTypeCode(fileType);
		}
		return item;
	}
}
