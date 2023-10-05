package com.ApiRestConcesionario.Service;

import com.ApiRestConcesionario.Controller.*;
import com.ApiRestConcesionario.Domain.Coche;
import com.ApiRestConcesionario.Domain.Exposicion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ExposicionService {

    private List<Exposicion> exposiciones = new ArrayList<>();

   public List<ExposicionOutput> getExposiciones() throws IsEmptyException, NullException {
       List<ExposicionOutput> exposicionesOutput = new ArrayList<>();
       for(Exposicion exposicion: exposiciones) {
           exposicionesOutput.add((new ExposicionOutput(exposicion.getId())));
       }
       return exposicionesOutput;
   }

    public void anyadirExposicion(ExposicionInput exposicion) throws IsEmptyException, NullException, AlreadyExistsException {
        Exposicion e = new Exposicion(exposicion.getId(),exposicion.getNombre());
        for (Exposicion exposicion1 : exposiciones) {
            if (exposicion1.getId().equalsIgnoreCase(exposicion.getId()))
                throw new AlreadyExistsException("La exposición ya existe");
        }
        exposiciones.add(e);
    }

    public List<CochesOutput> getCochesExpo(String id) throws IsEmptyException, NullException, NotExistsException {
        List<CochesOutput> cochesOutput = new ArrayList<>();
        for(Exposicion exposicion: exposiciones){ //recorre exposiciones
            if(exposicion.getId().equalsIgnoreCase(id)){ //encuentra la exposición
                for(Coche coche : exposicion.getCochesExpo()){ //recorre los coches de la exposición
                    cochesOutput.add(new CochesOutput(coche.getMatricula(), coche.getMarca())); //añade el coche a la lista
                }
                return cochesOutput; //devuelve la lista de coches
            }
        }
        throw new NotExistsException("La exposición no existe");
    }

    public void anyadirCocheAExpo(String id, CocheInput cocheInput) throws AlreadyExistsException, IsEmptyException, InvalidException, NullException, NotExistsException {
       boolean cocheanyadido = false;
       for(Exposicion exposicion: exposiciones){
           if (exposicion.getId().equalsIgnoreCase(id)){
               for(Coche coche: exposicion.getCochesExpo()){
                   if(coche.getMatricula().equalsIgnoreCase(cocheInput.getMatricula())){
                       throw new AlreadyExistsException("El coche ya está en la exposición");
                   }
               }
               exposicion.getCochesExpo().add(new Coche(cocheInput.getMatricula(), " ",
                       cocheInput.getModelo(), 1900));
               cocheanyadido = true;
           }
       }
       if (cocheanyadido == false) throw new NotExistsException("La exposición no existe");
    }

    public ExposicionOutput updateExposicion(String id, ExposicionUpdate exposicionUpdate) throws IsEmptyException, NullException, NotExistsException {
        for (Exposicion exposicion: exposiciones){ //recorre la lista de exposiciones
            if(exposicion.getId().equalsIgnoreCase(id)){ //encuentra la exposicion
                exposicion.setNombre(exposicionUpdate.getNombre()); //actualiza el nombre
                return new ExposicionOutput(exposicion.getId());
            }
        }
        throw new NotExistsException("La exposición no existe");
    }
}
