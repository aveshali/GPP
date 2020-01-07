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

public class Login extends AppCompatActivity {
    EditText email,password;
    Button logbutton;
    FirebaseAuth fAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );
        email=findViewById ( R.id.Lmail );
        password=findViewById ( R.id.Lpass );
        logbutton=findViewById ( R.id.Lbtn );
        progressBar=findViewById ( R.id.Lprogressbar );
        fAuth=FirebaseAuth.getInstance ();
        logbutton.setOnClickListener ( new View.OnClickListener ( ) {
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
                    fAuth.signInWithEmailAndPassword ( mail,pass ).addOnCompleteListener ( new OnCompleteListener<AuthResult> ( ) {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful ()){
                                Toast.makeText ( Login.this,"Logged In successfully",Toast.LENGTH_SHORT ).show ();
                                startActivity ( new Intent ( getApplicationContext (),HomePage.class ) );
                            }
                            else{
                                Toast.makeText ( Login.this,"Error!"+task.getException ().getMessage (),Toast.LENGTH_SHORT ).show ();
                            }
                        }
                    } );

                }
            }
        } );
    }

    public void RegPage(View view) {
        Intent intent = new Intent (this,Register.class);
        startActivity(intent);
    }
}
