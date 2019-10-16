package com.sbrotee63.donate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUp extends AppCompatActivity {

    String name;
    String email;
    String password;
    String confirmPassword;
    String bloodGroup;
    String dateOfBirth;
    String address;
    String cellNumber;
    String lastBloodDonation;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /*nameF = (EditText) findViewById(R.id.signup_text_name);
        emailF = (EditText) findViewById(R.id.signup_text_email);
        passwordF = (EditText) findViewById(R.id.signup_password_password);
        final EditText confirmPasswordF = (EditText) findViewById(R.id.signup_password_confirmpassword);
        final EditText bloodGroupF = (EditText) findViewById(R.id.signup_text_bloodgroup);
        final EditText dateOfBirthF = (EditText) findViewById(R.id.signup_text_dateofbirth);
        final EditText addressF = (EditText) findViewById(R.id.signup_text_address);
        final EditText cellNumberF = (EditText) findViewById(R.id.singup_text_cellnumber);
        final EditText lastBloodDonationF = (EditText) findViewById(R.id.signup_text_lastblooddonation);*/

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signup_button_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void signUp(){
        if(!allFieldChecked()) return;
        String email = ((EditText)findViewById(R.id.signup_text_email)).getText().toString().trim();
        String password = ((EditText)findViewById(R.id.signup_password_password)).getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener <AuthResult>()
        {
            public void onComplete(@NonNull Task <AuthResult> task)
            {
                if(task.isSuccessful()){
                    Log.d("DONATE+", "createUserWithEmail: successful");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(SignUp.this, "Sign up successful.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, NewsFeed.class);
                    startActivity(intent);

                }
                else{
                    Log.w("DONATE+", "createUserWithEmail: failure", task.getException());
                    Toast.makeText(SignUp.this, "Sign up failed. Please check all information.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean allFieldChecked(){
        Log.d("newTag", "Came");
        name = ((EditText)findViewById(R.id.signup_text_name)).getText().toString().trim();
        email = ((EditText)findViewById(R.id.signup_text_email)).getText().toString().trim();
        password = ((EditText)findViewById(R.id.signup_password_password)).getText().toString().trim();
        confirmPassword = ((EditText)findViewById(R.id.signup_password_confirmpassword)).getText().toString().trim();
        bloodGroup = ((EditText)findViewById(R.id.signup_text_bloodgroup)).getText().toString().trim();
        dateOfBirth = ((EditText)findViewById(R.id.signup_text_dateofbirth)).getText().toString().trim();
        address = ((EditText)findViewById(R.id.signup_text_address)).getText().toString().trim();
        cellNumber = ((EditText)findViewById(R.id.singup_text_cellnumber)).getText().toString().trim();
        lastBloodDonation = ((EditText)findViewById(R.id.signup_text_lastblooddonation)).getText().toString().trim();

        if(name.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("") || bloodGroup.equals("") || dateOfBirth.equals("") || address.equals("") || cellNumber.equals("") || lastBloodDonation.equals("")){
            Toast.makeText(SignUp.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!confirmPassword.equals(password)){
            Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /*
    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
     */

}