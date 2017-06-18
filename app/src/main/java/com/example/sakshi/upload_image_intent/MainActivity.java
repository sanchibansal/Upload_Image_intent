package com.example.sakshi.upload_image_intent;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

import static android.R.attr.start;

public class MainActivity extends AppCompatActivity {

    Button upload;                  //declaring components
    ImageView image;
    public static final int GET_FROM_GALLERY=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        upload=(Button)findViewById(R.id.upload);       //associating components
        image=(ImageView)findViewById(R.id.image);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {           //on click listener
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);       //creating intent
                i.setType("image/*");
                startActivityForResult(i,GET_FROM_GALLERY);         //starting activity through intent
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();            //code for returning to the activity after picture selection from gallery
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            image.setImageBitmap(bitmap);

        }
    }

}
