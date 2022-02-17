package jpabook.jpashop

import jpabook.jpashop.aop.TestAspect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {

    @Bean
    fun aop(): TestAspect {
        return TestAspect();
    }

}