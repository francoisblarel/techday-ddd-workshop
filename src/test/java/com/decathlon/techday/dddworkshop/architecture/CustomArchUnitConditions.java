package com.decathlon.techday.dddworkshop.architecture;

import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

public class CustomArchUnitConditions {

  public static final ArchCondition<JavaMethod> notHaveSetters =
    new ArchCondition<>("not have any setter methods") {
      @Override
      public void check(JavaMethod method, ConditionEvents events) {
        if (method.getName().startsWith("set") &&
          method.getModifiers().contains(JavaModifier.PUBLIC)) {
          String message = String.format(
            "Setter found: %s.%s()",
            method.getOwner().getName(),
            method.getName()
          );
          events.add(SimpleConditionEvent.violated(method, message));
        }
      }
    };

}
