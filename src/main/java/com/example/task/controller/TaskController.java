package com.example.task.controller;

import com.example.task.model.TaskResult;
import com.example.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
public class TaskController {

    final TaskService taskService;

    @RequestMapping("/result")
    public @ResponseBody String result ( @RequestParam( defaultValue = "" ) String parsingUrl,
                                         @RequestParam( defaultValue = "tag" ) String parsingType,
                                         @RequestParam( defaultValue = "1" )  int divideCount ) {

        String response = "";

        try {

            Document document = Jsoup.connect(parsingUrl).get();

            String target;

            if(parsingType.equals("tag")) {
                target = document.text();
            }
            else {
                target = document.html();
            }

            TaskResult taskResult = taskService.getResult(target, divideCount);

            response = "몫 : " + taskResult.getShare() + "<br> 나머지 : " + taskResult.getRest();
        }
        catch (IOException ioe) {
            log.debug(String.format( "EXCEPTION %s ", ioe.getMessage()));
        }

        return response;
    }
}
