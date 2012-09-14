package com.theladders.bankkata.transaction;

import java.util.ArrayList;
import java.util.List;

import com.theladders.bankkata.transaction.filter.TransactionFilter;

public class Transactions
{
  private List<Transaction> transactions;


  public Transactions()
  {
    transactions = new ArrayList<Transaction>();
  }


  public void add(Transaction transaction)
  {
    transactions.add(transaction);
  }


  public Transactions filterDownTo(TransactionFilter filter)
  {
    Transactions filteredTransactions = new Transactions();
    for (Transaction transaction : transactions)
    {
      addTransactionIfItPasses(filter, transaction, filteredTransactions);
    }
    return filteredTransactions;
  }


  private void addTransactionIfItPasses(TransactionFilter filter,
                                        Transaction transaction,
                                        Transactions filteredTransactions

  )
  {
    if (filter.filter(transaction))
    {
      filteredTransactions.add(transaction);
    }
  }


  public String print()
  {
    StringBuilder transactionsStringBuilder = new StringBuilder();
    transactionsStringBuilder.append("Type      || When       || Amount || Balance");
    transactionsStringBuilder.append("\n");
    for (Transaction transaction : transactions)
    {
      transactionsStringBuilder.append(transaction.print());
      transactionsStringBuilder.append("\n");
    }

    return transactionsStringBuilder.toString();
  }


  public String printTimes()
  {
    StringBuilder transactionsStringBuilder = new StringBuilder();
    transactionsStringBuilder.append("When       ");
    transactionsStringBuilder.append("\n");
    for (Transaction transaction : transactions)
    {
      transactionsStringBuilder.append(transaction.printWhen());
      transactionsStringBuilder.append("\n");
    }

    return transactionsStringBuilder.toString();
  }
}
