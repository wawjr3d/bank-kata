package com.theladders.bankkata.transaction;

import com.theladders.bankkata.cash.Amount;

public class TransactionDetails
{
  private Amount amount;
  private Amount resultBalance;


  public TransactionDetails(Amount amount,
                            Amount resultBalance)
  {
    this.amount = amount;
    this.resultBalance = resultBalance;
  }


  public String print()
  {
    return amount.print() + " || " + resultBalance.print();
  }

}
