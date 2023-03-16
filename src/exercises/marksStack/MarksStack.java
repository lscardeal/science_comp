package exercises.marksStack;

import dataStructures.hashTable.HashTable;
import dataStructures.stacks.Stack;

public class MarksStack {

    private Stack<String> stack;

    private HashTable<String, String> marksTable;

    public MarksStack() {
        this.stack = new Stack<String>();
        this.marksTable = new HashTable<String, String>();
        this.fillMarksTable();
    }

    public void print() {
        if (!stack.isEmpty()) {
            stack.print();
        }
    }

    private void fillMarksTable() {
        this.marksTable.put(")", "(");
        this.marksTable.put("]", "[");
        this.marksTable.put("}", "{");
    }

    public void add(String mark) {
        if (!isMark(mark)) {
            throwInvalidMarkError(mark, "add");
        }

        if (isLeftMark(mark)) {
            stack.push(mark);
        } else if (isRightMark(mark)) {
            rightMarkHandler(mark);
        }
    }

    private void rightMarkHandler(String mark) {
        String lastMark = stack.peek();
        String expectedLastMark = getLeftFromRightMark(mark);
        if (lastMark.equals(expectedLastMark)) {
            stack.pop();
        } else {
            throwInvalidMarkError(mark, "rightMarkHandler");  
        }
    }

    private boolean isLeftParentesis(String mark) {
        return mark.equals("(");
    }

    private boolean isRightParentesis(String mark) {
        return mark.equals(")");
    }

    private boolean isLeftSquareBracket(String mark) {
        return mark.equals("[");
    }

    private boolean isRightSquareBracket(String mark) {
        return mark.equals("]");
    }

    private boolean isLeftCurlyBracket(String mark) {
        return mark.equals("{");
    }

    private boolean isRightCurlyBracket(String mark) {
        return mark.equals("}");
    }

    private boolean isLeftMark(String mark) {
        return isLeftParentesis(mark) || isLeftSquareBracket(mark) || isLeftCurlyBracket(mark);
    }

    private boolean isRightMark(String mark) {
        return isRightParentesis(mark) || isRightSquareBracket(mark) || isRightCurlyBracket(mark);
    }

    private boolean isMark(String mark) {
        return isLeftMark(mark) || isRightMark(mark);
    }

    private String getLeftFromRightMark(String mark) {
        if (isRightMark(mark)) {
            return this.marksTable.get(mark);
        }

        return "";
    }

    private void throwInvalidMarkError(String mark, String caller) {
        System.out.println("Invalid mark: " + mark + " // caller: " + caller);
    }
}