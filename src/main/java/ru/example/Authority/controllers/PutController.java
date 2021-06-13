package ru.example.Authority.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.example.Authority.DataBaseAssistant;
import ru.example.Authority.dataManagers.Desktop;
import ru.example.Authority.dataManagers.HardDisk;
import ru.example.Authority.dataManagers.Monitor;
import ru.example.Authority.dataManagers.Notebook;

@RestController
public class PutController {
    @Autowired
    DataBaseAssistant dataBaseAssistant;

    @PostMapping(path = "/auth/putDesktop")
    public @ResponseBody String putDesktop(@RequestParam("id") int id,
                                           @RequestParam("seriesNumber") int seriesNumber,
                                           @RequestParam("maker") String maker,
                                           @RequestParam("price") int price,
                                           @RequestParam("counter") int counter,
                                           @RequestParam("formFactor") String formFactor){
        if(dataBaseAssistant.set(id, new Desktop(seriesNumber, maker, price, counter, formFactor))){
            return "Successful";
        } else return "Error in upload";
    }

    @PostMapping(path = "/auth/putHardDisk")
    public @ResponseBody String putHardDisk(@RequestParam("id") int id,
                                           @RequestParam("seriesNumber") int seriesNumber,
                                           @RequestParam("maker") String maker,
                                           @RequestParam("price") int price,
                                           @RequestParam("counter") int counter,
                                           @RequestParam("volume") int volume){
        if(dataBaseAssistant.set(id, new HardDisk(seriesNumber, maker, price, counter, volume))){
            return "Successful";
        } else return "Error in upload";
    }

    @PostMapping(path = "/auth/putMonitor")
    public @ResponseBody String putMonitor(@RequestParam("id") int id,
                                           @RequestParam("seriesNumber") int seriesNumber,
                                           @RequestParam("maker") String maker,
                                           @RequestParam("price") int price,
                                           @RequestParam("counter") int counter,
                                           @RequestParam("diagonal") int diagonal){
        if(dataBaseAssistant.set(id, new Monitor(seriesNumber, maker, price, counter, diagonal))){
            return "Successful";
        } else return "Error in upload";
    }

    @PostMapping(path = "/auth/putNotebook")
    public @ResponseBody String putNotebook(@RequestParam("id") int id,
                                           @RequestParam("seriesNumber") int seriesNumber,
                                           @RequestParam("maker") String maker,
                                           @RequestParam("price") int price,
                                           @RequestParam("counter") int counter,
                                           @RequestParam("size") int size){
        if(dataBaseAssistant.set(id, new Notebook(seriesNumber, maker, price, counter, size))){
            return "Successful";
        } else return "Error in upload";
    }
}
