package pl.politechnika.lodzka.budget_management.services;

import pl.politechnika.lodzka.budget_management.data.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by waver on 17.06.2018.
 */

public interface AuthorizationService {
    @FormUrlEncoded
    @Headers({"Authorization: Basic ZnJvbnRlbmRDbGllbnRJZDphZG1pbg==", "Content-type: application/x-www-form-urlencoded"})
    @POST("oauth/token")
    Call<LoginResponse> login(@Field("grant_type") String grantType, @Field("username") String username,
                              @Field("password") String password);
}
