package br.com.fiap.doctorchat.models;


import br.com.fiap.doctorchat.valueObjects.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Usuário")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    //@Embedded
    //private CPF CPF;
    private String email;
    private String senha;
    private Date dataDeNascimento;
    //@Embedded
    //private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "Agendamento_Id")
    private Agendamento Agendamento;
}
