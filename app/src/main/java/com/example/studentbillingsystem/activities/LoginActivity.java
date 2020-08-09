package com.example.studentbillingsystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentbillingsystem.R;
import com.example.studentbillingsystem.helpers.AppActivity;
import com.example.studentbillingsystem.helpers.ShowToast;
import com.example.studentbillingsystem.models.Login;
import com.example.studentbillingsystem.presenters.LoginPresenter;
import com.example.studentbillingsystem.utils.Utilities;
import com.example.studentbillingsystem.utils.UtilitiesFunctions;
import com.google.gson.GsonBuilder;

public class LoginActivity extends AppActivity implements View.OnClickListener,LoginPresenter.View  {
    private Button login_btn;
    private EditText username;
    private EditText password;
    private EditText granttype;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializedView();
        initializedListners();
        checkIfUserIsLogin();
    }

    private void checkIfUserIsLogin() {
        if(Utilities.isLogin())
        {
            afterLoginSuccess();
        }
    }
    @Override
    protected void initializedView() {
        login_btn=findViewById(R.id.btn_login);
        username=findViewById(R.id.get_username);
        password=findViewById(R.id.get_password);
        granttype=findViewById(R.id.grant_type);

    }

    @Override
    protected void initializedListners() {
        loginPresenter =new LoginPresenter(this);
        login_btn.setOnClickListener(this);

    }
    private void afterLoginSuccess(){
        startActivity(new Intent(LoginActivity.this, Dashboard.class));
        LoginActivity.this.finish();
    }

    private void dologinaftersucess() {
        //main activity hunu parxa hia yeta
        startActivity(new Intent(LoginActivity.this, Dashboard.class));
        LoginActivity.this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
//                dologinaftersucess();
                doLoginWork(granttype,username,password);

        }
    }
    private void doLoginWork( EditText granttype,EditText email, EditText password) {
        if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
            Toast.makeText(this, "All Filed ARE Mandatoru", Toast.LENGTH_SHORT).show();
        }
        else {
//            if (Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
                if(UtilitiesFunctions.isNetworkAvailable(LoginActivity.this)){


                    loginPresenter.userLogin(granttype.getText().toString(),email.getText().toString(), password.getText().toString());
                }
                  else {
                    ShowToast.withMessage("Please Connect to Internet");
                }
//  else{
//                Toast.makeText(this, "Valid email is required", Toast.LENGTH_SHORT).show();
//            }
//            }


        }
    }

    @Override
    public void onUserLoginSuccess(Login login) {
        dologinaftersucess();
        ShowToast.withMessage("Successfully Logging");


    }

    @Override
    public void onFailure(String message) {
        Log.e( "onFailure: ", message);
//        Log.e(TAG, "onFailure: ", new GsonBuilder().create().toJson(message));

        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();

    }
}
