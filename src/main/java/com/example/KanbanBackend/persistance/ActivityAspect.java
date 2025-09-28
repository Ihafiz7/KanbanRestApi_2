//package com.example.KanbanBackend.persistance;
//
//import com.example.KanbanBackend.activity.ActivityService;
//import lombok.RequiredArgsConstructor;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Aspect
//@Component
//@RequiredArgsConstructor
//public class ActivityAspect {
//    private final ActivityService activityService;
//
//    @AfterReturning(value = "@annotation(logActivity)", returning = "result")
//    public void logActivity(JoinPoint joinPoint, LogActivity logActivity, Object result) {
//        MethodSignature sig = (MethodSignature) joinPoint.getSignature();
//        String[] names = sig.getParameterNames();
//        Object[] args = joinPoint.getArgs();
//        Map<String, Object> params = buildMap(names, args);
//
//        Long projectId = (Long) params.get(logActivity.projectIdParam());
//        Long actorId = (Long) params.get(logActivity.actorIdParam());
//        Long entityId = (Long) params.get(logActivity.entityIdParam());
//
//        String metadata = null;
//        if (logActivity.captureDiff() && args.length > 0 && result != null) {
//            Object newObj = args[args.length - 1];
//            metadata = MetadataUtil.diff(result, newObj);
//        }
//
//        activityService.log(projectId, actorId, logActivity.entityType(), entityId, logActivity.action(), metadata);
//    }
//
//    private Map<String, Object> buildMap(String[] names, Object[] values) {
//        return java.util.stream.IntStream.range(0, names.length)
//                .boxed()
//                .collect(Collectors.toMap(i -> names[i], i -> values[i]));
//    }
//}
