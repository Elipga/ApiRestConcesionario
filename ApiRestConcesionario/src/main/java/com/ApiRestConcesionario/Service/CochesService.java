package com.ApiRestConcesionario.Service;

import com.ApiRestConcesionario.Controller.*;
import com.ApiRestConcesionario.Domain.Coche;
import com.ApiRestConcesionario.Exception.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CochesService {
    private HashMap<String,Coche> coches = new HashMap<>();

    public CochesService(HashMap<String, Coche> coches) {
        this.coches = coches;
    }

    public CochesService() {
        this.coches = coches;
    }

    public List<CochesOutput> getCoches() throws IsEmptyException, InvalidException, NullException {
        List<CochesOutput> cochesOutput = new ArrayList<>();
        if (coches.isEmpty()) throw new NullException("No hay coches en el concesionario");
        for (Coche coche : coches.values()) { //se obtienen los valores de cada coche
            cochesOutput.add(CochesOutput.getCoche(coche)); //añade los coches a lista de CocheOutput con los parámetros
        }
        return cochesOutput;
    }

    public CocheOutputMatricula getCocheId(String matricula) throws IsEmptyException, InvalidException, NullException, NotExistsException {
        if(coches.containsKey(matricula))
            return new CocheOutputMatricula(matricula);
        throw new NotExistsException("El coche no existe");
    }

    public void anyadirCoche(CocheInput input) throws AlreadyExistsException, IsEmptyException, InvalidException, NullException {
        Coche c = CocheInput.getCoche(input);
        if(coches.containsKey(input.getMatricula()))
            throw new AlreadyExistsException("El coche ya existe");
        coches.put(input.getMatricula(), c);
    }

    public CochesOutput updateCoche(String matricula, CocheUpdate cocheUpdate) throws NotExistsException, IsEmptyException, InvalidException, NullException {
        if(coches.containsKey(matricula)){
            Coche c = coches.get(matricula);
            c.setMarca(cocheUpdate.getMarca());
            c.setModelo(cocheUpdate.getModelo());
            return CochesOutput.getCoche(c);
        }
        throw new NotExistsException("El coche no existe");
    }
}
