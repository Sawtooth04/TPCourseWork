package com.sawtooth.controllers;

import com.sawtooth.dao.abstractions.LocalityDao;
import com.sawtooth.models.locality.Localities;
import com.sawtooth.models.locality.LocalitiesAssembler;
import com.sawtooth.models.trafficpolice.TrafficPoliceLabels;
import com.sawtooth.models.trafficpolice.TrafficPoliceLabelsAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/locality")
public class LocalityController {
    private final LocalityDao localityDao;

    @Autowired
    public LocalityController(LocalityDao localityDao) {
        this.localityDao = localityDao;
    }

    @GetMapping("level/get/{level}")
    @ResponseBody
    public CompletableFuture<Localities> getByLevel(@Nullable @PathVariable("level") Integer level, @Nullable LocalitiesAssembler assembler) {
        Localities result = new Localities();

        if (level != null)
            result.localities = localityDao.getByLevel(level);
        return assembler == null ? CompletableFuture.completedFuture(result) : CompletableFuture.completedFuture(assembler.toModel(result));
    }
}
