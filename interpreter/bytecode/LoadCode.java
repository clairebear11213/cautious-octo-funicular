package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LoadCode extends ByteCode {
    //used to move values from an offset in the current frame to the top of the stack - works from the beginning of the frame
    //needed to set up copies of values for things like expressions/arguments for functions
    //not allowed to operate across frame boundaries
    //1. can have 1 or 2 arguments
    //one argument - the offset in the current frame where the value is to be copied from
    //second argument, if present - the identifier (variable) the value belongs to, used for dumping
    //2. must copy the value at the offset in the current and push it to the top of the stack
    //3. must not remove any values from the runtime stack
    //4. cannot operate across frame boundaries
    //5. if dump on - needs to be dumped according to specifications
    //Basic Syntax: LOAD <offset> <id>   <load id>
    //<offset> is the index in the current from to load from
    //<id> is a variable identifier
    //EX: LOAD 2 j      <load j>

    private int offset;
    private String id = "";

    @Override
    public void init(ArrayList<String> arguments) {
        offset = Integer.parseInt(arguments.get(1));
        if (arguments.size() > 2) { //if 2nd arg
            id = arguments.get(2);
        }
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.loadRunTimeStack(offset);
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        if (!id.equals("")) {
            System.out.println("LOAD " + offset + " " + id + "\t<load " + id + ">");
        }
        else {
            System.out.println("LOAD " + offset);
        }
    }
}
