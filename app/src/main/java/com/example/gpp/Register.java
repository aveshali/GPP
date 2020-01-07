package com.example.gpp;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
EditText fullname,email,password,phone;
Button regbutton;
FirebaseAuth fAuth;
ProgressBar progressBar;


public void LoginPage(View v){
    Intent intent = new Intent (this,Login.class);
    startActivity(intent);}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_register );

        fullname=findViewById ( R.id.Rname );
        email=findViewById ( R.id.Remail );
        password=findViewById ( R.id.Rpassword );
        phone=findViewById ( R.id.Rnumber );
        regbutton = findViewById ( R.id.SignUp );

        fAuth=FirebaseAuth.getInstance ();
        progressBar = findViewById ( R.id.progressBar );

        if (fAuth.getCurrentUser ()!=null){
            startActivity ( new Intent ( getApplicationContext (),HomePage.class ) );
            finish ();

        }

        regbutton.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                String mail=email.getText ().toString ().trim ();
                String pass=password.getText ().toString ().trim ();

                if(TextUtils.isEmpty ( mail )){
                    email.setError ( "Enter Email" );
                    return;
                }
                if(TextUtils.isEmpty ( pass )){
                    password.setError ( "Enter Password" );
                    return;
                }
                if(pass.length ()<6)
                {
                    password.setError ( "Password should be Six or more Character Long" );
                    return;
                }
                progressBar.setVisibility ( View.VISIBLE );
                {
                    //register the user in firebase
                    fAuth.createUserWithEmailAndPassword (mail,pass).addOnCompleteListener ( new OnCompleteListener<AuthResult> ( ) {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful ()){
                                Toast.makeText ( Register.this,"User Created",Toast.LENGTH_SHORT ).show ();
                                startActivity ( new Intent ( getApplicationContext (),HomePage.class ) );
                            }
                            else{
                                Toast.makeText ( Register.this,"Error!"+task.getException ().getMessage (),Toast.LENGTH_SHORT ).show ();
                            }
                        }
                    } );
                }
            }
        } );
    }
}
