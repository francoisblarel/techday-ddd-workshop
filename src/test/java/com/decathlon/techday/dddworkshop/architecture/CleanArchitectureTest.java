package com.decathlon.techday.dddworkshop.architecture;

import static com.decathlon.techday.dddworkshop.architecture.CustomArchUnitConditions.notHaveSetters;
import static com.tngtech.archunit.base.DescribedPredicate.not;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAnyPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = {"com.decathlon.techday.dddworkshop"}, importOptions = {
  ImportOption.DoNotIncludeTests.class})
public class CleanArchitectureTest {

  @ArchTest
  public static final ArchRule domain_does_not_depend_on_infrastructure = noClasses()
    .that()
    .resideInAPackage("..domain..")
    .should()
    .dependOnClassesThat()
    .resideInAPackage(
      "..infrastructure..");

  @ArchTest
  public static final ArchRule domain_does_not_depend_on_application = noClasses()
    .that()
    .resideInAPackage("..domain..")
    .should()
    .dependOnClassesThat()
    .resideInAPackage("..application..");

  @ArchTest
  public static final ArchRule application_does_not_depend_on_infrastructure = noClasses()
    .that()
    .resideInAPackage("..application..")
    .should()
    .dependOnClassesThat(
      resideInAnyPackage("..infrastructure..")
    );

  @ArchTest
  public static final ArchRule infrastructure_has_no_dependency_with_something_else_than_infrastructure = classes()
    .that()
    .resideInAPackage("..infrastructure..")
    .should()
    .onlyHaveDependentClassesThat()
    .resideInAnyPackage("..infrastructure..", "..configurations..");

  @ArchTest
  public static final ArchRule no_log_outside_use_cases_nor_queries = noClasses()
    .that()
    .resideOutsideOfPackages("..usecases..", "..queries..")
    .should()
    .dependOnClassesThat()
    .resideInAPackage("org.slf4j..");

  @ArchTest
  public static final ArchRule no_setter_in_domain = methods()
    .that()
    .areDeclaredInClassesThat(
      resideInAnyPackage("..domain..")
        .and(not(resideInAnyPackage("..studio.domain.."))) // Studio use JPA Entities
    )
    .should(notHaveSetters);

  @ArchTest
  public static final ArchRule domain_should_not_depend_on_repositories =
    noClasses()
      .that()
      .resideInAPackage("..domain..")
      .should()
      .dependOnClassesThat()
      .haveNameMatching(".*Repository");
}
