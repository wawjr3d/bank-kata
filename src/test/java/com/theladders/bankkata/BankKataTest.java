package com.theladders.bankkata;

import static com.theladders.bankkata.cash.Amount.amountOf;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.theladders.bankkata.cash.Amount;
import com.theladders.bankkata.cash.InsufficientFundsException;
import com.theladders.bankkata.customer.Customer;
import com.theladders.bankkata.customer.CustomerSweatshop;
import com.theladders.bankkata.transaction.filter.OnlyDeposits;
import com.theladders.bankkata.transaction.filter.OnlyWithdrawals;
import com.theladders.bankkata.transaction.filter.TransactionFilter;

public class BankKataTest
{
  private static final Amount            LOTS_OF_MONEY                   = amountOf(1000);
  private static final Amount            GET_IT_TOGETHER_MONEY           = amountOf(200);
  private static final Amount            SOME_AMOUNT                     = amountOf(200);
  private static final Amount            LESS_THAN_GET_IT_TOGETHER_MONEY = amountOf(50);
  private static final Amount            MORE_THAN_GET_IT_TOGETHER_MONEY = amountOf(250);
  private static final TransactionFilter ONLY_DEPOSITS                   = new OnlyDeposits();
  private static final TransactionFilter ONLY_WITHDRAWALS                = new OnlyWithdrawals();

  private Bank                           bank;
  private CustomerSweatshop              customerSweatShop;
  private Customer                       MrWarner;
  private Customer                       MrWinter;


  @Before
  public void setUp()
  {
    bank = new Bank();
    customerSweatShop = new CustomerSweatshop();

    MrWarner = customerSweatShop.createCustomer();
    MrWinter = customerSweatShop.createCustomer();

    bank.openAccountFor(MrWarner, LOTS_OF_MONEY);
    bank.openAccountFor(MrWinter, GET_IT_TOGETHER_MONEY);
  }


  @Test
  public void bankShouldAcceptDeposits()
  {
    Amount amountToDeposit = SOME_AMOUNT;
    bank.acceptDepositFor(MrWarner, amountToDeposit);

    Amount expectedAmount = LOTS_OF_MONEY.add(amountToDeposit);

    assertTrue(bank.checkCustomerAccountBalanceEquals(MrWarner, expectedAmount));
  }


  @Test
  public void bankShouldAcceptDepositsForDifferentCustomers()
  {
    Amount amountToDeposit = SOME_AMOUNT;
    bank.acceptDepositFor(MrWarner, amountToDeposit);
    bank.acceptDepositFor(MrWinter, amountToDeposit);

    Amount expectedAmountForMrWarner = LOTS_OF_MONEY.add(amountToDeposit);
    Amount expectedAmountForMrWinter = GET_IT_TOGETHER_MONEY.add(amountToDeposit);

    assertTrue(bank.checkCustomerAccountBalanceEquals(MrWarner, expectedAmountForMrWarner));
    assertTrue(bank.checkCustomerAccountBalanceEquals(MrWinter, expectedAmountForMrWinter));
  }


  @Test
  public void bankShouldAllowMultipleDeposits()
  {
    Amount amountToDeposit = SOME_AMOUNT;
    Amount anotherAmountToDeposit = SOME_AMOUNT;

    bank.acceptDepositFor(MrWarner, amountToDeposit);
    bank.acceptDepositFor(MrWarner, anotherAmountToDeposit);

    Amount combinedExpectedDeposit = amountToDeposit.add(anotherAmountToDeposit);
    Amount expectedAmount = LOTS_OF_MONEY.add(combinedExpectedDeposit);

    assertTrue(bank.checkCustomerAccountBalanceEquals(MrWarner, expectedAmount));
  }


  @Test
  public void bankShouldAcceptWithdrawalsForDifferentCustomers() throws InsufficientFundsException
  {
    Amount amountToWithdraw = LESS_THAN_GET_IT_TOGETHER_MONEY;

    bank.acceptWithdrawalFor(MrWarner, amountToWithdraw);
    bank.acceptWithdrawalFor(MrWinter, amountToWithdraw);

    Amount expectedAmountForMrWarner = LOTS_OF_MONEY.subtract(amountToWithdraw);
    Amount expectedAmountForMrWinter = GET_IT_TOGETHER_MONEY.subtract(amountToWithdraw);

    assertTrue(bank.checkCustomerAccountBalanceEquals(MrWarner, expectedAmountForMrWarner));
    assertTrue(bank.checkCustomerAccountBalanceEquals(MrWinter, expectedAmountForMrWinter));
  }


