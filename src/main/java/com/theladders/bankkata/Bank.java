package com.theladders.bankkata;

import com.theladders.bankkata.account.AccountManager;
import com.theladders.bankkata.cash.Amount;
import com.theladders.bankkata.cash.InsufficientFundsException;
import com.theladders.bankkata.customer.Customer;
import com.theladders.bankkata.transaction.filter.TransactionFilter;

public class Bank
{
  private AccountManager accountManager;


  public Bank()
  {
    accountManager = new AccountManager();
  }


  public void acceptDepositFor(Customer customer,
                               Amount amount)
  {
    accountManager.depositFor(customer, amount);
  }


  public void acceptWithdrawalFor(Customer customer,
                                  Amount amount) throws InsufficientFundsException
  {
    accountManager.withdrawFor(customer, amount);
  }


  public void transferBetweenCustomers(Customer benefactor,
                                       Customer beggar,
                                       Amount amount) throws InsufficientFundsException
  {
    accountManager.transferBetweenCustomers(benefactor, beggar, amount);
  }


  public void openAccountFor(Customer customer,
                             Amount amount)
  {
    accountManager.openAccountFor(customer, amount);
  }


  public String printStatementFor(Customer customer)
  {
    return accountManager.printStatementFor(customer);
  }


  public String printStatementFor(Customer customer,
                                  TransactionFilter filter)
  {
    return accountManager.printStatementFor(customer, filter);
  }


  public String printTransactionTimesFor(Customer customer)
  {
    return accountManager.printTransactionTimesFor(customer);
  }


  public boolean checkCustomerAccountBalanceEquals(Customer customer,
                                                   Amount amount)
  {
    return accountManager.checkCustomerAccountBalanceEquals(customer, amount);
  }
}
