package lista.ramal.listaramal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import lista.ramal.listaramal.model.Ramal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface RamalRepository extends JpaRepository<Ramal, Long> {

    Optional<Ramal> findRamalByemailDepartamento(String emailDepartamento);

    @Query(value = "select count(a) from ListaRamal a where a.emailDepartamento=:paramMail")
    Integer countUtilizacaoEmailDepartamento(String paramMail);
    
}
