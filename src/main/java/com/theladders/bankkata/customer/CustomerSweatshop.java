package com.theladders.bankkata.customer;

public class CustomerSweatshop
{
  private Integer lazyAutoIncrement = 0;


  private Integer getNextId()
  {
    lazyAutoIncrement = new Integer(lazyAutoIncrement.intValue() + 1);
    return lazyAutoIncrement;
  }


  public Customer createCustomer()
  {
    return new Customer(getNextId());
  }
}
