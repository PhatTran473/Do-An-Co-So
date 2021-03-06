package com.example.congtimviecit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText mTextUsername1;
    EditText mTextPassword1;
    Button mButtonLogin;
    Button mButtonRegister;
    DatabaseHelper db;
    ViewGroup progressView;
    protected boolean isProgressShowing = false;
    CheckBox mCbShowPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       // Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar);
        //View v = this.getLayoutInflater().inflate(R.layout.progress_bar,null);
        //dialog.setContentView(dialog);
        //dialog.show();

        db = new DatabaseHelper(this);
        mTextUsername1 = (EditText)findViewById(R.id.edtLogin);
        mTextPassword1 = (EditText)findViewById(R.id.edtPass);
        mButtonLogin = (Button)findViewById(R.id.btnLogin);
        mButtonRegister = (Button) findViewById(R.id.btnRegis);
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,CreateNewUser.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername1.getText().toString().trim();
                String pwd = mTextPassword1.getText().toString().trim();
                //Check user
                Boolean res = db.checkUser(user, pwd);
                if(user.length() !=0 && pwd.length()!=0) {
                    if(res == true)
                    {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent HomePage = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(HomePage);

                    }
                else
                    {
                        Toast.makeText(LoginActivity.this, "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Mời bạn nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mTextPassword1 = (EditText) findViewById(R.id.edtPass);
        mCbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);

        mCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                    if (!isChecked) {
                    // show password
                    mTextPassword1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    mTextPassword1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    //public void showProgressingView() {

       // if (!isProgressShowing) {
        //    View view=findViewById(R.id.progressBar1);
        //    view.bringToFront();
     //   }
   // }

    public void hideProgressingView() {
        View v = this.findViewById(android.R.id.content).getRootView();
        ViewGroup viewGroup = (ViewGroup) v;
        viewGroup.removeView(progressView);
        isProgressShowing = false;
    }



}
