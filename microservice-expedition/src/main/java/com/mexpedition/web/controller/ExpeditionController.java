package com.mexpedition.web.controller;


import com.mexpedition.dao.ExpeditionDao;
import com.mexpedition.model.Expedition;
import com.mexpedition.web.exceptions.ExpeditionNotFoundException;
import com.mexpedition.web.exceptions.ImpossibleAjouterExpeditionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ExpeditionController {

    @Autowired
    ExpeditionDao expeditionDao;

    @PostMapping (value = "/expeditions")
    public ResponseEntity<Expedition> ajouterExpedition (@RequestBody Expedition expedition){

        Expedition nouvelleExpedition = expeditionDao.save(expedition);

       if(nouvelleExpedition == null) throw new ImpossibleAjouterExpeditionException("Impossible d'ajouter cette expédition");

        return new ResponseEntity<Expedition>(expedition, HttpStatus.CREATED);
    }

    @GetMapping(value = "/expeditions/{id}")
    public Optional<Expedition> recupererUneExpedition(@PathVariable int id){

        Optional<Expedition> expedition = expeditionDao.findById(id);

        if(!expedition.isPresent()) throw new ExpeditionNotFoundException("Cette expédition n'existe pas");

        return expedition;
    }


    @PutMapping(value = "/expeditions")
    public ResponseEntity<Expedition>  updateExpedition(@RequestBody Expedition expedition) {

        Expedition nouvelleExpedition = expeditionDao.save(expedition);
        if(nouvelleExpedition == null) throw new ImpossibleAjouterExpeditionException("Impossible de modifier cette expédition");

        return new ResponseEntity<Expedition>(expedition, HttpStatus.OK);
    }
}
