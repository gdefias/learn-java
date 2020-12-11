package Net.Customer;

public class VoteMsg {
  private boolean isInquiry; // 其值为true时表示该消息是查询请求(为false时表示该消息是投票信息)
  private boolean isResponse;// 指示该消息是响应(由服务器发送)还是请求
  private int candidateID;   // 候选人的ID：[0,1000]
  private long voteCount;    // 所查询的候选人获得的总选票数

  public static final int MAX_CANDIDATE_ID = 1000;

  public VoteMsg(boolean isResponse, boolean isInquiry, int candidateID, long voteCount)
          throws IllegalArgumentException {
    if (voteCount != 0 && !isResponse) {
      throw new IllegalArgumentException("Request vote count must be zero");
    }
    if (candidateID < 0 || candidateID > MAX_CANDIDATE_ID) {
      throw new IllegalArgumentException("Bad Candidate ID: " + candidateID);
    }
    if (voteCount < 0) {
      throw new IllegalArgumentException("Total must be >= zero");
    }
    this.candidateID = candidateID;
    this.isResponse = isResponse;
    this.isInquiry = isInquiry;
    this.voteCount = voteCount;
  }

  public void setInquiry(boolean isInquiry) {
    this.isInquiry = isInquiry;
  }

  public void setResponse(boolean isResponse) {
    this.isResponse = isResponse;
  }

  public boolean isInquiry() {
    return isInquiry;
  }

  public boolean isResponse() {
    return isResponse;
  }

  public void setCandidateID(int candidateID) throws IllegalArgumentException {
    if (candidateID < 0 || candidateID > MAX_CANDIDATE_ID) {
      throw new IllegalArgumentException("Bad Candidate ID: " + candidateID);
    }
    this.candidateID = candidateID;
  }

  public int getCandidateID() {
    return candidateID;
  }

  public void setVoteCount(long count) {
    if ((count != 0 && !isResponse) || count < 0) {
      throw new IllegalArgumentException("Bad vote count");
    }
    voteCount = count;
  }

  public long getVoteCount() {
    return voteCount;
  }

  public String toString() {
    String res = (isInquiry ? "inquiry" : "vote") + " for candidate " + candidateID;
    if (isResponse) {
      res = "response to " + res + " who now has " + voteCount + " vote(s)";
    }
    return res;
  }
}
