package br.com.unime.qualidade.oficial2.entity;

import br.com.unime.qualidade.oficial2.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tuition {

    private Double amount;
    private String formattedAmount;
    private LocalDate dueDate;
    private Status status;

}