  @Test(expected = InsufficientFundsException.class)
  public void bankShouldRejectWithdrawalsWithoutEnoughFunds() throws InsufficientFundsException
  {
    Amount amountToWithdraw = MORE_THAN_GET_IT_TOGETHER_MONEY;
    bank.acceptWithdrawalFor(MrWinter, amountToWithdraw);
  }


  @Test
  public void bankShouldAllowTransfersBetweenCustomers() throws InsufficientFundsException
  {
    bank.transferBetweenCustomers(MrWarner, MrWinter, SOME_AMOUNT);

    Amount expectedAmountForMrWarner = LOTS_OF_MONEY.subtract(SOME_AMOUNT);
    Amount expectedAmountForMrWinter = GET_IT_TOGETHER_MONEY.add(SOME_AMOUNT);

    assertTrue(bank.checkCustomerAccountBalanceEquals(MrWarner, expectedAmountForMrWarner));
    assertTrue(bank.checkCustomerAccountBalanceEquals(MrWinter, expectedAmountForMrWinter));
  }


  @Test(expected = InsufficientFundsException.class)
  public void bankShouldRejectTransfersWithoutEnoughFunds() throws InsufficientFundsException
  {
    bank.transferBetweenCustomers(MrWinter, MrWarner, MORE_THAN_GET_IT_TOGETHER_MONEY);
  }


  @Test
  public void bankShouldPrintTransactions() throws InsufficientFundsException, InterruptedException
  {
    bank.acceptDepositFor(MrWarner, amountOf(25));
    bank.acceptDepositFor(MrWarner, amountOf(625));
    Thread.sleep(1000);
    bank.acceptWithdrawalFor(MrWarner, amountOf(425));
    bank.acceptDepositFor(MrWarner, amountOf(285));
    bank.acceptWithdrawalFor(MrWarner, amountOf(275));

    System.out.print(bank.printStatementFor(MrWarner));
  }


  @Test
  public void bankShouldBeAbleToPrintOnlyDeposits() throws InsufficientFundsException, InterruptedException
  {
    bank.acceptDepositFor(MrWarner, amountOf(25));
    bank.acceptDepositFor(MrWarner, amountOf(625));
    Thread.sleep(1000);
    bank.acceptWithdrawalFor(MrWarner, amountOf(425));
    bank.acceptDepositFor(MrWarner, amountOf(285));
    bank.acceptWithdrawalFor(MrWarner, amountOf(275));

    System.out.print(bank.printStatementFor(MrWarner, ONLY_DEPOSITS));
  }


  @Test
  public void bankShouldBeAbleToPrintOnlyWithdrawals() throws InterruptedException, InsufficientFundsException
  {
    bank.acceptDepositFor(MrWarner, amountOf(25));
    bank.acceptDepositFor(MrWarner, amountOf(625));
    Thread.sleep(1000);
    bank.acceptWithdrawalFor(MrWarner, amountOf(425));
    bank.acceptDepositFor(MrWarner, amountOf(285));
    bank.acceptWithdrawalFor(MrWarner, amountOf(275));

    System.out.print(bank.printStatementFor(MrWarner, ONLY_WITHDRAWALS));
  }


  @Test
  public void bankShouldBeAbleToPrintOnlyTransactionTimes() throws InterruptedException, InsufficientFundsException
  {
    bank.acceptDepositFor(MrWarner, amountOf(25));
    bank.acceptDepositFor(MrWarner, amountOf(625));
    Thread.sleep(1000);
    bank.acceptWithdrawalFor(MrWarner, amountOf(425));
    bank.acceptDepositFor(MrWarner, amountOf(285));
    bank.acceptWithdrawalFor(MrWarner, amountOf(275));

    System.out.print(bank.printTransactionTimesFor(MrWarner));
  }
}
