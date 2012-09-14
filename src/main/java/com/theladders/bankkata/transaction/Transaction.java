package com.theladders.bankkata.transaction;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.theladders.bankkata.cash.Amount;

public abstract class Transaction
{
  private static final SimpleDateFormat FORMAT = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss");

  protected Date                        when;
  protected TransactionDetails          details;


  public Transaction(Amount amount,
                     Amount resultBalance)
  {
    when = new Date();
    details = new TransactionDetails(amount, resultBalance);
  }


  public String print()
  {
    return getClassName() + " || " + printWhen() + " || " + details.print();
  }


  public String printWhen()
  {
    return FORMAT.format(when);
  }


  private String getClassName()
  {
    Class<? extends Transaction> klass = this.getClass();
    return klass.getSimpleName();
  }
}
