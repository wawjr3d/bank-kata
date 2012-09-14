package com.theladders.bankkata.account;

import static com.theladders.bankkata.cash.Amount.amountOf;

import com.theladders.bankkata.cash.Amount;
import com.theladders.bankkata.cash.InsufficientFundsException;
import com.theladders.bankkata.transaction.Deposit;
import com.theladders.bankkata.transaction.Transactions;
import com.theladders.bankkata.transaction.Withdrawal;
import com.theladders.bankkata.transaction.filter.TransactionFilter;

public class Account
{

  private Amount       balance;
  private Transactions transactions;


  public Account()
  {
    this(amountOf(0));
  }


  public Account(Amount openingBalance)
  {
    balance = openingBalance;
    transactions = new Transactions();

    recordDeposit(openingBalance, openingBalance);
  }


  public void deposit(Amount amount)
  {
    balance = balance.add(amount);

    recordDeposit(amount, balance);
  }


  public void withdraw(Amount amount) throws InsufficientFundsException
  {
    Amount newBalance = balance.subtract(amount);

    if (newBalanceWouldBeInsufficient(newBalance))
    {
      throw new InsufficientFundsException();
    }

    balance = newBalance;
    recordWithdrawal(amount, newBalance);
  }


  public String printStatement()
  {
    return transactions.print();
  }


  public String printStatement(TransactionFilter filter)
  {
    Transactions filteredTransactions = transactions.filterDownTo(filter);
    return filteredTransactions.print();
  }


  public String printTransactionTimes()
  {
    return transactions.printTimes();
  }


  public boolean balanceEquals(Amount amount)
  {
    return balance.equals(amount);
  }


  private boolean newBalanceWouldBeInsufficient(Amount balance)
  {
    return balance.isLessThan(InsufficientFundsException.INSUFFICIENT_AMOUNT_FOR_WITHDRAWL);
  }


  private void recordDeposit(Amount amount,
                             Amount resultBalance)
  {
    Deposit deposit = new Deposit(amount, resultBalance);
    transactions.add(deposit);
  }


  private void recordWithdrawal(Amount amount,
                                Amount resultBalance)
  {
    Withdrawal withdrawal = new Withdrawal(amount, resultBalance);
    transactions.add(withdrawal);
  }
}
