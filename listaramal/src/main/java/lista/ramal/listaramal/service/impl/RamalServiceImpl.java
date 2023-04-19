package lista.ramal.listaramal.service.impl;

import lista.ramal.listaramal.model.Ramal;
import lista.ramal.listaramal.repository.RamalRepository;
import lista.ramal.listaramal.service.RamalService;
import lista.ramal.listaramal.utils.ValidacoesComum;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RamalServiceImpl implements RamalService {

    @Autowired
    private RamalRepository ramalRepository;
    @Override
    public Ramal incluir(Ramal ramal) {
        this.validarCamposObrigatorios(ramal);
        this.validarDados(ramal);
        this.prepararParaIncluir(ramal);
        Ramal ramalIncluido = this.gravarDados(ramal);
        return ramalIncluido;

    }
    private void prepararParaIncluir(Ramal ramal) {
        ramal.setNumRamal("Aguardando inclusao do setor");
    }
    
    private Ramal gravarDados(Ramal ramal) {
        return ramalRepository.save(ramal);
    }

    private void validarCamposObrigatorios(Ramal ramal) {
        if (Objects.isNull(ramal)) {
            throw new IllegalArgumentException("Entidiade Ramal deve ser preenchida");
        }
        List<String> camposVazios = new ArrayList<>();
        if (Strings.isEmpty(ramal.getNumRamal())) {
            camposVazios.add("Numero do Ramal");
        }
        if (Strings.isEmpty(ramal.getNomeCompleto())) {
            camposVazios.add("Nome Completo");
        }
        if (Strings.isEmpty(ramal.getEmailDepartamento())) {
            camposVazios.add("Email do Departamento");
        }
        if (Strings.isEmpty(ramal.getNomeResponsavel())) {
            camposVazios.add("Nome do responsavel");
        }
        if (!camposVazios.isEmpty()) {
            throw new IllegalArgumentException(
                    "Campos Obrigatórios não preenchidos (" +
                            String.join(",", camposVazios) + ")");
        }
    }

    private void validarDados(Ramal ramal) {
        List<String> erros = new ArrayList<>();

        if (!ValidacoesComum.isEmailValido(ramal.getEmailDepartamento())) {
            erros.add("E-mail inválido");
        }

        String validacaoEmail = validarEmailExistente(ramal);
        if (Strings.isNotEmpty(validacaoEmail)) {
            erros.add(validacaoEmail);
        }

        if (!erros.isEmpty()) {
            throw new IllegalArgumentException("Erro ao Validar dados do Ramal: " +
                    String.join(",", erros));
        }
    }

    private String validarEmailExistente(Ramal ramal) {
        Optional<Ramal> ramalDoEmail = ramalRepository.findRamalByemailDepartamento(ramal.getEmailDepartamento());
        String retorno = "";
        if (ramalDoEmail.isPresent()) {
            String nomeCompleto = ramalDoEmail.get().getNomeCompleto();
            retorno = "E-mail já utilizado no sistema, e-mail:" + ramal.getEmailDepartamento() +
                    " do Usuário: " + nomeCompleto;
        }
        return retorno;
    }
    
    @Override
    public Ramal alterar(Ramal ramal, Long numRamal) {
        this.validarCamposObrigatorios(ramal);
        this.validarDados(ramal);

        Ramal ramalBD = recuperarRamalOuGeraErro(numRamal);

        ramal.setNumRamal(ramalBD.getNumRamal());
        ramal.setId(numRamal);

        Ramal save = ramalRepository.save(ramal);

        return save;
    }
    private Ramal recuperarRamalOuGeraErro(Long numRamal) {
        Ramal ramalBD = ramalRepository
                .findById(numRamal)
                .orElseThrow(
                        () -> new IllegalArgumentException("Erro ao Localizar o numero do  ramal para alteração")
                );
        return ramalBD;
    }
    @Override
    public Ramal excluir(Long id) {
        return null;
    }

    @Override
    public Ramal obterRamalPeloId(Long id) {
        return null;
    }

    @Override
    public List<Ramal> listarTodos() {
        return ramalRepository.findAll();
    }
}
