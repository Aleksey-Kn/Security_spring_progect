package ru.example.Authority.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.example.Authority.DataBaseAssistant;

@RestController
public class DownloadController{
    @Autowired
    DataBaseAssistant dataBaseAssistant;

    @GetMapping(path = "/user/download")
    public @ResponseBody String download(@RequestParam("contact") String concat,
                                         @RequestParam("id") int id){
        try {
            return dataBaseAssistant.searchId(concat, id).toString()
                    .concat("<p><a href=\"http://localhost:8080\">На главную</a></p>");
        } catch (IndexOutOfBoundsException e){
            return "Incorrect data".concat("<p><a href=\"http://localhost:8080\">На главную</a></p>");
        }
    }
}
