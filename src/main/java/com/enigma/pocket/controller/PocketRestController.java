package com.enigma.pocket.controller;

import com.enigma.pocket.entity.Pocket;
import com.enigma.pocket.service.PocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PocketRestController {

    @Autowired
    PocketService pocketService;

    @GetMapping("/pocket/{id}")
    public Pocket findPocketById(@PathVariable(name = "id") String id){
        return pocketService.findPocketById(id);
    }

    @GetMapping("/pockets")
    public List<Pocket> findAllPocket(){
        return pocketService.findAllPocket();
    }

    @PostMapping("/pocket")
    public void createNewPocket(@RequestBody Pocket pocket){
        pocketService.createPocket(pocket);
    }
}
