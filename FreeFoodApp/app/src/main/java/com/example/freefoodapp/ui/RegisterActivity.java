package com.example.freefoodapp.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.freefoodapp.R;
import com.example.freefoodapp.models.Register;
import com.example.freefoodapp.responses.AuthAndRegisterResponse;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.AuthAndRegisterService;
import com.example.freefoodapp.util.Util;
import com.example.freefoodapp.util.UtilToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText nombre,email,password;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nombre = findViewById(R.id.nombreRegistro);
        email = findViewById(R.id.emailRegistro);
        password = findViewById(R.id.passwordRegistro);
        btnRegister = findViewById(R.id.buttonRegistro);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String name = nombre.getText().toString().trim();


                Register registro = new Register(name,correo,pass);


                AuthAndRegisterService service = ServiceGenerator.createService(AuthAndRegisterService.class);
                Call<AuthAndRegisterResponse> registerResponseCall = service.register(registro);

                registerResponseCall.enqueue(new Callback<AuthAndRegisterResponse>() {
                    @Override
                    public void onResponse(Call<AuthAndRegisterResponse> call, Response<AuthAndRegisterResponse> response) {
                        if(response.isSuccessful()){
                            UtilToken.setToken(RegisterActivity.this,response.body().getToken());
                            startActivity(new Intent(RegisterActivity.this,DashboardActivity.class));
                            Toast.makeText(RegisterActivity.this, "Usuario registrado y logueado con éxito", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Error en el Registro, Revise los datos", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthAndRegisterResponse> call, Throwable t) {
                        Log.e("NetworkFailure", t.getMessage());
                        Toast.makeText(RegisterActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }


    public void onRegisterSuccess(Call<AuthAndRegisterResponse> call, Response<AuthAndRegisterResponse> response){
        Util.setData(RegisterActivity.this,response.body().getToken(),response.body().getUser().getId(),
                response.body().getUser().getEmail(),response.body().getUser().getName(),response.body().getUser().getPhotoUser());

        startActivity(new Intent(RegisterActivity.this,DashboardActivity.class));
    }

    public void onRegisterFail(int tipoError){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(RegisterActivity.this);
        dialogBuilder.setIcon(R.drawable.ic_cancel);
        dialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogBuilder.setMessage(tipoError).setTitle(R.string.error);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }


}
