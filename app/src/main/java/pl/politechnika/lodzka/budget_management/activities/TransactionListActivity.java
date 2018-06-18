package pl.politechnika.lodzka.budget_management.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.politechnika.lodzka.budget_management.R;
import pl.politechnika.lodzka.budget_management.adapter.TransactionAdapter;
import pl.politechnika.lodzka.budget_management.data.TransactionInfo;
import pl.politechnika.lodzka.budget_management.data.TransactionResponse;
import pl.politechnika.lodzka.budget_management.data.User;
import pl.politechnika.lodzka.budget_management.services.TransactionService;
import pl.politechnika.lodzka.budget_management.tools.ConnectionProvider;
import pl.politechnika.lodzka.budget_management.tools.ToastManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionListActivity extends AppCompatActivity {
    private ConnectionProvider connectionProvider;
    private String bearer;
    private User user;

    private RecyclerView mRecyclerView;
    private List<TransactionInfo> mTransactions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        mRecyclerView = findViewById(R.id.transactions);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new TransactionAdapter(mTransactions));
        connectionProvider = ConnectionProvider.getInstance();
        bearer = connectionProvider.getBearer();
        user = connectionProvider.getUser();
        getTransactions();
    }

    private void getTransactions() {
        TransactionService transactionService = connectionProvider.getHttpClient().create(TransactionService.class);

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", bearer);

        transactionService.getTransactions(headers).enqueue(new Callback<List<TransactionResponse>>() {
            @Override
            public void onResponse(Call<List<TransactionResponse>> call, Response<List<TransactionResponse>> response) {
                mTransactions = TransactionResponse.transactionResponsesToInfos(response.body());
                mRecyclerView.setAdapter(new TransactionAdapter(mTransactions));
            }

            @Override
            public void onFailure(Call<List<TransactionResponse>> call, Throwable t) {
                ToastManager.ShowToast(t.getMessage(), getApplicationContext());
            }
        });
    }
}
