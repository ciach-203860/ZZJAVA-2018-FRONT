package pl.politechnika.lodzka.budget_management.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import pl.politechnika.lodzka.budget_management.R;
import pl.politechnika.lodzka.budget_management.data.TransactionBody;
import pl.politechnika.lodzka.budget_management.services.TransactionService;
import pl.politechnika.lodzka.budget_management.tools.ConnectionProvider;
import pl.politechnika.lodzka.budget_management.tools.ToastManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maciej on 2018-06-18.
 */

public class AddTransactionActivity extends AppCompatActivity {

    Spinner chooseCategory;
    Spinner chooseType;


    private static final String EXPENSES = "Wydatki";
    private static final String INCOMES = "Przychody";

    private static final String FOOD = "food";
    private static final String UTILITIES = "utilities";
    private static final String MEDICAL = "medical";
    private static final String CLOTHING = "clothing";
    private static final String TRANSPORTATION = "transportation";
    private static final String PERSONAL = "personal";
    private static final String TRANSFER = "transfer";

    private ConnectionProvider connectionProvider;
    Button saveButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        chooseCategory = findViewById(R.id.category);
        chooseType = findViewById(R.id.type);

        List<String> types = new ArrayList<String>();
        Collections.addAll(types, EXPENSES, INCOMES);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseType.setAdapter(typeAdapter);

        List<String> categories = new ArrayList<String>();
        Collections.addAll(categories, FOOD, UTILITIES, MEDICAL, CLOTHING, TRANSPORTATION, PERSONAL, TRANSFER);
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, categories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseCategory.setAdapter(categoriesAdapter);

        saveButton = findViewById(R.id.add_transaction);
        Button cancelButton = findViewById(R.id.cancel_transaction);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTransaction();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        connectionProvider = ConnectionProvider.getInstance();
    }

    private void cancel() {
        this.finish();
    }

    private void saveTransaction() {

        saveButton.setEnabled(false);

        String category = chooseCategory.getSelectedItem().toString();
        String description = ((EditText) findViewById(R.id.description)).getText().toString();


        Float value = Float.parseFloat(((EditText) findViewById(R.id.value)).getText().toString());
        String selectedType = chooseType.getSelectedItem().toString();

        if (EXPENSES.equals(selectedType)) {
            value = -value;
        }

        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

        TransactionBody transactionBody = TransactionBody.builder()
                .value(value)
                .date(dateString)
                .category(category)
                .description(description)
                .build();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", connectionProvider.getBearer());
        TransactionService transactionService = connectionProvider.getHttpClient().create(TransactionService.class);
        transactionService.addTransaction(transactionBody, headers).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                saveButton.setEnabled(true);
                if (response.isSuccessful()) {
                    ToastManager.ShowToast("Pomyślnie dodano transakcje", getApplicationContext());
                    AddTransactionActivity.this.finish();
                } else {
                    String msg = getString(R.string.dunno_exception);
                    ToastManager.ShowToast(msg, getApplicationContext());
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                saveButton.setEnabled(true);
                ToastManager.ShowToast(t.getMessage(), getApplicationContext());
            }
        });


    }
}
