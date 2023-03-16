package exercises.marksStack;

import java.util.Scanner;

import dataStructures.lists.LinkedList;
import dataStructures.stacks.Stack;

public class Main {

    public static void main(String[] args) {
        MarksStack marksStack = new MarksStack();
        LinkedList<String> list = new LinkedList<String>();
        Stack<String> stack = new Stack<String>();
        Scanner scan = new Scanner(System.in);
        
        while(true) {
            String s = scan.nextLine();

            if(s.equals("q")) {
                break;
            }

            marksStack.add(s);
            marksStack.print();
        }

        scan.close();
    }
    
}
