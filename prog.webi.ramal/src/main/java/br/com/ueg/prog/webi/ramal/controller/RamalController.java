package br.com.ueg.prog.webi.ramal.controller;

import br.com.ueg.prog.webi.ramal.dto.RamalDto;
import br.com.ueg.prog.webi.ramal.mapper.RamalMapper;
import br.com.ueg.prog.webi.ramal.model.RamalModel;
import br.com.ueg.prog.webi.ramal.service.RamalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = " ${app.api.base}")
public class RamalController {

    @Autowired
    private RamalService ramalService;

    @Autowired
    private RamalMapper ramalMapper;

    @PostMapping("/incluir")
    @Operation(description = "Método utilizado para realizar a inclusão de um ramal", responses = {
            @ApiResponse(responseCode = "200", description = "Ramal Incluído", content = @Content(mediaType = "application/json"))})
    public RamalDto incluir(@RequestBody RamalDto ramalDto) {
        RamalModel ramalModel = ramalMapper.toModelo(ramalDto);
        ramalService.incluir(ramalModel);
        return ramalMapper.toRamalDto(ramalModel);
    }

    @PutMapping(path = "/alterar/{id}")
    @Operation(description = "Método utilizado para alterar os dados de um ramal", responses = {
            @ApiResponse(responseCode = "200", description = "Ramal Alterado", content = @Content(mediaType = "application/json"))})
    public RamalDto alterar(@RequestBody RamalDto ramalDto, @PathVariable(name = "id") Long idRamal) {
        RamalModel ramalModel = ramalMapper.toModelo(ramalDto);
        ramalModel.setId(idRamal);
        RamalModel alterar = ramalService.alterar(ramalModel);
        return ramalMapper.toRamalDto(alterar);
    }

    @DeleteMapping(path = "/excluir/{id}")
    @Operation(description = "Método utilizado para remover uma ramal pelo id informado", responses = {
            @ApiResponse(responseCode = "200", description = "Ramal Removido", content = @Content(mediaType = "application/json"))})
    public RamalDto excluir(@PathVariable(name = "id") Long idRamal) {
        RamalModel ramalModel = ramalService.excluir(idRamal);
        return ramalMapper.toRamalDto(ramalModel);
    }

    @GetMapping(path = "/listar")
    @Operation(description = "Listagem Geral", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral", content = @Content(mediaType = "application/json"))})
    public List<RamalDto> listAll() {
        List<RamalModel> ramais = ramalService.listar();
        return ramalMapper.toListDto(ramais);
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "Obter os dados completos de um ramal pelo id informado!", responses = {
            @ApiResponse(responseCode = "200", description = "Entidade encontrada", content = @Content(mediaType = "application/json"))})
    public RamalDto ObterPorId(@PathVariable(name = "id") Long id){
        RamalModel ramalModel = this.ramalService.buscaRamalPorId(id);
        return this.ramalMapper.toRamalDto(ramalModel);
    }
}
