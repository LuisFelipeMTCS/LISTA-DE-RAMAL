package br.com.ueg.prog.webi.ramal.service;

import br.com.ueg.prog.webi.ramal.dto.RamalDto;
import br.com.ueg.prog.webi.ramal.model.RamalModel;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface RamalService {

    void incluir(RamalModel ramalModel);

    RamalModel alterar(RamalModel ramalModel);

    RamalModel excluir(Long idRamal);

    List<RamalModel> listar();

    RamalModel buscaRamalPorId(Long idRamal);
}
