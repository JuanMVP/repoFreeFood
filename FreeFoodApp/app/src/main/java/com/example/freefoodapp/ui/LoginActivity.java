package com.example.freefoodapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.freefoodapp.R;
import com.example.freefoodapp.responses.AuthAndRegisterResponse;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.AuthAndRegisterService;
import com.example.freefoodapp.util.Util;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button btnLogin;
    TextView textoRegistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        textoRegistro = findViewById(R.id.goRegisterText);

        textoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Iniciando Sesión...");
                progressDialog.show();

                String emailUser = email.getText().toString().trim();
                String passwordUser = password.getText().toString().trim();

                String credentialsUserLogin = Credentials.basic(emailUser,passwordUser);

                AuthAndRegisterService loginService = ServiceGenerator.createService(AuthAndRegisterService.class);

                final Call<AuthAndRegisterResponse> callLogin = loginService.login(credentialsUserLogin);
                callLogin.enqueue(new Callback<AuthAndRegisterResponse>() {
                    @Override
                    public void onResponse(Call<AuthAndRegisterResponse> call, final Response<AuthAndRegisterResponse> response) {
                        if(response.isSuccessful()){
                            Runnable progressRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.cancel();
                                    onLoginSucces(callLogin,response);
                                }
                            };

                            Handler dialogCancell = new Handler();
                            dialogCancell.postDelayed(progressRunnable,2000);

                        }else{
                            progressDialog.cancel();
                            onLoginFail();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthAndRegisterResponse> call, Throwable t) {
                        Log.i("TAG", "Error al iniciar sesión");

                    }
                });



            }
        });


    }



    public void onLoginSucces(Call<AuthAndRegisterResponse> call, Response<AuthAndRegisterResponse> response){

        /*Util.setData(LoginActivity.this, response.body().getToken(), response.body().getUser().getId(),
                response.body().getUser().getEmail(), response.body().getUser().getName(),response.body().getUser().getPhotoUser().get(0));*/

        startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
        finish();

    }


    public void onLoginFail(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);

        dialogBuilder.setIcon(R.drawable.ic_cancel);

        dialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogBuilder.setMessage(R.string.login_error).setTitle(R.string.error);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

}
