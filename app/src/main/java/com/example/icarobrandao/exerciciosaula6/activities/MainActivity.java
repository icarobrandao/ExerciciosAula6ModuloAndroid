package com.example.icarobrandao.exerciciosaula6.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.camera.CropImageIntentBuilder;
import com.example.icarobrandao.exerciciosaula6.R;
import com.example.icarobrandao.exerciciosaula6.adapters.UserListAdapter;
import com.example.icarobrandao.exerciciosaula6.models.User;
import com.example.icarobrandao.exerciciosaula6.utils.Constants;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by icarobrandao on 20/05/16.
 */
public class MainActivity extends AppCompatActivity{
    @BindView(R.id.user_image)
    ImageView user_image;

    @BindView(R.id.user_name)
    EditText user_name;
    @BindView(R.id.user_age)
    EditText user_age;

    @BindView(R.id.btn_user_list)
    Button btn_user_list;
    @BindView(R.id.btn_user_sing_up)
    Button btn_user_sing_up;

    User user = new User();
    List<User> userlist = new ArrayList<>();

    private static final int REQUEST_CODE_CAMERA =123;
    private static final int REQUEST_CODE_CROP = 456;
    private Uri originalImageURI;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        user_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File originalImage = new File(getExternalCacheDir(), "originalImage");
                originalImageURI = Uri.fromFile(originalImage);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, originalImageURI);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

        btn_user_sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setName(user_name.getText().toString());
                user.setAge(user_age.getText().toString());
                //user.setImage(user_image.);

                userlist.add(user);

                Intent i = new Intent();
                i.putExtra(Constants.EXTRA_FOR_B, (Serializable) userlist);
                setResult(RESULT_OK,i);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File croppedImage = new File(getFilesDir(),"croppedImage.jpg");
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK){
            Uri croppedImageURI = Uri.fromFile(croppedImage);
            CropImageIntentBuilder cropImageIntentBuilder = new CropImageIntentBuilder(200,200, croppedImageURI);
            cropImageIntentBuilder.setOutlineCircleColor(0xFF03A9F4);
            cropImageIntentBuilder.setSourceImage(originalImageURI);
            startActivityForResult(cropImageIntentBuilder.getIntent(MainActivity.this),REQUEST_CODE_CROP);
        }else if (requestCode == REQUEST_CODE_CROP && resultCode == RESULT_OK){
            user_image.setImageBitmap(BitmapFactory.decodeFile(croppedImage.getAbsolutePath()));
        }



    }



}
