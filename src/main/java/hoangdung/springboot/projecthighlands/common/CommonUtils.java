package hoangdung.springboot.projecthighlands.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
            e.printStackTrace();
            return switchException(e);
        }
    }

    public static ResponseEntity<?> switchException(Exception e) {

        var mappings = (Map<Class<? extends Exception>, HttpStatus>) Map.of(
                JsonProcessingException.class, HttpStatus.BAD_REQUEST,
                IllegalArgumentException.class, HttpStatus.BAD_REQUEST,
                NullPointerException.class, HttpStatus.NOT_FOUND,
                NoSuchElementException.class, HttpStatus.NOT_FOUND,
                ClassCastException.class, HttpStatus.NOT_FOUND
        );
        var t = mappings.getOrDefault(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(t).body(e.getMessage());
    }

}
