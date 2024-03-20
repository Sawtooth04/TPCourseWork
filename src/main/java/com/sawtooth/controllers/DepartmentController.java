package com.sawtooth.controllers;

import com.sawtooth.dao.abstractions.TrafficPoliceDao;
import com.sawtooth.models.trafficpolice.TrafficPoliceLabels;
import com.sawtooth.models.trafficpolice.TrafficPoliceLabelsAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final TrafficPoliceDao trafficPoliceDao;

    @Autowired
    public DepartmentController(TrafficPoliceDao trafficPoliceDao) {
        this.trafficPoliceDao = trafficPoliceDao;
    }

    @GetMapping("label/get")
    @ResponseBody
    public CompletableFuture<TrafficPoliceLabels> getLabels(@RequestParam("start") int start, @RequestParam("count") int count,
            @Nullable TrafficPoliceLabelsAssembler assembler) {
        TrafficPoliceLabels result = new TrafficPoliceLabels();

        result.trafficPoliceLabels = trafficPoliceDao.getLabels(start, count);
        return assembler == null ? CompletableFuture.completedFuture(result) : CompletableFuture.completedFuture(assembler.toModel(result));
    }
}
