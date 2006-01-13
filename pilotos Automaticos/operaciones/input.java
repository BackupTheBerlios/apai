package operaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class input {
	/*
	 * Metodo para leer de consola, obtiene los datos como un string y alli donde los solicitaron
	 * ya los transforman al tipo que quieran.
	 */
	public String readString(){
		BufferedReader br
	      = new BufferedReader(new InputStreamReader(System.in), 1);

	    // Declare and initialize the string
	    String s = "";

	    // Get the string from the keyboard
	    try
	    {
	      s = br.readLine();
	    }
	    catch (IOException ex)
	    {
	      System.out.println(ex);
	    }
	     return s;
	}

}
