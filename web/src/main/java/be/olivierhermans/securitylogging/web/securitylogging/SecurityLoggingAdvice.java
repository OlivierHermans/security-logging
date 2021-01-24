package be.olivierhermans.securitylogging.web.securitylogging;

import be.olivierhermans.securitylogging.web.Action;
import be.olivierhermans.securitylogging.web.IdProvider;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

@Aspect
@Component
public class SecurityLoggingAdvice {

    @Around(value = "@annotation(be.olivierhermans.securitylogging.web.securitylogging.SecurityLoggable)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            result = joinPoint.proceed();
            return result;
        } catch (Throwable t) {
            result = t.toString();
            throw t;
        } finally {
            SecurityLogEntry entry = SecurityLogEntry.builder()
                    .action(getAction(joinPoint))
                    .path(getPath(joinPoint))
                    .result(result instanceof IdProvider ? ((IdProvider) result).provideId() : result)
                    .build();
            System.out.println(entry);
        }
    }

    private Action getAction(JoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(SecurityLoggable.class).action();
    }

    private String getPath(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        return UriComponentsBuilder.fromPath(
                String.join("",
                        getPathFragment(method.getDeclaringClass().getAnnotations()),
                        getPathFragment(method.getAnnotations())))
                .buildAndExpand(getPathVariables(joinPoint)).getPath();
    }

    private String getPathFragment(Annotation[] annotations) {
        return Arrays.stream(annotations)
                .map(annotation -> MappingType.findForAnnotationClass(annotation.annotationType())
                        .map(type -> type.getPath(annotation))
                        .orElse(""))
                .filter(not(String::isEmpty))
                .findFirst().orElse("");
    }

    private Map<String, Object> getPathVariables(JoinPoint joinPoint) {
        Parameter[] params = (((MethodSignature) joinPoint.getSignature()).getMethod()).getParameters();

        return IntStream.range(0, params.length)
                .filter(i -> Arrays.stream(params[i].getAnnotations()).anyMatch(annotation ->
                        annotation.annotationType().isAssignableFrom(PathVariable.class)))
                .boxed()
                .map(i -> {
                    PathVariable pathVariable = (PathVariable) Arrays.stream(params[i].getAnnotations())
                            .filter(annotation -> annotation.annotationType().isAssignableFrom(PathVariable.class))
                            .findFirst().orElseThrow(IllegalStateException::new);
                    return Pair.of(pathVariable.value(), joinPoint.getArgs()[i]);
                })
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }
}
