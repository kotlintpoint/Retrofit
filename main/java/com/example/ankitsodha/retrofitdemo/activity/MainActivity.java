package com.example.ankitsodha.retrofitdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ankitsodha.retrofitdemo.R;
import com.example.ankitsodha.retrofitdemo.adapter.MyDataAdapter;
import com.example.ankitsodha.retrofitdemo.model.User;
import com.example.ankitsodha.retrofitdemo.model.UserResponse;
import com.example.ankitsodha.retrofitdemo.rest.ApiClient;
import com.example.ankitsodha.retrofitdemo.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String SELECT = "select";
    RecyclerView recyclerView;
    Button btnInsertUser;
    EditText etUsername, etPassword;

    ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertUser=(Button)findViewById(R.id.btnInsertUser);
        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        
        btnInsertUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                InsertDummyUser(name,password);
            }
        });
        
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiService = ApiClient.getClient().create(ApiInterface.class);

        fetchUsers();
    }

    private void fetchUsers() {
        Call<UserResponse> call = apiService.getAllUser(SELECT);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                List<User> users = response.body().getData();
                Log.d(TAG, "Number of Users received: " + users.size());
                recyclerView.setAdapter(new MyDataAdapter(users, R.layout.list_item_user, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<UserResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    private void InsertDummyUser(String username, String password) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        
        Call<String> call=apiService.insertUser(username,password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String resp=response.body().toString();
                Toast.makeText(MainActivity.this, resp, Toast.LENGTH_SHORT).show();
                fetchUsers();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
