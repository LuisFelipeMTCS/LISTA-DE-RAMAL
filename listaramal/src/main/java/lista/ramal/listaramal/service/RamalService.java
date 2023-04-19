package lista.ramal.listaramal.service;

import lista.ramal.listaramal.model.Ramal;

import java.util.List;


public interface RamalService {
    public Ramal incluir(Ramal ramal);
    public Ramal alterar(Ramal ramal, Long numRamal);
    public Ramal excluir(Long id);
    public Ramal obterRamalPeloId(Long id);
    public List<Ramal> listarTodos();  

}
