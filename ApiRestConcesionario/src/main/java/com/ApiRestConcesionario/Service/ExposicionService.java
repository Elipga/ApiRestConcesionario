package com.ApiRestConcesionario.Service;

import com.ApiRestConcesionario.Controller.*;
import com.ApiRestConcesionario.Domain.Coche;
import com.ApiRestConcesionario.Domain.Exposicion;
import com.ApiRestConcesionario.Exception.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class ExposicionService {

    private HashMap<String, Exposicion> exposiciones = new HashMap<>();
    private HashMap<String, HashMap<String, Coche>> exposicionesYcoches = new HashMap<>();

    public ExposicionService() {
        this.exposiciones = exposiciones;
        this.exposicionesYcoches = exposicionesYcoches;
    }

    public ExposicionService(HashMap<String, Exposicion> exposiciones, HashMap<String, HashMap<String, Coche>> exposicionesYcoches) {
        this.exposiciones = exposiciones;
        this.exposicionesYcoches = exposicionesYcoches;
    }

    public HashMap<String, List<CochesOutput>> getExposicionesYCoches() throws IsEmptyException, NullException, InvalidException {
        HashMap<String, List<CochesOutput>> expoCoches = new HashMap<>();
        for (String idExpo : exposicionesYcoches.keySet()) { //recorre las claves (id expos)
            HashMap<String, Coche> cochesExposicion = exposicionesYcoches.get(idExpo); //crea hashmap con los coches de la exposicon
            List<CochesOutput> cochesOutput = new ArrayList<>();
            for (Coche coche : cochesExposicion.values()) { //recorre los coches de la exposicion
                cochesOutput.add(CochesOutput.getCoche(coche));
            }
            expoCoches.put(idExpo,cochesOutput);
        }
        return expoCoches;
    }

    public List<ExposicionOutput> getExposiciones() throws NullException {
        List<ExposicionOutput> exposicionOutputs = new ArrayList<>();
        if (exposiciones.isEmpty()) throw new NullException("No hay exposiciones en el concesionario");
        for (Exposicion exposicion : exposiciones.values()) { //se obtienen los valores de cada expo
            exposicionOutputs.add(new ExposicionOutput(exposicion.getId(), exposicion.getNombre()) ); //añade los coches a lista de CocheOutput con los parámetros
        }
        return exposicionOutputs;
    }

    public void anyadirExposicion(ExposicionInput exposicionInput) throws IsEmptyException, NullException, AlreadyExistsException {
        Exposicion e = ExposicionInput.getExposicion(exposicionInput);
        if (exposicionesYcoches.containsKey(exposicionInput.getId()))
            throw new AlreadyExistsException("La exposición ya existe");
        exposicionesYcoches.put(exposicionInput.getId(), new HashMap<>());
        exposiciones.put(exposicionInput.getId(), e);
    }

    public CocheOutputMarcaMatricula getCocheExpo(String codExpo, String matricula) throws NotExistsException {
        if (exposicionesYcoches.containsKey(codExpo)) {
            HashMap<String, Coche> cochesExposicion = exposicionesYcoches.get(codExpo);
            for (Coche coche : cochesExposicion.values()) {
                if (cochesExposicion.containsKey(matricula))
                    return CocheOutputMarcaMatricula.getCoche(coche);
            }
            throw new NotExistsException("El coche no existe");
        }
        throw new NotExistsException("La exposición no existe");
    }

    public List<CocheOutputMarcaMatricula> getCochesExpo(String id) throws NotExistsException {
        boolean existe = true;
        List<CocheOutputMarcaMatricula> cochesExpo = new ArrayList<>();
        if (exposicionesYcoches.containsKey(id)) {
            HashMap<String, Coche> cochesExposicion = exposicionesYcoches.get(id);
            for (Coche coche : cochesExposicion.values()) {
                cochesExpo.add(CocheOutputMarcaMatricula.getCoche(coche));
            }
            existe = false;
        }
        if (existe == true) throw new NotExistsException("La exposición no existe");
        return cochesExpo;
    }

    public void anyadirCocheAExpo(String id, CocheInput cocheInput) throws AlreadyExistsException, IsEmptyException, InvalidException, NullException, NotExistsException {
        Coche c = CocheInput.getCoche(cocheInput);
        if (exposicionesYcoches.containsKey(id)) {
            HashMap<String, Coche> cochesExposicion = exposicionesYcoches.get(id);
            if (cochesExposicion.containsKey(cocheInput.getMatricula())) {
                throw new AlreadyExistsException("El coche ya existe");
            }
            exposicionesYcoches.get(id).put(cocheInput.getMatricula(), c);
        }
        throw new NotExistsException("La exposición no existe");
    }

    public ExposicionOutput updateExposicion(String id, ExposicionUpdate exposicionUpdate) throws NotExistsException, IsEmptyException, NullException {
        ExposicionOutput exposicionOutput = new ExposicionOutput(exposicionUpdate.getNombre());
        if(exposicionesYcoches.containsKey(id)){
            Exposicion e = exposiciones.get(id);
            e.setNombre(exposicionUpdate.getNombre());
            return exposicionOutput;
        }
        throw new NotExistsException("El coche no existe");
    }
}
