package com.theladders.bankkata.transaction.filter;

import com.theladders.bankkata.transaction.Transaction;

public interface TransactionFilter
{
  public boolean filter(Transaction transaction);
}
