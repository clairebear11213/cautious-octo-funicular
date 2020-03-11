package interpreter;

import java.util.Stack;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.DumpCode;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;

    private boolean dumpFlag;

    protected VirtualMachine(Program program) {
        this.program = program;
    }

    public void halt() {
        isRunning = false;
    }

    public int peekRunTimeStack() {
        return runTimeStack.peek();
    }

    public void pushRunTimeStack(Integer value) {
        runTimeStack.push(value);
    }

    public int popRunTimeStack() {
        return runTimeStack.pop();
    }

    public void storeRunTimeStack(int offset) {
        runTimeStack.store(offset);
    }

    public void loadRunTimeStack(Integer offset) {
        runTimeStack.load(offset);
    }

    public void newFrameAt(int offset) {
        runTimeStack.newFrameAt(offset);
    }

    public void popFrame() {
        runTimeStack.popFrame();
    }

    public void pushPC(int value) {
        returnAddress.push(value);
    }

    public void returnPC() {
        if(!returnAddress.isEmpty()) {
            setPC((int) returnAddress.pop());
        }
    }

    public void savePC() {
        pushPC(programCounter);
    }

    public void setPC(int value) {
        programCounter = value;
    }

    public void setDump(boolean flag) {
        dumpFlag = flag;
    }

    public void setArgumentsCount(int count) {
        runTimeStack.setArgumentsCount(count);
    }

    public void executeProgram() {
        programCounter = 0;
        runTimeStack = new RunTimeStack();
        returnAddress = new Stack<Integer>();
        isRunning = true;
        dumpFlag = false;

        while (isRunning) {
            ByteCode bc = program.getCode(programCounter);
            bc.execute(this);
            if (dumpFlag && !(bc instanceof DumpCode)) {
                bc.dump(this);
                runTimeStack.dump();
            }
            programCounter++;
        }
    }

    public void printArguments() {
        runTimeStack.printArguments();
    }
}
