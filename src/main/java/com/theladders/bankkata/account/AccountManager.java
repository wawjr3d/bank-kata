package com.theladders.bankkata.account;

import java.util.HashMap;

import com.theladders.bankkata.cash.Amount;
import com.theladders.bankkata.cash.InsufficientFundsException;
import com.theladders.bankkata.customer.Customer;
import com.theladders.bankkata.transaction.filter.TransactionFilter;

public class AccountManager
{
  private HashMap<Customer, Account> accounts;


  public AccountManager()
  {
    accounts = new HashMap<Customer, Account>();
  }


  public void openAccountFor(Customer customer,
                             Amount amount)
  {
    Account account = new Account(amount);
    accounts.put(customer, account);
  }


  public void closeAccountFor(Customer customer)
  {
    accounts.remove(customer);
  }


  public void depositFor(Customer customer,
                         Amount amount)
  {
    Account account = getAccountByCustomer(customer);
    account.deposit(amount);
  }


  public void withdrawFor(Customer customer,
                          Amount amount) throws InsufficientFundsException
  {
    Account account = getAccountByCustomer(customer);
    account.withdraw(amount);
  }


  public void transferBetweenCustomers(Customer benefactor,
                                       Customer beggar,
                                       Amount amount) throws InsufficientFundsException
  {
    withdrawFor(benefactor, amount);
    depositFor(beggar, amount);
  }


  public String printStatementFor(Customer customer)
  {
    Account account = getAccountByCustomer(customer);
    return account.printStatement();
  }


  public String printStatementFor(Customer customer,
                                  TransactionFilter filter)
  {
    Account account = getAccountByCustomer(customer);
    return account.printStatement(filter);
  }


  public String printTransactionTimesFor(Customer customer)
  {
    Account account = getAccountByCustomer(customer);
    return account.printTransactionTimes();
  }


  public boolean checkCustomerAccountBalanceEquals(Customer customer,
                                                   Amount amount)
  {
    Account account = getAccountByCustomer(customer);
    return account.balanceEquals(amount);
  }


  private Account getAccountByCustomer(Customer customer)
  {
    return accounts.get(customer);
  }
}
