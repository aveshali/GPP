package com.example.gpp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import java.lang.Comparable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class HomePage extends AppCompatActivity {
   private Button button;
   private Button camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_home_page );
   camera=findViewById(R.id.button);
   camera.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           try{
               Intent intent=new Intent();
               intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivity(intent);
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
   }) ;
    }



    public void logout(View view) {
        FirebaseAuth.getInstance ().signOut ();//logout
        startActivity ( new Intent ( getApplicationContext (),Login.class ) );
        finish ();
    }
}


