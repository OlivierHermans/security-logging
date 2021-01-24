package be.olivierhermans.securitylogging.web.securitylogging;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public enum MappingType {

    REQUEST_MAPPING(RequestMapping.class, annotation -> toPath(((RequestMapping) annotation).value())),
    GET_MAPPING(GetMapping.class, annotation -> toPath(((GetMapping) annotation).value())),
    POST_MAPPING(PostMapping.class, annotation -> toPath(((PostMapping) annotation).value()));

    private final Class<? extends Annotation> clazz;
    private final Function<Annotation, String> pathMapper;

    public static Optional<MappingType> findForAnnotationClass(Class<? extends Annotation> clazz) {
        return Arrays.stream(MappingType.values())
                .filter(type -> clazz.isAssignableFrom(type.clazz))
                .findFirst();
    }

    public String getPath(Annotation annotation) {
        return pathMapper.apply(annotation);
    }

    private static String toPath(String[] paths) {
        return paths.length > 0 ? paths[0] : "";
    }
}
