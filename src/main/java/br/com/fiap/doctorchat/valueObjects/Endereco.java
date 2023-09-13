package br.com.fiap.doctorchat.valueObjects;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Embeddable
public class Endereco {
    @Column(length = 8)
    private int CEP;
    private String logradouro;
    @Column(length = 10)
    private int numero;
    private String bairro;
    private String complemento;
}
