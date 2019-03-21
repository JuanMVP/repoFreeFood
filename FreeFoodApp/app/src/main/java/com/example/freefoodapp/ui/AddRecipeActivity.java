package com.example.freefoodapp.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.freefoodapp.R;
import com.example.freefoodapp.models.Recipe;
import com.example.freefoodapp.models.TipoAutenticacion;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.RecetaService;
import com.example.freefoodapp.util.UtilToken;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRecipeActivity extends AppCompatActivity {

    private String token;
    private  EditText addName, addIngredients, addDescription, addDinnerGuest;
    private Button btnCrear, btnFoto;
    private ImageView addImagenReceta;
    private Uri uriSelected;
    private static final int READ_REQUEST_CODE = 7;
    private Context ctx;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        token = UtilToken.getToken(AddRecipeActivity.this);
        addName = findViewById(R.id.nombreEditReceta);
        addIngredients = findViewById(R.id.ingredientesEditReceta);
        addDescription = findViewById(R.id.descripcionEditReceta);
        addDinnerGuest = findViewById(R.id.comensalesEditReceta);
        addImagenReceta = findViewById(R.id.imagenAddReceta);
        btnCrear = findViewById(R.id.buttonAddReceta);
        btnFoto = findViewById(R.id.btnAddFotoReceta);

        ctx = AddRecipeActivity.this;

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFileSearch();
            }
        });






        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Recipe addRecipe = new Recipe(
                       addName.getText().toString(),
                       addIngredients.getText().toString(),
                       addDescription.getText().toString(),
                       addDinnerGuest.getText().toString()
               );

               if(uriSelected != null){

                   try{

                       InputStream inputStream = ctx.getContentResolver().openInputStream(uriSelected);
                       ByteArrayOutputStream baos = new ByteArrayOutputStream();
                       BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                       int cantBytes;
                       byte[] buffer = new byte[1024*4];

                       while ((cantBytes = bufferedInputStream.read(buffer,0,1024*4)) != -1) {
                           baos.write(buffer,0,cantBytes);
                       }


                       RequestBody requestFile =
                               RequestBody.create(
                                       MediaType.parse(ctx.getContentResolver().getType(uriSelected)), baos.toByteArray());


                       MultipartBody.Part body =
                               MultipartBody.Part.createFormData("picture", "picture", requestFile);


                       RequestBody name = RequestBody.create(MultipartBody.FORM, addName.getText().toString().trim());
                       RequestBody ingredients = RequestBody.create(MultipartBody.FORM, addIngredients.getText().toString().trim());
                       RequestBody description = RequestBody.create(MultipartBody.FORM, addDescription.getText().toString().trim());
                       RequestBody dinnerGuest = RequestBody.create(MultipartBody.FORM, addDinnerGuest.getText().toString().trim());





                       RecetaService service = ServiceGenerator.createService(RecetaService.class, token, TipoAutenticacion.JWT);
                       Call<Recipe> call =  service.addReceta(body,name,ingredients,description,dinnerGuest);

                       call.enqueue(new Callback<Recipe>() {
                           @Override
                           public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                               if(response.isSuccessful()){
                                   Log.d("Uploaded", "Éxito");
                                   Log.d("Uploaded", response.body().toString());
                                   Toast.makeText(AddRecipeActivity.this, "Receta creada!", Toast.LENGTH_SHORT).show();
                                   finish();
                               }else{
                                   Log.e("Upload error", response.errorBody().toString());

                                   Toast.makeText(AddRecipeActivity.this, "Fallo al crear la receta", Toast.LENGTH_SHORT).show();

                               }
                           }

                           @Override
                           public void onFailure(Call<Recipe> call, Throwable t) {
                               Log.e("NetworkFailure", t.getMessage());
                               Toast.makeText(AddRecipeActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

                           }
                       });

                   }catch (FileNotFoundException e) {
                       e.printStackTrace();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }





               }











            }
        });

    }


    public void performFileSearch(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("Filechooser URI", "Uri: " + uri.toString());
                //showImage(uri);
                Glide
                        .with(this)
                        .load(uri)
                        .into(addImagenReceta);
                uriSelected = uri;
                btnCrear.setEnabled(true);
            }
        }
    }





}
