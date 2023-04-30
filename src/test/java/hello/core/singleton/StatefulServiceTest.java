package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreaA: A 사용자가 10000원 주문
        statefulService1.problemOrder("userA", 10000);
        //ThreadB: B 사용자가 20000원 주문
        statefulService2.problemOrder("userA", 20000);

        //ThreaA: A 사용자의 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); // 기대 값은 10000원이나 출력은 20000원이 나온다, 중간에 stateService2가 끼어있다.

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}