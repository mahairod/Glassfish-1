package org.jboss.weld.tests.enterprise;

public class BowlerHatException extends RuntimeException
{

   public BowlerHatException()
   {
      super();
   }

   public BowlerHatException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public BowlerHatException(String message)
   {
      super(message);
   }

   public BowlerHatException(Throwable cause)
   {
      super(cause);
   }

}
