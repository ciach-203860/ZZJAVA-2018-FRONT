package pl.politechnika.lodzka.budget_management.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pl.politechnika.lodzka.budget_management.R;
import pl.politechnika.lodzka.budget_management.data.LoginResponse;
import pl.politechnika.lodzka.budget_management.data.User;
import pl.politechnika.lodzka.budget_management.services.AuthorizationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit httpClient;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpClient = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        context = getApplicationContext();
    }


    public void login(View view) {
        AuthorizationService loginService = httpClient.create(AuthorizationService.class);
        EditText usernameEdit = findViewById(R.id.username);
        EditText passwordEdit = findViewById(R.id.password);
        final User user = User.builder().username(usernameEdit.getText().toString())
                .password(passwordEdit.getText().toString()).build();



        loginService.login("password", user.getUsername(), user.getPassword())
                .enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    String bearer = response.body().getBearer();
                    Toast.makeText(context, bearer, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, TransactionsActivity.class);
                    intent.putExtra("BEARER", bearer);
                    intent.putExtra("USER", user);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void register(View view) {
    }
}
