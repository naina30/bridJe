package com.example.bridje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    //Declare fields
    private EditText emailaddress;
    private EditText enrno;
    private EditText dob;
    private EditText password;
    private Button signupbtn;
    private ProgressDialog progressDialog;

    //Firebase Authentication ids
   private FirebaseAuth mAuth;
   private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Initialising the progress bar object
        progressDialog= new ProgressDialog(this);
        //Assign ids
        emailaddress=(findViewById(R.id.email));
        enrno=(findViewById(R.id.enr));
        dob=(findViewById(R.id.dob));
        password=(findViewById(R.id.pass));
        signupbtn=(findViewById(R.id.sign_up));


        //Firebase instance
        mAuth= FirebaseAuth.getInstance();
       /* if(mAuth.getCurrentUser()==null)
        {
            finish();
            startActivity();
        } */
        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               //Check user
            }
        };

        //Create an onClickListener on the button and the text view otherwise it won't work
        signupbtn.setOnClickListener(this);


    }

    //generating onStart overriding method in order to enter custom fields i.e fields other than email and password while signing up
    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null)
        {
            //handle the already singed Up user
        }
    }

    //Creating a method for signing up logic
    private void createUserAccount() {
    final String  passuser, emailuser, enrUser,dobuser;

    enrUser= enrno.getText().toString().trim();
    passuser=password.getText().toString().trim();
    emailuser=emailaddress.getText().toString().trim();
    dobuser=dob.getText().toString().trim();
    if(enrUser.isEmpty())
    {
        enrno.setError("Enter a valid enrollment");
        enrno.requestFocus(); //requesting focus to show the error
        return; //stopping the execution if validity parameters are not fulfilled
    }
     if(passuser.isEmpty())
     {
      password.setError("Password Required");
         password.requestFocus();
         return;
     }
     if(passuser.length()<6) // The minimum length of password required for Firebase authentication is 6 so, there should be a condition to check the same
     {
         password.setError("Minimum length of password is 6");
         password.requestFocus();
         return;
     }
     if(emailuser.isEmpty()) {
         emailaddress.setError("Enter a valid college name");
         emailaddress.requestFocus();
         return;
         }
     if(dobuser.isEmpty()){
        dob.setError("Date of birth required");
         dob.requestFocus();
         return;
     }
     //If validations are fine
        progressDialog.setMessage("Registering user, please wait");
        progressDialog.show();


        mAuth.createUserWithEmailAndPassword(emailuser, passuser).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
             if(task.isSuccessful()) // user is successfully registered and logged in
             {
                 // we will store the additional fields in firebase database
                 User user= new User( enrUser, dobuser, emailuser);
                 FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         if(task.isSuccessful())
                         {
                             progressDialog.hide();
                             Intent intent=new Intent(SignUpActivity.this, Home_Activity.class);
                             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //So that the user does not come back to the log in activity after pressing the back button
                             startActivity(intent);

                         }
                         else
                             //display a failure message
                         Toast.makeText(SignUpActivity.this, getString(R.string.registrationunsuccesful),Toast.LENGTH_LONG).show();
                     }
                 });

             }
             else
             {
                 if(task.getException() instanceof FirebaseAuthUserCollisionException) //email is already registered
                 {
                     Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_LONG).show();
                 }
                 else
                 {
                     Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                 }
             }
         }
     });

    }

    @Override
    public void onClick(View view) {
        createUserAccount();

    }
}
