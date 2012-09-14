package com.theladders.bankkata;

import com.theladders.bankkata.cash.Amount;
import com.theladders.bankkata.customer.Customer;
import com.theladders.bankkata.customer.CustomerSweatshop;

/*
 * The Bank Account Kata Think of your personal bank account experience. When in doubt, go for the
 * simplest solution. Requirements: Deposit and withdrawal Transfer Account statement (date, amount,
 * balance) Statement printing Statement filters (just deposits, withdrawal, date) Rules: 1. Use
 * only one level of indentation per method 2. Don’t use the else keyword 3. Wrap all primitives and
 * strings 4. Use only one dot per line 5. Don’t abbreviate 6. Keep all entities small (<50 lines)
 * 7. Don’t use any classes with more than two instance variables 8. Use first-class collections 9.
 * Don’t use any getters/setters/properties See the included ObjectCalisthenics.pdf for more details
 * and an explanation of the rules.
 */

public class BankKata
{
  private static final Amount LOTS_OF_MONEY         = new Amount(1000);
  private static final Amount GET_IT_TOGETHER_MONEY = new Amount(50);


  public static void main(String[] args)
  {
    Bank bank = new Bank();

    CustomerSweatshop matchMaker = new CustomerSweatshop();

    Customer MrWarner = matchMaker.createCustomer();
    Customer MrWinter = matchMaker.createCustomer();

    bank.openAccountFor(MrWarner, LOTS_OF_MONEY);
    bank.openAccountFor(MrWinter, GET_IT_TOGETHER_MONEY);

    System.out.println("バンキング型!!");
  }
}
