package demo;

import java.util.Arrays;
import java.util.List;

public class GeneralClass {
  public void info( String string ) {
    System.out.println( string );
  }
  
  public void info( String[] array ) {
    System.out.println( Arrays.toString( array ) );
  }
  
  public void info( List<String> list ) {
    System.out.println( String.join( ",", list ) );
  }
  
}