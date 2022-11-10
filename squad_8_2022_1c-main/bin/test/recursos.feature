Feature: Operaciones de horas

  Scenario: Un empleado carga exitosamente sus horas
    Given un empleado
    When carga 4 horas un martes
    Then se cargan 4 horas

  Scenario: un empleado modifica horas
    Given una cantidad de horas cargadas a una tarea
    When se la modifica a 3
    Then las horas modificadas se mantienen en 3

  Scenario: un empleado inserta una cantidad negativa de horas saltando un error
    Given un empleado
    When carga una cantidad negativa de 2 horas
    Then sale un error de cantidad invalida de horas

  Scenario: un empleado inserta una cantidad mayor a 8 de horas resultando en un error
    Given un empleado
    When carga 9 horas un martes
    Then sale un error de cantidad invalida de horas

  Scenario: un empleado modifica sus horas en una tarea con un numero negativo
    Given una cantidad de horas cargadas a una tarea
    When se la modifica con el negativo de 2
    Then sale un error de cantidad invalida de horas

  Scenario: un empleado modifica sus horas en una tarea a una cantidad mayor a 8
    Given una cantidad de horas cargadas a una tarea
    When se la modifica a 9
    Then sale un error de cantidad invalida de horas