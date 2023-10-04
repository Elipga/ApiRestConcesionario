package com.ApiRestConcesionario.Service;

import com.ApiRestConcesionario.Controller.*;
import com.ApiRestConcesionario.Domain.Coche;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CochesService {
    private List<Coche> coches = new ArrayList<>();



    public List<CochesOutput> getCoches() throws IsEmptyException, InvalidException, NullException {
        List<CochesOutput> cochesOutput = new ArrayList<>();
        for(Coche coche: coches) {
            cochesOutput.add((new CochesOutput(coche.getMatricula(), coche.getMarca(), coche.getModelo(),
                    coche.getAnyo())));
        }
        return cochesOutput;
    }


    public CochesOutput getCocheId(String id) throws IsEmptyException, InvalidException, NullException, NotExistsException {
        for (Coche coche: coches){
            if(coche.getId().equals(id))
                return new CochesOutput(coche.getMatricula());
        }
        throw new NotExistsException ("El coche no existe");
    }


    public void anyadirCoche(CocheInput input) throws CocheAlreadyExists, IsEmptyException, InvalidException, NullException {
        Coche c = new Coche(UUID.randomUUID().toString(), input.getModelo()); //UUIDs (Identificadores Únicos Universales)
        for (Coche coche : coches) {
            if (coche.getMatricula().equalsIgnoreCase(coche.getMatricula()))
                throw new CocheAlreadyExists ("El coche ya existe");
        }
        coches.add(c);
    }
}
