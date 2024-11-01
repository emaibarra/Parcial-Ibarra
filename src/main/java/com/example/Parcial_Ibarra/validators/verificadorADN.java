package com.example.Parcial_Ibarra.validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class verificadorADN implements ConstraintValidator<AdnValido,String[]> {

    char[] correto = {'A', 'C', 'T', 'G'};  //arreglos de palabras correctas

    @Override
    public void initialize(AdnValido constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    public boolean isValid(String[] adn, ConstraintValidatorContext context) {
        if (adn==null){
            return false;
        }
        if (adn.length==0){
            return false;
        }

        return esNxN(adn) && esCorrecto(adn);

    }


    public boolean esNxN(String[] adn) {

        int cantidad = 0;
        for (int i = 0; i < adn.length; i++) {
            cantidad = adn[i].length() + cantidad;
        }
        if (cantidad == (adn.length * adn.length)) {
            return true;
        }
        return false;
    }

    public boolean esCorrecto(String[] adn) {

        //Este for pone todas las letras de la posicion i, en un arreglos de letras
        for (int i = 0; i < adn.length; i++) {
            char[] letras = adn[i].toCharArray();
            //este for, POR CADA LETRA en el arreglo LETRAS la pone en una variable Letra
            for (char letra : letras) {
                boolean bandera = false;
                //este for, la letra la compara con el arreglo de las correctas
                for (int j = 0; j < correto.length; j++) {
                    if (letra == correto[j]) {
                        bandera = true;
                        break; // si es verdadero, si matchea deja de recorrer las correctas
                    }
                }
                if (!bandera) {
                    return false;
                }
            }
        }
        return true;
    }

}
