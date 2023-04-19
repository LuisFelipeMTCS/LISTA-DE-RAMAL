package lista.ramal.listaramal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lista.ramal.listaramal.dto.RamalDTO;
import lista.ramal.listaramal.dto.RamalDadosAlteravelDTO;
import lista.ramal.listaramal.dto.RamalListaDTO;
import lista.ramal.listaramal.mapper.RamalMapper;
import lista.ramal.listaramal.model.Ramal;
import lista.ramal.listaramal.service.RamalService;

import java.util.List;


@RestController
@RequestMapping(path = "${app.api.base}/ListaRamal")
public class RamalController {

    @Autowired
    private RamalMapper ramalMapper;

    @Autowired
    private RamalService ramalService;

    @GetMapping(path="/pesquisar")
    @Operation(description = "Listagem Geral de ramal")
    public List<RamalListaDTO> listAll(){
        List<Ramal> alunos = ramalService.listarTodos();
     
        return ramalMapper.toDTO(alunos);
    }
    @PostMapping
    @Operation(description = "Método utilizado para realizar a inclusão de um numero de ramal")
    public RamalDTO incluir(@RequestBody RamalDadosAlteravelDTO ramal){
        //prepração para entrada.
        Ramal ramalIncluir = this.ramalMapper.toModelo(ramal);

        //chamada do serviço
        System.out.println(ramalIncluir);
        ramalIncluir = this.ramalService.incluir(ramalIncluir);

        //preparação para o retorno
        RamalDTO retorno = this.ramalMapper.toRamalDTO(ramalIncluir);
        return retorno;
    }
    @PutMapping(path = "/{id}")
    @Operation(description = "Método utilizado para altlerar os dados de um ramal")
    public RamalDTO alterar(@RequestBody() RamalDadosAlteravelDTO ramal, @PathVariable(name = "id") Long numRamal ){
        Ramal pRamal = ramalMapper.toModelo(ramal);
        Ramal alterar = ramalService.alterar(pRamal, numRamal);
        return ramalMapper.toRamalDTO(alterar);
    }
}
