package interpreter;

import java.util.ArrayList;
import java.util.Stack;

public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer>     framePointer;
    private int argumentsCount;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    public void dump() {
        int index = 0;
        if (framePointer.size() > 1) {
            for (int i = 1; i < framePointer.size(); i++) {
                System.out.print(runTimeStack.subList(index, framePointer.get(i)) + " ");
                index = framePointer.get(i);
            }
        }
        System.out.println(runTimeStack.subList(index, runTimeStack.size()));
    }

    public int peek() {
        if (!runTimeStack.isEmpty()) {
            return runTimeStack.get(runTimeStack.size() - 1);
        }
        return 0;
    }

    public int pop() {
        if (!runTimeStack.isEmpty() && framePointer.peek() < runTimeStack.size()) {
            return runTimeStack.remove(runTimeStack.size() - 1);
        }
        return 0;
    }

    public void newFrameAt(int offset) {
        framePointer.push(runTimeStack.size() - offset);
    }

    public void popFrame() {
        Integer top = pop();
        Integer bottom = framePointer.pop();

        for (int i = runTimeStack.size() - 1; i >= bottom; i--) {
            pop();
        }
        push(top);
    }

    public int store(int offset) {
        Integer top = pop();
        runTimeStack.set(framePointer.peek() + offset, top);
        return top;
    }

    public int load(int offset) {
        return push(runTimeStack.get(framePointer.peek() + offset));
    }

    public Integer push(Integer value) {
        runTimeStack.add(value);
        return value;
    }

    public void setArgumentsCount(int count) {
        argumentsCount = count;
    }

    public void printArguments() {
        if(!runTimeStack.isEmpty()) {
            for (int i = 0; i < argumentsCount; i++) {
                System.out.print(runTimeStack.get(runTimeStack.size() - argumentsCount + i));
                if (i != argumentsCount - 1) {
                    System.out.print(", ");
                }
            }
        }
    }
}
