package com.example.mycactusdisease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Dictionary;


public class MainActivity extends AppCompatActivity {
    ImageView mImageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_GALLERY = 1;

    Bitmap bitmap;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.imageView);

        Button captureButton = (Button) this.findViewById(R.id.btnCapture);
        captureButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 123);

            }

        });

        Button buttonIntent = (Button) findViewById(R.id.buttonIntent);
        buttonIntent.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent
                        , "Select Picture"),REQUEST_GALLERY);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = Media.getBitmap(this.getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageView imageView = (ImageView) findViewById(R.id.imageView2);
            imageView.setImageBitmap(bitmap);
        }

        else {
        Bundle extras = data.getExtras();
        Bitmap bitmap = (Bitmap) extras.get("data");
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        imageView.setImageBitmap(bitmap);}

    }


    }


