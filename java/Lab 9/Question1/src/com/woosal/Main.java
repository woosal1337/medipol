package com.woosal;

public class Main
{
    public static void main(String [] args)
    {
        try
        {
            badMethod();
            System.out.print("A");
        }
        catch (Exception ex)
        {
            System.out.print("B");
        }
        finally
        {
            System.out.print("C");
        }
        System.out.print("D");
    }
    public static void badMethod()
    {
        throw new Error(); /* Line 22 */
    }
}

// Output:
/*
Exception in thread "main" java.lang.Error
	at com.woosal.Main.badMethod(Main.java:24)
	at com.woosal.Main.main(Main.java:9)
C
 */