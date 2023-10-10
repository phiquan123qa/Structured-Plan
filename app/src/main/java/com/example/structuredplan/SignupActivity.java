package com.example.structuredplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.structuredplan.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        databaseHelper = new DatabaseHelper(this);
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.signupUsername.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupPasswordConfirm.getText().toString();

                if(username.equals("")||password.equals("")||confirmPassword.equals("")){
                    Toast.makeText(SignupActivity.this, "All fields need input", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(confirmPassword)){
                        Boolean checkUsername = databaseHelper.checkUsername(username);
                        if(checkUsername == false){
                            Boolean insert = databaseHelper.insertData(username, password);
                            if(insert == true){
                                Toast.makeText(SignupActivity.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(SignupActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignupActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignupActivity.this, "Password and Confirm password not same", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.signinRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}