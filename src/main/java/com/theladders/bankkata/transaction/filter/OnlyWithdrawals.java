package com.theladders.bankkata.transaction.filter;

import com.theladders.bankkata.transaction.Transaction;
import com.theladders.bankkata.transaction.Withdrawal;

public class OnlyWithdrawals implements TransactionFilter
{

  public boolean filter(Transaction transaction)
  {
    return transaction instanceof Withdrawal;
  }

}
