package br.com.ueg.prog.webi.ramal.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_RAMAL")
public class RamalModel {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;


    private Long ramal;
    private String nomeSetor;
    private String emailDepartamento;
    private String nomeResponsavelSetor;
}
