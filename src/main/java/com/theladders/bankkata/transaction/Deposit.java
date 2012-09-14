package com.theladders.bankkata.transaction;

import com.theladders.bankkata.cash.Amount;

public class Deposit extends Transaction
{

  public Deposit(Amount amount,
                 Amount resultBalance)
  {
    super(amount, resultBalance);
  }

}
