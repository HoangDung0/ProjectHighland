package hoangdung.springboot.projecthighlands.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class MappingUtils {

    public static ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static List<Transformable> convertIdsToObjects(String listIDs,JpaRepository<Transformable, String> repo) {
        return   objectMapper.readValue(listIDs, new TypeReference<List<Transformable>>(){})
                .stream()
                .map(s -> repo.findById(s.toString()).orElseThrow())
                .toList();
    }

    @SneakyThrows
    public static String convertObjectsToIds(List<Transformable> objects, JpaRepository<Transformable, String> repo ) {
        List<String> ids = objects.stream().map(Transformable::getId).toList();
        return objectMapper.writeValueAsString(ids);
    }

}
