public class PDA implements IPDA {

  public static String stack = null;
  
  public static void main(String [] args){

    if(args.length != 1 && args.length != 2){
      System.out.println("Usage: java PDA [Binary-String] [1 = Show Stack Content]");
      System.exit(1);
    }
    
    PDA myPDA = new PDA();
    
    if(myPDA.accept(args[0]) == true){
      System.out.println("Das Wort " + args[0] + " wurde akzeptiert.");
    } else {
      System.out.println("Das Wort " + args[0] + " wurde abgelehnt.");
    }
    
    // show stack content
    if(args.length == 2){
      System.out.println(myPDA.getStackContent(args[0]));
    }
  }

  public boolean accept(String s){
    
    // reset stack
    PDA.stack = "#";
    
    // accept lambda
    if(s.equals("")){
      return true;
    }
    
    // added whitespace at the end to check for lambda
    s += " ";

    char[] input = s.toCharArray();
    
    // start state
    int state = 0;
    
    for(int i = 0; i < input.length; i++){
      
      state = getNextState(state, String.valueOf(input[i]));
      
      // invalid state => word not accepted
      if(-1 == state){
        return false;
      }
    }
    
    // stack empty => word accepted
    if(PDA.stack.equals("")){
      return true;
    }
    
    return false;    
  }
  
  public int getNextState(int state, String symbol){

    // z0
    if(0 == state && symbol.equals("0") && PDA.top().equals("#")){
      PDA.push("A");
      return 1;
    }
    
    if(0 == state && symbol.equals("1") && PDA.top().equals("#")){
      PDA.push("B");
      return 2;
    }
    
    // z1
    if(1 == state && symbol.equals("0") && PDA.top().equals("A")){
      PDA.push("A");
      return 1;
    }
    
    if(1 == state && symbol.equals("1") && PDA.top().equals("A")){
      PDA.pop();
      return 3;
    }
    
    // z2
    if(2 == state && symbol.equals("0") && PDA.top().equals("B")){
      PDA.pop();
      return 3;
    }

    if(2 == state && symbol.equals("1") && PDA.top().equals("B")){
      PDA.push("B");
      return 2;
    }
        
    // z3
    if(3 == state && symbol.equals("0") && PDA.top().equals("B")){
      PDA.pop();
      return 3;
    }
    
    if(3 == state && symbol.equals("1") && PDA.top().equals("A")){
      PDA.pop();
      return 3;
    }
    
    if(3 == state && (symbol.equals("") || symbol.equals(" ")) && PDA.top().equals("#")){
      PDA.pop();
      return 3;
    }
    
    // return invalid state
    return -1;

  }
   
  public String getStackContent(String input){
 
    accept(input);
    
    String output = "[";   
    
    char[] stack = PDA.stack.toCharArray();
    
    for(int i = 0; i < stack.length; i++){
    
      output = output.concat(stack[i] + "");
      
      if(i < stack.length - 1){
        output = output.concat(", ");
      }
      
    }
    
    output = output.concat("]");
    return output;
  }

  // return top symbol of string-stack
  private static String top(){
    return PDA.stack.substring(PDA.stack.length()-1, PDA.stack.length());
  }
  
  // push symbol to string-stack
  private static void push(String symbol){
    PDA.stack += symbol;
  }
  
  // pop top-symbol from string-stack
  private static void pop(){
    PDA.stack = PDA.stack.substring(0,PDA.stack.length()-1);
  }
}