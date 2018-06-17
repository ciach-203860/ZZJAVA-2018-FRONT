package pl.politechnika.lodzka.budget_management.data;

import lombok.*;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TransactionInfo {
    private Long id;

    private String description;

    private float value;

    private Date date;

    private String category;
}
