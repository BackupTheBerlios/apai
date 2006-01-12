package operaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class input {
	/*
	 * Metodo para leer de consola
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
