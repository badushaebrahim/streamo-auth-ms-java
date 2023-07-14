package streamo.server.auth.bootstrap.service;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import streamo.server.auth.bootstrap.configurations.properties.CustomProperties;
import streamo.server.auth.bootstrap.model.command.TaskDto;
import streamo.server.auth.bootstrap.model.entity.AuthEntity;
import streamo.server.auth.bootstrap.model.entity.TaskEntity;
import streamo.server.auth.bootstrap.model.request.TaskRequest;
import streamo.server.auth.bootstrap.model.request.TaskSearchRequest;
import streamo.server.auth.bootstrap.model.request.TaskUpdateRequest;
import streamo.server.auth.bootstrap.repository.AuthRepository;
import streamo.server.auth.bootstrap.repository.TaskRepository;
import streamo.server.auth.bootstrap.util.JwtTokenUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service

public class TaskSerivce {

    @Autowired
    TaskRepository repository;
    @Autowired
    AuthRepository authRepository;
    @Autowired
    JwtTokenUtil util;
    @Autowired
    CustomProperties properties;
    @Autowired
    ModelMapper mapper;
    @Autowired
    MongoTemplate template;
    public TaskDto createTask(TaskRequest request,String  authToken){
        String tok = util.readToken(authToken,properties);
        try{
        AuthEntity authEntity =authRepository.findById(tok).orElseThrow();
            TaskEntity task = new TaskEntity();
            mapper.map(request,task);
            task.setTaskActive(true);
            task.setTaskAssignedUserName(authEntity.getUserName());
            task.setTaskAssignedUserName(authEntity.getUserName());
            return mapper.map(repository.save(task),TaskDto.class);
        }
        catch (Exception e){
            throw new RuntimeException("Not Authorized");
        }

    }

    public TaskDto updateTask(TaskUpdateRequest request, String taskId,String  authToken){
        String tok = util.readToken(authToken,properties);
        AuthEntity authEntity =authRepository.findById(tok).orElseThrow();
        TaskEntity taskEntity  =   repository.findById(taskId).orElseThrow();
        taskEntity.setTaskName(StringUtils.isNoneBlank(request.getTaskName())?request.getTaskName():taskEntity.getTaskName());
        taskEntity.setTaskAssignedUserName(StringUtils.isNoneBlank(request.getTaskAssignedUserName())?request.getTaskAssignedUserName():taskEntity.getTaskAssignedUserName());
        taskEntity.setTaskStatus(StringUtils.isNoneBlank(request.getTaskStatus())?request.getTaskStatus():taskEntity.getTaskStatus());
        taskEntity.setTaskEndTime(Objects.nonNull(request.getTaskEndTime())?request.getTaskEndTime():taskEntity.getTaskEndTime());
        taskEntity.setTaskActive(Objects.nonNull(request.getTaskActive())?request.getTaskActive():taskEntity.getTaskActive());
        taskEntity.setTaskData(request.getTaskData().isEmpty()?request.getTaskData():taskEntity.getTaskData());

        return mapper.map(repository.save(taskEntity),TaskDto.class);
    }

    public TaskDto updateTaskRunning( String taskId,Boolean isActive,String  authToken){
        String tok = util.readToken(authToken,properties);
        AuthEntity authEntity =authRepository.findById(tok).orElseThrow();
        TaskEntity taskEntity  =   repository.findById(taskId).orElseThrow();

        if (taskEntity.getTaskAssignedUserName().equals(authEntity.getUserName())) {
            taskEntity.setTaskActive(isActive);
            taskEntity.setTaskCompletedTime(LocalDateTime.now());
        }
        return mapper.map(repository.save(taskEntity),TaskDto.class);
    }

    public List<TaskDto> getTask(TaskSearchRequest request, String token){
        String tok = util.readToken(token,properties);
        AuthEntity authEntity =authRepository.findById(tok).orElseThrow();
        Query taskQuery = new Query();
        if (StringUtils.isNoneBlank(request.getTaskStatus()))
            taskQuery.addCriteria(Criteria.where("taskStatus").is(request.getTaskStatus()));
        if (StringUtils.isNoneBlank(request.getTaskName()))
            taskQuery.addCriteria(Criteria.where("taskName").is(request.getTaskName()));
        if (Objects.nonNull(request.getTaskActive()))
            taskQuery.addCriteria(Criteria.where("taskActive").is(request.getTaskActive()));
        if (Objects.nonNull(request.getTaskAssignedUserName()))
            taskQuery.addCriteria(Criteria.where("taskAssignedUserName").is(request.getTaskAssignedUserName()));

        if (Objects.nonNull(request.getTaskFromEndTime())&&Objects.nonNull(request.getTaskToEndTime())){
            taskQuery.addCriteria(Criteria.where("taskEndTime").gte(request.getTaskFromEndTime()).lte(request.getTaskToEndTime()));
        }




        return template.find(taskQuery,TaskEntity.class).stream().map(i->mapper.map(i,TaskDto.class)).collect(Collectors.toList());

    }





}
