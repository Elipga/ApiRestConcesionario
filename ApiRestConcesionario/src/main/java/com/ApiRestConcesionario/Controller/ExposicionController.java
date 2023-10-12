package com.ApiRestConcesionario.Controller;

import com.ApiRestConcesionario.Exception.InvalidException;
import com.ApiRestConcesionario.Exception.IsEmptyException;
import com.ApiRestConcesionario.Exception.NullException;
import com.ApiRestConcesionario.Exception.AlreadyExistsException;
import com.ApiRestConcesionario.Service.ExposicionService;
import com.ApiRestConcesionario.Exception.NotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ExposicionController {

    @Autowired
    private ExposicionService exposicionService;

    @GetMapping("/exposiciones")
    public ResponseEntity<List<ExposicionOutput>> listarExposiciones() {
        List<ExposicionOutput> exposiciones = null;
        try {
            exposiciones = exposicionService.getExposiciones();
            return ResponseEntity.ok(exposiciones);
        } catch (NullException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @GetMapping("/exposiciones/coches")
    public ResponseEntity<HashMap<String, List<CochesOutput>>> listarExposicionesYCoches() {
        HashMap<String, List<CochesOutput>> exposiciones = null;
        try {
            exposiciones = exposicionService.getExposicionesYCoches();
            return ResponseEntity.ok(exposiciones);
        } catch (IsEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (NullException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (InvalidException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }


    @PostMapping("/exposiciones")
    public ResponseEntity<String> anyadirExposicion(@RequestBody ExposicionInput exposicion) {
        try {
            exposicionService.anyadirExposicion(exposicion);
            return ResponseEntity.ok().build();
        } catch (IsEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (NullException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        }
    }

    @GetMapping("/exposiciones/{id}/coches")
    public ResponseEntity<List<CocheOutputMarcaMatricula>> listarCochesExpo(@PathVariable String id) {
        List<CocheOutputMarcaMatricula> cochesExpo = null;
        try {
            cochesExpo = exposicionService.getCochesExpo(id);
        } catch (NotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(cochesExpo);

    }

    @GetMapping("/exposiciones/{idExpo}/coches/{matricula}")
    public ResponseEntity<CocheOutputMarcaMatricula> listarCocheDeExpo(@PathVariable String idExpo, @PathVariable String matricula) {
        try {
            CocheOutputMarcaMatricula c = exposicionService.getCocheExpo(idExpo, matricula);
            return ResponseEntity.ok().build();
        } catch (NotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/exposiciones/{id}/coches")
    public ResponseEntity<String> anyadirCocheAExpo(@PathVariable String id, @RequestBody CocheInput coche) {
        try {
            exposicionService.anyadirCocheAExpo(id, coche);
            return ResponseEntity.ok().build();
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        } catch (IsEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (InvalidException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (NullException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (NotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /*@PutMapping("exposiciones/{id}")
    public ResponseEntity<ExposicionOutput> modificarExpo(@PathVariable String id, @RequestBody ExposicionUpdate exposicionUpdate) {
        ExposicionOutput modificado = null;
        try {
            modificado = exposicionService.updateExposicion(id, exposicionUpdate);
            return ResponseEntity.ok(modificado);

        } catch (IsEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (NullException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (NotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }*/
}
