package com.theladders.bankkata.transaction;

import com.theladders.bankkata.cash.Amount;

public class WhatHappened
{
  private Amount amount;
  private Amount balance;


  public WhatHappened(Amount amount,
                      Amount balance)
  {
    this.amount = amount;
    this.balance = balance;
  }
}
