package org.example.helpers.annotations;

import com.epam.reportportal.annotations.Step;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
@Slf4j
public class CustomStepAspect {
  private static final ThreadLocal<String> stepNameHolder = new ThreadLocal<>();

  @Around("@annotation(org.example.helpers.annotations.CustomStep)")
  public Object logAndReportStep(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();

    CustomStep customStep = method.getAnnotation(CustomStep.class);
    String stepName = customStep.value();
    // Store the step name in the thread-local variable
    stepNameHolder.set(stepName);

    // Log the step name to the console
    log.debug(" -[STEP] " + stepName);

    // Report the step to ReportPortal
    return reportStepToReportPortal(joinPoint,stepName);
  }

  @Step("{stepName}")
  private Object reportStepToReportPortal(ProceedingJoinPoint joinPoint, String stepName)
          throws Throwable {
    return joinPoint.proceed();
  }

  // Getter for the step name
  public static String getStepName() {
    return stepNameHolder.get();
  }
}
