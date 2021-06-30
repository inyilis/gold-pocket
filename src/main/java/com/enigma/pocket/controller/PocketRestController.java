package com.enigma.pocket.controller;

import com.enigma.pocket.entity.Customer;
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

    @GetMapping("/pocket/{id}/customer")
    public List<Pocket> findPocketByCustomerId(@PathVariable(name = "id") String customerId){
        return pocketService.findPocketByCustomer(customerId);
    }

    @PostMapping("/pocket")
    public Pocket createNewPocket(@RequestBody Pocket pocket){
        return pocketService.createPocket(pocket);
    }

    @PutMapping("/pocket")
    public Pocket updatePocket(@RequestBody Pocket pocket) {
        return pocketService.updatePocket(pocket);
    }

    @DeleteMapping("/pocket/{id}")
    public String removePocket(@PathVariable(name = "id") String id) {
        pocketService.removePocket(id);
        return "Deleted";
    }
}
