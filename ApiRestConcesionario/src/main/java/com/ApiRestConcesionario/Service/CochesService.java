package com.ApiRestConcesionario.Service;

import com.ApiRestConcesionario.Controller.*;
import com.ApiRestConcesionario.Domain.Coche;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public CochesOutput getCocheId(String matricula) throws IsEmptyException, InvalidException, NullException, NotExistsException {
        for (Coche coche: coches){
            if(coche.getMatricula().equals(matricula))
                return new CochesOutput(coche.getMatricula());
        }
        throw new NotExistsException ("El coche no existe");
    }

    public void anyadirCoche(CocheInput input) throws AlreadyExistsException, IsEmptyException, InvalidException, NullException {
        Coche c = new Coche(input.getMatricula(), "marca" ,input.getModelo(), 1900);
        for (Coche coche : coches) {
            if (coche.getMatricula().equalsIgnoreCase(coche.getMatricula()))
                throw new AlreadyExistsException("El coche ya existe");
        }
        coches.add(c);
    }

    public CochesOutput updateCoche(String matricula, CocheUpdate cocheUpdate) throws NotExistsException, IsEmptyException, InvalidException, NullException {
        for (Coche coche: coches){ //recorre los coches
            if(coche.getMatricula().equalsIgnoreCase(matricula)){ //encuentra el coche
                coche.setMarca(cocheUpdate.getMarca()); //actualiza la marca
                coche.setModelo(cocheUpdate.getModelo());
                return new CochesOutput(coche.getMatricula(), coche.getMarca(),
                        coche.getModelo(), coche.getAnyo());
            }
        }
        throw new NotExistsException("El coche no existe");
    }
}
