package com.theladders.bankkata.transaction;

import com.theladders.bankkata.cash.Amount;

public class Withdrawal extends Transaction
{

  public Withdrawal(Amount amount,
                    Amount resultBalance)
  {
    super(amount, resultBalance);
  }

}
