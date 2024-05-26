package br.com.unime.qualidade.oficial2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Registration {

    private String id;
    private String courseName;
    private Tuition tuition;
    private Student student;

}
