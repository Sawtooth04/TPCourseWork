package com.sawtooth.controllers;

import com.sawtooth.models.main.MainAssembler;
import com.sawtooth.models.main.MainResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/api")
    public ResponseEntity<MainResponse> getRootEndpoints(@Nullable MainAssembler assembler) {
        if (assembler != null)
            return ResponseEntity.ok(assembler.toModel(new MainResponse()));
        return ResponseEntity.internalServerError().body(new MainResponse());
    }
}
