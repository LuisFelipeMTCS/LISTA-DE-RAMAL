package lista.ramal.listaramal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_RAMAL",
uniqueConstraints = {
        @UniqueConstraint(name= Ramal.UK_AMIGO_MAIL, columnNames = "id" )
}
)
public class Ramal {
    public static final String UK_AMIGO_MAIL = "id";

    @SequenceGenerator(
        name="id",
        sequenceName = "id",
        allocationSize = 1
    )
    @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "id")

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "ramal", length = 200, nullable = false)
    private String numRamal;
    @Column(name = "nome", length = 200, nullable = false)
    private String nomeCompleto;
    @Column(name = "email", length = 300, unique = true)
    private String emailDepartamento;
    @Column(name = "responsavel", length = 300, unique = true)
    private String nomeResponsavel;
}


