package ru.example.Authority.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.example.Authority.DataBaseAssistant;
import ru.example.Authority.dataManagers.Data;

import java.util.List;

@RestController
public class ScanController {
    @Autowired
    DataBaseAssistant dataBaseAssistant;

    @GetMapping(path = "/auth/print")
    public @ResponseBody String download(@RequestParam("contact") String concat){
        List<Data> data = dataBaseAssistant.getAll(concat);
        StringBuilder result = new StringBuilder();
        data.forEach(o -> result.append(o.toString()).append("<br>"));
        return result.toString();
    }
}
