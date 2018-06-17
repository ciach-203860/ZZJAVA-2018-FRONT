package pl.politechnika.lodzka.budget_management.data;

import com.google.gson.annotations.SerializedName;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by waver on 17.06.2018.
 */

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LoginResponse {
    @SerializedName("access_token")
    private String accessToken;

    public String getBearer() {

        return "Bearer " + (accessToken != null ? accessToken : "empty");
    }
}
