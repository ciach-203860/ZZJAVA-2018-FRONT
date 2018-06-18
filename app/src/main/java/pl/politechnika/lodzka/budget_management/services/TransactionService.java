package pl.politechnika.lodzka.budget_management.services;

import java.util.List;
import java.util.Map;

import pl.politechnika.lodzka.budget_management.data.TransactionResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

/**
 * Created by Maciej on 2018-06-17.
 */

public interface TransactionService {
    @GET("/api/transaction/all")
    Call<List<TransactionResponse>> getTransactions(
            @HeaderMap Map<String, String> headers
    );
}
