package hello;

public class AccountChangeInfo {

  private String changeId;
  private String transactionId;
  private String transactionType;
  private long amount;
  private long balanceDelta;

  public AccountChangeInfo(String changeId, String transactionId, String transactionType, long amount, long balanceDelta) {
    this.changeId = changeId;
    this.transactionId = transactionId;
    this.transactionType = transactionType;
    this.amount = amount;
    this.balanceDelta = balanceDelta;
  }
}
