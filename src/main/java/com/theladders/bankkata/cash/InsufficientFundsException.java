package com.theladders.bankkata.cash;

import static com.theladders.bankkata.cash.Amount.amountOf;

public class InsufficientFundsException extends Exception
{

  public static final Amount INSUFFICIENT_AMOUNT_FOR_WITHDRAWL = amountOf(0);
  /**
   * 
   */
  private static final long  serialVersionUID                  = 1L;

}
