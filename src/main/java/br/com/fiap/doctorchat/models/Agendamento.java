package br.com.fiap.doctorchat.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Date Data;

    private String Descricao;

    @OneToMany(mappedBy = "Agendamento")
    private List<Medico> Medico;

    @OneToMany(mappedBy = "Agendamento")
    private List<Usuario> Usuario;
}
