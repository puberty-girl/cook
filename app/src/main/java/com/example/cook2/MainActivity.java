package com.example.cook2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.cook2.Models.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    Button btnSignIn, btnRegister;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    RelativeLayout root;

    private FirebaseAuth mAuth;

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File file = new File(this.getFilesDir(), "testfiles.txt");
        if (file.exists()) {
            new Intent(MainActivity.this, MapActivity.class);
        }
        ;
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnRegister = findViewById(R.id.btnRegister);

        root = findViewById(R.id.root_element);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWindow();

            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignInWindow();

            }
        });


        mCallbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
        LoginButton loginButton = findViewById(R.id.btnSignFace);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });

    }
    CallbackManager mCallbackManager;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void showSignInWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Введите все данные для входа");

        LayoutInflater inflater = LayoutInflater.from(this);
        View sign_in_window = inflater.inflate(R.layout.sign_in_window, null);
        dialog.setView(sign_in_window);

        final MaterialEditText email = sign_in_window.findViewById(R.id.emailField);
        final MaterialEditText pass = sign_in_window.findViewById(R.id.passField);


        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if (TextUtils.isEmpty(email.getText().toString())) {
                            Snackbar.make(root, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        if (pass.getText().toString().length() < 5) {
                            Snackbar.make(root, "Введите пароль (более 5 символов)", Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        auth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        //Создаем файл
                                        try {
                                            File file = new File("testfiles.txt");
                                            file.createNewFile();
                                           // PrintWriter writer = new PrintWriter("testfiles.txt");
                                           // writer.printf("%x", 255); //Записываем текст в файл
                                            //writer.close(); // Закрываем файл
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        startActivity(new Intent(MainActivity.this, MapActivity.class));
                                      //finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(root, "Оштбка авторизации" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
        dialog.show();
    }
    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегестрироваться");
        dialog.setMessage("Введите все данные для регестрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register_window, null);
        dialog.setView(register_window);

        final MaterialEditText email = register_window.findViewById(R.id.emailField);
        final MaterialEditText pass = register_window.findViewById(R.id.passField);
        final MaterialEditText name = register_window.findViewById(R.id.nameField);
        final MaterialEditText phone = register_window.findViewById(R.id.phoneField);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root, "Введите вашу почту",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(root, "Введите ваше имя",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone.getText().toString())){
                    Snackbar.make(root, "Введите ваш телефон",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (pass.getText().toString().length() < 5){
                    Snackbar.make(root, "Введите пароль (более 5 символов)",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                //регистрация пользователя
                auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User();
                                user.setEmail(email.getText().toString());
                                user.setName(name.getText().toString());
                                user.setPass(pass.getText().toString());
                                user.setPhone(phone.getText().toString());

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Snackbar.make(root, "Пользователь добавлен!", Snackbar.LENGTH_SHORT).show();

                                    }
                                });
                            }
                        });

            }
        });
        dialog.show();
    }

    private void updateUI(FirebaseUser user) {
        if(user == null){
            Toast.makeText(this, "Вход не выполнен", Toast.LENGTH_SHORT).show();
        }
        else {
            startActivity( new Intent(MainActivity.this, MapActivity.class));
        }
    }

}