package hoangdung.springboot.projecthighlands.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

@Slf4j
public class CommonUtils {

    // creates a method that take a logic to handle exceptions and log out it
    // the logic that take nothing and return a ResponseEntity as a result of @Service

    public static ResponseEntity<?> controllerWrapper(Supplier<?> controllerLogic) {
        try {
            return ResponseEntity.ok().body(controllerLogic.get());
        } catch (Exception e) {
            log.warn(e.toString());
            return switchException(e);
        }
    }

    public static ResponseEntity<?> switchException(Exception e) {

        var mappings = (Map<Class<? extends Exception>, Supplier<? extends Object>>) Map.of(
                NullPointerException.class, (Supplier<?>) ResponseEntity::notFound,
                IllegalArgumentException.class, (Supplier<?>) ResponseEntity::badRequest,
                NoSuchElementException.class, (Supplier<?>) ResponseEntity::notFound,
                JsonProcessingException.class, (Supplier<?>) ResponseEntity::badRequest
        );

        return (ResponseEntity<?>) mappings.getOrDefault(
                        e.getClass(),
                        () -> ResponseEntity.internalServerError().body(e)
                ).get();
    }

}
