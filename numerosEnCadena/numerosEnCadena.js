let cadena="jj@dud324khsa123h12";

let counter=0;
for(let caracter of cadena){
    isNaN(caracter)?counter+=0:counter++;
}
console.log(counter); //Aqui imprime 8
