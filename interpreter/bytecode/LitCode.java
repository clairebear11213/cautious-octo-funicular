package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode {
    //used to push literal values to the runtime stack
    //in some cases, will be accompanied by an id (variable name) - represents the variable name the value belongs to, optional
    //1. takes 1 or 2 arguments
    //2. should only push 1 value to the top of the runtime stack
    //3. if dump on - needs to be dumped according to the specification
    //always assume Lit is an int declaration
    //Basic Syntax: LIT <value> <id>    int <id>
    //<value> is the value being pushed ti RuntimeStack
    //<id> is a variable identifier
    //EX: LIT 0 j       int j

    private int value;
    private String id = "";

    @Override
    public void init(ArrayList<String> arguments) {
        value = Integer.parseInt(arguments.get(1));
        if (arguments.size() > 2) {
            id = arguments.get(2);
        }
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.pushRunTimeStack(value);
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        if (!id.equals("")) {
            System.out.println("LIT " + value + " " + id);
        }
        else {
            System.out.println(("LIT " + value));
        }
    }
}
