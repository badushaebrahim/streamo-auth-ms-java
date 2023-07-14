package streamo.server.auth.bootstrap.controller;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import streamo.server.auth.bootstrap.model.command.TaskDto;
import streamo.server.auth.bootstrap.model.request.TaskRequest;
import streamo.server.auth.bootstrap.model.request.TaskSearchRequest;
import streamo.server.auth.bootstrap.model.request.TaskUpdateRequest;
import streamo.server.auth.bootstrap.service.TaskSerivce;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/task")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    TaskSerivce service;

    @ApiOperation(value = "create task ")
    @PostMapping()
    public ResponseEntity<TaskDto> signIn(@RequestBody TaskRequest request ,@RequestHeader String token){


            return new ResponseEntity<>(service.createTask(request, token), HttpStatus.OK);

        }

    @ApiOperation(value = "update task ")
    @PutMapping()
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskUpdateRequest request , @RequestHeader String taskId, @RequestHeader String token){

        return new ResponseEntity<>(service.updateTask(request,taskId,token), HttpStatus.OK);
    }

    @ApiOperation(value = "uddate  task running")
    @PutMapping("/")
    public ResponseEntity<TaskDto> updateTaskStatus(@RequestHeader String taskId, @RequestHeader boolean taskStatus,@RequestHeader String token){

        return new ResponseEntity<>(service.updateTaskRunning(taskId,taskStatus,token), HttpStatus.OK);
    }
    @ApiOperation(value = "search task ")
    @PostMapping("/search")
    public ResponseEntity<List<TaskDto>> updateTaskStatus(@RequestHeader TaskSearchRequest request, @RequestHeader String token){

        return new ResponseEntity<>(service.getTask(request, token), HttpStatus.OK);
    }
}
