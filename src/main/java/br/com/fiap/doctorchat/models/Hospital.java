package br.com.fiap.doctorchat.models;

import br.com.fiap.doctorchat.valueObjects.CNPJ;
import br.com.fiap.doctorchat.valueObjects.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Hospital")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeFantasia;
    private String razaoSocial;
    private Date dataDaAbertura;
    //@Embedded
    //private Endereco endereco;
    //@Embedded
    //private CNPJ CNPJ;
    private boolean ativo = true;

    @ManyToMany
    private List<Usuario> usuarios;
}
