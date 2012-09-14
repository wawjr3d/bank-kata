package com.theladders.bankkata.transaction.filter;

import com.theladders.bankkata.transaction.Deposit;
import com.theladders.bankkata.transaction.Transaction;

public class OnlyDeposits implements TransactionFilter
{

  public boolean filter(Transaction transaction)
  {
    return transaction instanceof Deposit;
  }

}
