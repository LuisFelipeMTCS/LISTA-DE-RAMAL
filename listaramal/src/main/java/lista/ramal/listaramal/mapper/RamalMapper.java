package lista.ramal.listaramal.mapper;


import java.util.List;

import lista.ramal.listaramal.dto.RamalDTO;
import lista.ramal.listaramal.dto.RamalDadosAlteravelDTO;
import lista.ramal.listaramal.dto.RamalListaDTO;
import lista.ramal.listaramal.model.Ramal;
import org.mapstruct.Mapper;

// Uma camada de mapeadores que move dados entre objetos
//  e um banco de dados enquanto os mantém independentes uns dos outros.

@Mapper(componentModel = "spring")
public interface RamalMapper {

    public RamalListaDTO tDto(Ramal ramal);

    public List<RamalListaDTO> toDTO(List<Ramal> ramals);

    public RamalDadosAlteravelDTO toRamalIncluirDTO(Ramal ramal);

    public Ramal toModelo(RamalDadosAlteravelDTO ramal);

    public RamalDTO toRamalDTO(Ramal ramal);


    
}
