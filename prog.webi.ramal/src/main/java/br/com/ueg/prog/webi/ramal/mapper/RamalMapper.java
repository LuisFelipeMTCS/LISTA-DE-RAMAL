package br.com.ueg.prog.webi.ramal.mapper;

import br.com.ueg.prog.webi.ramal.dto.RamalDto;
import br.com.ueg.prog.webi.ramal.model.RamalModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RamalMapper {

    RamalModel toModelo(RamalDto ramalDto);

    RamalDto toRamalDto(RamalModel ramalModel);

    List<RamalDto> toListDto(List<RamalModel> ramalModels);
}
