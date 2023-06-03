package br.com.ueg.prog.webi.ramal.repository;

import br.com.ueg.prog.webi.ramal.model.RamalModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RamalRepository extends JpaRepository<RamalModel, Long> {
}
