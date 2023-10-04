package com.ApiRestConcesionario.Controller;

import com.ApiRestConcesionario.Service.CocheAlreadyExists;
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

    @GetMapping ("/coches")
    public ResponseEntity<List<CochesOutput>> listarCoches(){
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
      


    @PostMapping("/coches")
    public ResponseEntity<String> anyadirCoche(@RequestBody CocheInput coche){
        try {
            cochesService.anyadirCoche(coche);
        } catch (CocheAlreadyExists e) {
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
}
