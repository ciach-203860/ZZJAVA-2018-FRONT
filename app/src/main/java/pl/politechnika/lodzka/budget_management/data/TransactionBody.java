package pl.politechnika.lodzka.budget_management.data;

import java.sql.Date;

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
public class TransactionBody {

    private Long id;

    private String description;

    private float value;

    private String date;

    private String category;
}
