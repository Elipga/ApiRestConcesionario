package com.ApiRestConcesionario.Controller;

import com.ApiRestConcesionario.Service.AlreadyExistsException;
import com.ApiRestConcesionario.Service.CochesService;
import com.ApiRestConcesionario.Service.NotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CochesController {

    @Autowired
    private CochesService cochesService;

    @GetMapping("/coches")
    public ResponseEntity<List<CochesOutput>> listarCoches() {
        List<CochesOutput> coches = null;
        try {
            coches = cochesService.getCoches();
            return ResponseEntity.ok(coches);
        } catch (IsEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (InvalidException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (NullException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @GetMapping("coches/{matricula}")
    public ResponseEntity<CochesOutput> listarCocheId(@PathVariable String matricula) {
        try {
            CochesOutput c = cochesService.getCocheId(matricula);
            return ResponseEntity.ok(c);
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


    @PostMapping("/coches")
    public ResponseEntity<String> anyadirCoche(@RequestBody CocheInput coche) {
        try {
            cochesService.anyadirCoche(coche);
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        } catch (IsEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (InvalidException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (NullException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("coches/{matricula}")
    public ResponseEntity<CochesOutput> modificarCoche(@PathVariable String matricula, @RequestBody CocheUpdate cocheUpdate) {
        try {
            CochesOutput modificado = cochesService.updateCoche(matricula, cocheUpdate);
            return ResponseEntity.ok(modificado);
        } catch (NotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IsEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (InvalidException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (NullException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
}
