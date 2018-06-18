package pl.politechnika.lodzka.budget_management.services;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import pl.politechnika.lodzka.budget_management.data.TransactionBody;
import pl.politechnika.lodzka.budget_management.data.TransactionInfo;
import pl.politechnika.lodzka.budget_management.data.TransactionResponse;
import pl.politechnika.lodzka.budget_management.data.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

/**
 * Created by Maciej on 2018-06-17.
 */

public interface TransactionService {
    @GET("/api/transaction/all")
    Call<List<TransactionResponse>> getTransactions(
            @HeaderMap Map<String, String> headers
    );

    @POST("/api/transaction/add")
    Call<ResponseBody> addTransaction(@Body TransactionBody transactionBody, @HeaderMap Map<String, String> headers);
}
