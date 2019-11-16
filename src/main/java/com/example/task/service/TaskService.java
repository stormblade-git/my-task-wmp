package com.example.task.service;

import com.example.task.model.TaskResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class TaskService {

    public TaskResult getResult(String target, int divideCount) {

        String strAlphabets = target.replaceAll("[^a-zA-Z]+", "");
        String strNumbers = target.replaceAll("[^0-9]", "");

        strAlphabets =
                Stream.of( strAlphabets.split("") )
                        .sorted( ( first, second ) -> {

                            String firstUpper = first.toUpperCase();
                            String secondUpper = second.toUpperCase();

                            int compared = firstUpper.compareTo(secondUpper);

                            if( compared != 0 ) {
                                return compared;
                            }
                            else {
                                return second.compareTo(first) * -1;
                            }
                        })
                        .collect(Collectors.joining());

        strNumbers =
                Stream.of( strNumbers.split("") )
                        .sorted()
                        .collect(Collectors.joining());

        char [] alphabets = strAlphabets.toCharArray();
        char [] numbers = strNumbers.toCharArray();

        StringBuilder sb = new StringBuilder();
        for( int i=0; i < alphabets.length + numbers.length; i++ ) {

            if( i < alphabets.length ) {
                sb.append(alphabets[i]);
            }

            if( i < numbers.length ) {
                sb.append(numbers[i]);
            }
        }

        return getTaskResult(sb.toString(), divideCount);
    }

    private TaskResult getTaskResult(String result , int divideCount ) {

        TaskResult taskResult = new TaskResult();

        int resultLength = result.length();
        int divided = divideCount > resultLength ? 0 : resultLength % divideCount;

        taskResult.setShare( result.substring(0, resultLength - divided) );
        taskResult.setRest( result.substring(resultLength - divided) );

        return taskResult;
    }
}
