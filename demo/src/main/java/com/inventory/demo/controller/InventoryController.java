package com.inventory.demo.controller;


import com.inventory.demo.dto.AddBackInventoryDTO;
import com.inventory.demo.dto.DeductInventoryDTO;
import com.inventory.demo.repository.InventoryRepository;
import com.inventory.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @PostMapping("/deduct")
    public ResponseEntity<String>deductInventory(@RequestBody DeductInventoryDTO request){
        String result = inventoryService.deductInventory(request);
        return  ResponseEntity.ok(result);
    }

    @PostMapping("/addback")
    public ResponseEntity<String> addBackInventory(@RequestBody AddBackInventoryDTO request) {
        String result=inventoryService.addBackInventory(request);
        return ResponseEntity.ok(result);
    }


}
