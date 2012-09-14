package com.theladders.bankkata.cash;

public class Amount implements Comparable<Amount>
{
  private Double amount;


  public static Amount amountOf(Double value)
  {
    return new Amount(value);
  }


  public static Amount amountOf(Integer value)
  {
    return new Amount(value);
  }


  public Amount(Double amount)
  {
    this.amount = amount;
  }


  public Amount(Integer amount)
  {
    this.amount = amount.doubleValue();
  }


  public Amount add(Amount otherAmount)
  {
    return amountOf(amount + otherAmount.amount);
  }


  public Amount subtract(Amount otherAmount)
  {
    return amountOf(amount - otherAmount.amount);
  }


  public Amount negative()
  {
    return amountOf(-amount);
  }


  public Boolean isLessThan(Amount otherAmount)
  {
    return compareTo(otherAmount) == -1;
  }


  public String print()
  {
    return amount.toString();
  }


  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((amount == null) ? 0 : amount.hashCode());
    return result;
  }


  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Amount other = (Amount) obj;
    if (amount == null)
    {
      if (other.amount != null)
        return false;
    }
    else if (!amount.equals(other.amount))
      return false;
    return true;
  }


  public int compareTo(Amount o)
  {
    return amount.compareTo(o.amount);
  }

}
