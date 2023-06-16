package hoangdung.springboot.projecthighlands.config.aop;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Configuration
public class AopConfiguration {


    @Around("@annotation(hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity)")
    @SneakyThrows
    public Transformable transformFromDtoToResponseEntity(ProceedingJoinPoint joinPoint) throws Throwable {
        //1. Lấy ra giá trị trả về cuộc hàm đc đánh @Transfer
        //vd: lấy được đc 1 Object : User
        var result = joinPoint.proceed();

        //2. xử lý tên class DAO, DTO
        //vd: lấy được tên package User:
        //C:\Users\DUng pc\Desktop\projecthighlands\src\main\java\hoangdung\springboot\projecthighlands\model\dao\User.java
        var resultClassName = result.getClass().getName();

        //3. Thêm địa chỉ Class có chứa hàm Transfer
        //vd: hoangdung.springboot.projecthighlands.model.dao.User
        // --> hoangdung.springboot.projecthighlands.model.response.UserResponseEntity
        var resultResponseEntityClassName = resultClassName.replace("dao", "response") + "ResponseEntity";

        //4. lấy hàm convert (DAO-> entity)
        var mappingMethodName = "from" + resultClassName.substring(48);

        var transformMethod = Arrays.stream(Class.forName(resultResponseEntityClassName).getDeclaredMethods())
                .filter(method -> method.getName().contains(mappingMethodName))
                .findFirst()
                .orElse(null);

        //5. Gọi hàm Convert vừa lấy ra
        return (Transformable) transformMethod.invoke(new Object(), result);

    }

    @Around("@annotation(hoangdung.springboot.projecthighlands.config.aop.MultipleTransferToResponseEntities)")
    @SneakyThrows
    public List<Transformable> multipleTransformFromDtoToResponseEntity(ProceedingJoinPoint joinPoint) {
        var result = (List<Transformable>) joinPoint.proceed();

        var resultClassName = result.get(0).getClass().getName();

        var resultResponseEntityClassName = resultClassName.replace("dao", "response") + "ResponseEntity";

        var mappingMethodName = "from" + resultClassName.substring(48);

        var transformMethod = Arrays.stream(Class.forName(resultResponseEntityClassName).getDeclaredMethods())
                .filter(method -> method.getName().contains(mappingMethodName))
                .findFirst()
                .orElse(null);

        List<Transformable> listTransformable = new ArrayList<>();
        for (Transformable e: result) {
            listTransformable.add((Transformable) transformMethod.invoke(new Object(), e));
        }
        return listTransformable;

    }


}
