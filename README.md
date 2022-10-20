Son 3 programas:
#1.-Calcula la cantidad de digitos que aparecen en un string
#2.-Valida si el formato de la fecha es valido asi como si esta existe
#3.-Permite crear,listar empleados,para ello se empleo JDBC.Para este caso no se hace validaciones de datos sde entrada aunque estas se pueden hacer empleando los 2 programas anteriores
Para ello se hace uso de una Base de Datos la cual se anexa el códigoTambien el programa nos permite calcular la edad de un empleado y listarlos ordenadamente.
Notas:
**En el campo de la FechaNacimiento de la Base de Datos se empleo un tipo varchar aunque se puede usar uno de tipo de Date() y al momento de recuperarlo checar o cambiar el formato para poder crear una instancia de la clase Empleado.
**La fecha de Nacimiento debe de ser ingresada con el formato "yyyy/MM/dd" sino lanzará un Exception ya que no es el formato esperado.


