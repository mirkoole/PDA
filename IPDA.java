public interface IPDA {

  public int getNextState(int state, String symbol);
  public boolean accept(String input);
  public String getStackContent(String input);

}