package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode {
    //used to move values from top of run time stack to an offset in the current frame
    //offset starts from the beginning of the frame - it is needed to do operations like assignments
    //not allowed to operate across frame boundaries
    //1. can have 1 or 2 arguments
    //one argument - offset in the current frame where the popped value is to be stored
    //second argument, if present - the identifier (variable) the value being moved belongs to - used for dumping
    //2. must pop the top of the runtime stack and store the value at the offset in the current frame
    //3. cannot operator across frame boundaries
    //4. if dump is on - needs to be dumped according to specifications
    //Basic Syntax: STORE <offset> <id>   <id>=<top-of-stack>
    //<offset> is the index in the current from to load from
    //<id> is a variable identifier
    //EX: If we assume the RuntimeStack has the following values: [0,1,2,3]
    //Then executing a Store 1 k would produce: STORE 1 k   k=3

    private int offset;
    private String id = "";
    private Integer value;

    @Override
    public void init(ArrayList<String> arguments) {
        offset = Integer.parseInt(arguments.get(1));
        if (arguments.size() > 2) {
            id = arguments.get(2);
        }
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        value = virtualMachine.peekRunTimeStack();
        virtualMachine.storeRunTimeStack(offset);
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        System.out.println("STORE " + offset + " " + id + "\t" + id + "=" + value);
    }
}
