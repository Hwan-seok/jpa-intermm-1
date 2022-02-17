package jpabook.jpashop.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory


@Aspect
//@Component
class TestAspect {
    val logger = LoggerFactory.getLogger(javaClass);

    @Around("execution(* jpabook.jpashop..*(..)) && !target(jpabook.jpashop.Config)")
    @Throws(Throwable::class)
    fun logging(pjp: ProceedingJoinPoint): Any? {
        logger.info("start - " + pjp.signature.declaringTypeName + " / " + pjp.signature.name);
        val result = pjp.proceed()
        logger.info("finished - " + pjp.signature.declaringTypeName + " / " + pjp.signature.name);
        return result;
    }


}