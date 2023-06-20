package br.com.ueg.prog.webi.ramal.service.impl;

import br.com.ueg.prog.webi.ramal.model.RamalModel;
import br.com.ueg.prog.webi.ramal.repository.RamalRepository;
import br.com.ueg.prog.webi.ramal.service.RamalService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RamalServiceImpl implements RamalService {

    @Autowired
    private RamalRepository ramalRepository;

    @Override
    public void incluir(RamalModel ramalModel) {
        this.validarCamposObrigatorios(ramalModel);
        ramalRepository.save(ramalModel);
    }

    @Override
    public RamalModel alterar(RamalModel ramalModel) {
        this.buscaRamalPorId(ramalModel.getId());
        validarCamposObrigatorios(ramalModel);
        return ramalRepository.save(ramalModel);
    }

    @Override
    public RamalModel excluir(Long idRamal) {
        RamalModel ramalModel = buscaRamalPorId(idRamal);
        ramalRepository.delete(ramalModel);
        return ramalModel;
    }

    @Override
    public List<RamalModel> listar() {
        return ramalRepository.findAll();
    }

    @Override
    public RamalModel buscaRamalPorId(Long idRamal) {
        return ramalRepository.findById(idRamal).orElseThrow(() -> new IllegalArgumentException("Ramal não encontrado!"));
    }

    private void validarCamposObrigatorios(RamalModel ramalModel) {
        if (Objects.isNull(ramalModel))
            throw new IllegalArgumentException("Campos precisam ser preenchidos");

        List<String> campoVazio = new ArrayList<>();

        if (ramalModel.getRamal() == null) campoVazio.add("Ramal");

        if (Strings.isEmpty(ramalModel.getNomeSetor())) campoVazio.add("Nome do Setor");

        if (Strings.isEmpty(ramalModel.getEmailDepartamento())) campoVazio.add("Email do Departamento");

        if (Strings.isEmpty(ramalModel.getNomeResponsavelSetor())) campoVazio.add("Nome Responsavel do Setor");

        if (!campoVazio.isEmpty())
            throw new IllegalArgumentException(
                    "Campos obrigatórios não preenchidos (" +
                            String.join(",", campoVazio) + ")"
            );

    }
}
