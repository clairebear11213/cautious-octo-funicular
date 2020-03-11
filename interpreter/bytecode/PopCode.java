package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode {
    //used to remove values from the run time stack
    //not allowed to remove values across frame boundaries
    //implementer's responsibility to ensure Pop only allowed to pop the appropriate # of values
    //has one value, N (strictly positive), which is the number of values to be popped
    //1. takes one argument - the # of values to remove from the run time stack
    //2. not allowed to operate across frame boundaries
    //3. if dump ON: Pop required to be dumped

    private int numPop;

    @Override
    public void init(ArrayList<String> arguments) {
        numPop = Integer.parseInt(arguments.get(1));
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        for (int i = 0; i < numPop; i++) {
            virtualMachine.popRunTimeStack();
        }
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        System.out.println("POP " + numPop);
    }
}