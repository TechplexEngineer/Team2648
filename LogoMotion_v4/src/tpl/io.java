/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tpl;

/**
 * This class is amed for expansion, at the current time, it provides a method for debuging.
 * @author Techplex Engineer
 */
public class io {

	private static boolean debug = false;

	/**
	 * Use this like a System.our.println(str),
	 * however it will only be printed when the above variable is true.
	 * @param str
	 */
	public static void d(String str)
	{
		if(debug)
			System.out.println(str);
	}

}
