package pl.politechnika.lodzka.budget_management.services;

import okhttp3.ResponseBody;
import pl.politechnika.lodzka.budget_management.data.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by waver on 17.06.2018.
 */

public interface RegisterService {
    @POST("api/register")
    Call<ResponseBody> register(@Body User user);
}
