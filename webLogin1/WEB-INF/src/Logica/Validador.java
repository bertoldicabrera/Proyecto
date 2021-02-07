package Logica;

public class Validador {

	public boolean isUsernameOrPasswordValid(String $cadena) {
        char[] cadena = $cadena.toLowerCase().toCharArray();
        boolean valido = true;
        //Compruebo la longitud
        if (cadena.length <= 6) {
        	valido = false;
        }
        int i =0;
        while ((i<cadena.length)&&(valido==true))
        {
        
            //Compruebo que no existan caracteres especiales 
        	//(solamento los que podrian ser usados para una inyeccion SQL o perjudicar en la consulta);
            if (cadena[i] == ' '
                    || cadena[i] == '='
                    || cadena[i] == '?'
                    || cadena[i] == '+'
                    || cadena[i] == '*'
                    || cadena[i] == '-'
                    || cadena[i] == '%'
                    || cadena[i] == '/'
                    || cadena[i] == '.'
                    || cadena[i] == ','
                    || cadena[i] == ';'
                    || cadena[i] == '!'
                    || cadena[i] == '<'
                    || cadena[i] == '>'
                    || cadena[i] == ':') {
            	valido = false;
            }
            i++;
 
        }
        return valido;
    }
}
