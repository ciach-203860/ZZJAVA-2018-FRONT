package pl.politechnika.lodzka.budget_management.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Maciej on 2018-06-18.
 */

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TransactionResponse {

    private Long id;

    private String description;

    private float value;

    private Date date;

    private String category;

    public static List<TransactionInfo> transactionResponsesToInfos(List<TransactionResponse> responses) {

        List<TransactionInfo> infos = new ArrayList<>();

        for (TransactionResponse response : responses) {

            java.sql.Date sqlDate = new java.sql.Date(response.getDate().getTime());

            TransactionInfo transactionInfo = TransactionInfo.builder()
                    .id(response.id)
                    .category(response.category)
                    .date(sqlDate)
                    .value(response.value)
                    .build();

            infos.add(transactionInfo);
        }

        return infos;
    }
}
