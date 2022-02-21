package com.naturewallpaper.allimagesfolder.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.naturewallpaper.allimagesfolder.R;

import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {
    RecyclerView MAIN_recycler;
    ImageAdapter imageAdapter;
    ArrayList<ModelImage> modelImageArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        MAIN_recycler=findViewById(R.id.MAIN_recycler);
        MAIN_recycler.setLayoutManager(new GridLayoutManager(this,2));
        modelImageArrayList=getImagesPaths();
        imageAdapter= new ImageAdapter(this,modelImageArrayList);
        MAIN_recycler.setAdapter(imageAdapter);
    }
//    public  void getImagesPath(){
//           ArrayList<ModelImage> imagesFolder=new ArrayList<>();
//           ArrayList<String> imagesPath= new ArrayList<>();
//           Uri allImgUri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//           String [] Projection={MediaStore.Images.ImageColumns.DATA,MediaStore.Images.Media.DISPLAY_NAME,MediaStore.Images.Media.BUCKET_DISPLAY_NAME,MediaStore.Images.Media.BUCKET_ID};
//           Cursor imageCursor=getContentResolver().query(allImgUri,Projection,null,null,null);
//           if (imageCursor!=null){
//               imageCursor.moveToFirst();
//           }
//           do {
//               ModelImage FOLDER_IMAGES = new ModelImage();
//
//
//
//           } while (imageCursor.moveToNext());
//
//    }

    public ArrayList<ModelImage> getImagesPaths() {
        ArrayList<ModelImage> imgFolders = new ArrayList<>();
        ArrayList<String> imgesPaths = new ArrayList<>();
        Uri allImgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projectionCursor = {MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID};
        Cursor image_cursor = this.getContentResolver().query(allImgUri, projectionCursor, null, null, null);
        try {
            if (image_cursor != null) {
                image_cursor.moveToFirst();
            }
            do {
                ModelImage imageFolder = new ModelImage();
                String name = image_cursor.getString(image_cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                @SuppressLint("Range") String folder = image_cursor.getString(image_cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                @SuppressLint("Range") String datapath = image_cursor.getString(image_cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                String folderpaths = datapath.substring(0, datapath.lastIndexOf(folder + "/"));
                folderpaths = folderpaths + folder + "/";
                if (!imgesPaths.contains(folderpaths)) {
                    imgesPaths.add(folderpaths);

                    imageFolder.setImagePath(folderpaths);
                    imageFolder.setFolderName(folder)
                    ;
                    imageFolder.setFstPicture(datapath);
                    imageFolder.addpics();
                    imgFolders.add(imageFolder);

                } else {
                    for (int i = 0; i < imgFolders.size(); i++) {
                        if (imgFolders.get(i).getImagePath().equals(folderpaths)) {
                            imgFolders.get(i).setFstPicture(datapath);
                            imgFolders.get(i).addpics();
                        }
                    }
                }
            } while (image_cursor.moveToNext());
            image_cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < imgFolders.size(); i++) {
            Log.d("picture folders", imgFolders.get(i).getFolderName() + " and path = " + imgFolders.get(i).getImagePath() + " " + imgFolders.get(i).getNoPicturs());
        }
        return imgFolders;
    }
}
