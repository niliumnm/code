import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.AssertFalse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@SpringBootTest
@DisplayName("Junit5功能测试")
public class Junit5Test {

    @Test
    @DisplayName("测试")

    @Timeout(value = 5,unit = TimeUnit.SECONDS)
    void testDisplayName() {
        System.out.println(1);

    }

    @BeforeEach
    @Test
    void beforeEach(){
        System.out.println("测试就要开始了");
    }

    @Test
    public String soutName( String name)  {

        Consumer<String> consumer = (String s) -> {
            System.out.println(s);
        };
        consumer.accept("测试");
        return "你好: " + name;
    }


}
