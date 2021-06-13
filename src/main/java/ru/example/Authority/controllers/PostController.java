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
public class PostController {
    @Autowired
    private DataBaseAssistant databaseAssistant;

    @PostMapping(path = "/auth/uploadDesktop")
    public @ResponseBody String uploadDesktop(@RequestParam("seriesNumber") int seriesNumber,
                                              @RequestParam("maker") String maker,
                                              @RequestParam("price") int price,
                                              @RequestParam("counter") int counter,
                                              @RequestParam("formFactor") String formFactor){
        if(databaseAssistant.add(new Desktop(seriesNumber, maker, price, counter, formFactor))){
            return "Successful";
        } else return "Error in upload";
    }

    @PostMapping(path = "/auth/uploadNotebook")
    public @ResponseBody String uploadNotebook(@RequestParam("seriesNumber") int seriesNumber,
                                               @RequestParam("maker") String maker,
                                               @RequestParam("price") int price,
                                               @RequestParam("counter") int counter,
                                               @RequestParam("size") int size){
        if(databaseAssistant.add(new Notebook(seriesNumber, maker, price, counter, size))){
            return "Successful";
        } else return "Error in upload";
    }

    @PostMapping(path = "/auth/uploadHardDisk")
    public @ResponseBody String uploadHardDisk(@RequestParam("seriesNumber") int seriesNumber,
                                               @RequestParam("maker") String maker,
                                               @RequestParam("price") int price,
                                               @RequestParam("counter") int counter,
                                               @RequestParam("volume") int volume){
        if(databaseAssistant.add(new HardDisk(seriesNumber, maker, price, counter, volume))){
            return "Successful";
        } else return "Error in upload";
    }

    @PostMapping(path = "/auth/uploadMonitor")
    public @ResponseBody String uploadMonitor(@RequestParam("seriesNumber") int seriesNumber,
                                               @RequestParam("maker") String maker,
                                               @RequestParam("price") int price,
                                               @RequestParam("counter") int counter,
                                               @RequestParam("diagonal") int diagonal){
        if(databaseAssistant.add(new Monitor(seriesNumber, maker, price, counter, diagonal))){
            return "Successful";
        } else return "Error in upload";
    }
}
