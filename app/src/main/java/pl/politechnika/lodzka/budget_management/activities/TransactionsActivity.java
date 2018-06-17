package pl.politechnika.lodzka.budget_management.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import pl.politechnika.lodzka.budget_management.R;
import pl.politechnika.lodzka.budget_management.data.User;

public class TransactionsActivity extends AppCompatActivity {
    private String bearer;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        Intent intent = getIntent();
        bearer = intent.getStringExtra("BEARER");
        user = intent.getParcelableExtra("USER");
        TextView textView = findViewById(R.id.greetingLabel);
        textView.setText(getString(R.string.greeting, user.getUsername()));
    }
}
