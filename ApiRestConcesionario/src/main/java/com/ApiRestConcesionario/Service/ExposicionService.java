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
                cochesOutput.add(CochesOutput.getCoche(coche)); //convierte coche en cocheoutput
            }
            expoCoches.put(idExpo,cochesOutput); //Hashmap con idExpo y los coches de cada una
        }
        return expoCoches;
    }

    public List<ExposicionOutput> getExposiciones() throws NullException, IsEmptyException {
        List<ExposicionOutput> exposicionOutputs = new ArrayList<>();
        if (exposiciones.isEmpty()) throw new NullException("No hay exposiciones en el concesionario");
        for (Exposicion exposicion : exposiciones.values()) { //se recorren los valores de cada expo
            exposicionOutputs.add(new ExposicionOutput(exposicion.getId()) ); //convierte exposicion en exposicionoutput
        }
        return exposicionOutputs;
    }

    public void anyadirExposicion(ExposicionInput exposicionInput) throws IsEmptyException, NullException, AlreadyExistsException {
        Exposicion e = ExposicionInput.getExposicion(exposicionInput); //crea una exposición
        if (exposicionesYcoches.containsKey(exposicionInput.getId())) //busca la exposición
            throw new AlreadyExistsException("La exposición ya existe"); //si existe lanza excepción
        exposicionesYcoches.put(exposicionInput.getId(), new HashMap<>()); //si no existe la añade a expo y coches
        exposiciones.put(exposicionInput.getId(), e); //la añade a expos
    }

    public CocheOutputMarcaMatricula getCocheExpo(String codExpo, String matricula) throws NotExistsException {
        if (exposicionesYcoches.containsKey(codExpo)) { //busca la exposición
            HashMap<String, Coche> cochesExposicion = exposicionesYcoches.get(codExpo); //obtiene los coches de esa exposición
            for (Coche coche : cochesExposicion.values()) { //recorre los coches de la exposición
                if (cochesExposicion.containsKey(matricula)) //busca el coche de la exposición
                    return CocheOutputMarcaMatricula.getCoche(coche); //lo devuelve en cocheOutuput
            }
            throw new NotExistsException("El coche no existe");
        }
        throw new NotExistsException("La exposición no existe");
    }

    public List<CocheOutputMarcaMatricula> getCochesExpo(String id) throws NotExistsException {
        boolean existe = true;
        List<CocheOutputMarcaMatricula> cochesExpo = new ArrayList<>();
        if (exposicionesYcoches.containsKey(id)) { //busca expo
            HashMap<String, Coche> cochesExposicion = exposicionesYcoches.get(id); //hashmap con los coches de la expo
            for (Coche coche : cochesExposicion.values()) { //recorre los coches
                cochesExpo.add(CocheOutputMarcaMatricula.getCoche(coche)); //los añade a lista cocheOutput
            }
            existe = false;
        }
        if (existe == true) throw new NotExistsException("La exposición no existe");
        return cochesExpo;
    }

    public void anyadirCocheAExpo(String id, CocheInput cocheInput) throws AlreadyExistsException, IsEmptyException, InvalidException, NullException, NotExistsException {
        boolean existe = true;
        Coche c = CocheInput.getCoche(cocheInput);
        if (exposicionesYcoches.containsKey(id)) { //busca expo
            HashMap<String, Coche> cochesExposicion = exposicionesYcoches.get(id); //hashmap con los coches de la expo
            if (cochesExposicion.containsKey(cocheInput.getMatricula())) { //comprueba si el coche ya existe
                throw new AlreadyExistsException("El coche ya existe");
            }
            exposicionesYcoches.get(id).put(cocheInput.getMatricula(), c); //si no existe lo añade al hashmap de coches de la expo
            existe = false;
        }
        if (existe == true) throw new NotExistsException("La exposición no existe");
    }

    public ExposicionOutputNombre updateExposicion(String id, ExposicionUpdate exposicionUpdate) throws NotExistsException, IsEmptyException, NullException {
        ExposicionOutputNombre exposicionOutputNombre = new ExposicionOutputNombre(exposicionUpdate.getNombre());
        if(exposicionesYcoches.containsKey(id)){
            Exposicion e = exposiciones.get(id);
            e.setNombre(exposicionUpdate.getNombre());
            return exposicionOutputNombre;
        }
        throw new NotExistsException("La exposición no existe");
    }
}
