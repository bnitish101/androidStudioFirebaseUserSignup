package com.example.androidstudiofirebaseusersignup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView ivBrowse;
    EditText etRollNo, etName, etCourse, etMobile;
    Button btnBrowse, btnSignup;
    Uri filePath;
    Bitmap bitmap;

    ActivityResultLauncher<String>  mGetContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBrowse = (ImageView) findViewById(R.id.ivBrowse);
        etRollNo = (EditText) findViewById(R.id.etRollNo);
        etName = (EditText) findViewById(R.id.etName);
        etCourse = (EditText) findViewById(R.id.etCourse);
        etMobile = (EditText) findViewById(R.id.etMobile);

        btnBrowse = (Button) findViewById(R.id.btnBrowse);
        btnSignup = (Button) findViewById(R.id.btnSignup);

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(MainActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                // Intent intent = new Intent(Intent.ACTION_PICK);
                                // intent.setType("image/*");
                                // startActivityForResult(Intent.createChooser(intent, "Select Image File"), 1);
                                mGetContent.launch("image/*");
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSignupData();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RESULT_OK){
            filePath =data.getData();
            try {
                InputStream inputStream =getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                ivBrowse.setImageBitmap(bitmap);
            } catch (Exception e){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveSignupData() {

        etRollNo = (EditText) findViewById(R.id.etRollNo);
        etName = (EditText) findViewById(R.id.etName);
        etCourse = (EditText) findViewById(R.id.etCourse);
        etMobile = (EditText) findViewById(R.id.etMobile);

    }
}