package lista.ramal.listaramal.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import lista.ramal.listaramal.dto.RamalDTO;
import lista.ramal.listaramal.dto.RamalDadosAlteravelDTO;
import lista.ramal.listaramal.dto.RamalListaDTO;
import lista.ramal.listaramal.model.Ramal;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-18T21:39:35-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230218-1114, environment: Java 17.0.6 (Eclipse Adoptium)"
)
@Component
public class RamalMapperImpl implements RamalMapper {

    @Override
    public RamalListaDTO tDto(Ramal ramal) {
        if ( ramal == null ) {
            return null;
        }

        RamalListaDTO ramalListaDTO = new RamalListaDTO();

        return ramalListaDTO;
    }

    @Override
    public List<RamalListaDTO> toDTO(List<Ramal> ramals) {
        if ( ramals == null ) {
            return null;
        }

        List<RamalListaDTO> list = new ArrayList<RamalListaDTO>( ramals.size() );
        for ( Ramal ramal : ramals ) {
            list.add( tDto( ramal ) );
        }

        return list;
    }

    @Override
    public RamalDadosAlteravelDTO toRamalIncluirDTO(Ramal ramal) {
        if ( ramal == null ) {
            return null;
        }

        RamalDadosAlteravelDTO ramalDadosAlteravelDTO = new RamalDadosAlteravelDTO();

        ramalDadosAlteravelDTO.setNomeResponsavel( ramal.getNomeResponsavel() );
        ramalDadosAlteravelDTO.setNumRamal( ramal.getNumRamal() );

        return ramalDadosAlteravelDTO;
    }

    @Override
    public Ramal toModelo(RamalDadosAlteravelDTO ramal) {
        if ( ramal == null ) {
            return null;
        }

        Ramal ramal1 = new Ramal();

        ramal1.setNomeResponsavel( ramal.getNomeResponsavel() );
        ramal1.setNumRamal( ramal.getNumRamal() );

        return ramal1;
    }

    @Override
    public RamalDTO toRamalDTO(Ramal ramal) {
        if ( ramal == null ) {
            return null;
        }

        RamalDTO ramalDTO = new RamalDTO();

        return ramalDTO;
    }
}
