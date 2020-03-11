package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode {
    //used to set up how many arguments a function has
    //always executed just before a Call
    //has 1 argument - the # of values, N, that are arguments for the next function call
    //N will be a part of a newly created activation frame for the next function call
    //needs to figure out where in the runtime stack this new frame begins at and pushes this index into the FramePointer stack
    //1. has 1 argument - # of values that will be a part of the new activation frame
    //2. will needs to push the starting index of the new frame to the framePointer stack
    //3. if dump on - required to be dumped

    private int argsCount;

    @Override
    public void init(ArrayList<String> arguments) {
        argsCount = Integer.parseInt(arguments.get(1));
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.newFrameAt(argsCount);
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        System.out.println("ARGS " + argsCount);
        virtualMachine.setArgumentsCount(argsCount);
    }
}