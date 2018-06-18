package pl.politechnika.lodzka.budget_management.tools;

import pl.politechnika.lodzka.budget_management.data.User;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maciej on 2018-06-17.
 */

public class ConnectionProvider {
    private static final ConnectionProvider ourInstance = new ConnectionProvider();

    private static final String BASE_URL = "http://10.0.2.2:8080/";

    private Retrofit httpClient;
    private String bearer;
    private User user;


    public static ConnectionProvider getInstance() {
        return ourInstance;
    }

    private ConnectionProvider() {
        this.httpClient = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public Retrofit getHttpClient() {
        return httpClient;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
