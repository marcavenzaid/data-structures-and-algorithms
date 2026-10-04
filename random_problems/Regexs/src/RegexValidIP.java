import java.util.Scanner;

/**
 * author: marcavenzaid
 * created: Aug 7, 2018
 */

class MyRegex {
	
    String pattern;
    MyRegex() {
        pattern = "([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\." +
                  "([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\." +
                  "([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\." +
                  "([01]?\\d?\\d|2[0-4]\\d|25[0-5])$";
    }
}

public class RegexValidIP {
	
	public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String IP = in.next();
            System.out.println(IP.matches(new MyRegex().pattern));
        }
        in.close();
    }
}
