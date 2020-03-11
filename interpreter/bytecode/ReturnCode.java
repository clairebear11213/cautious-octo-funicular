package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode {
    //used to return from functions but also to put return values in the correct position on the runtime stack
    //the interpreter project will use this convention for handling arguments and return values
    //callers of functions are required to set up arguments for the functions they call
    //callees (functions themselves) are required to put return values in the correct spot just before returning from a function
    //this is a convention we will adhere to and is something that is not enforced programmatically
    //this means if you fail to follow this convention, transient bugs can happen
    //has a lot of responsibility - the steps for completing a return are important
    //1. can take 0 or 1 arguments - arguments have no effect on its functionality but do effect Dumping process
    //2. must store the return value at the top of the runtime stack
    //3. must empty the current frame of all values when the function is complete
    //4. must pop the top value from the framePointer stack to remove the frame boundary
    //5. must pop the top of the return address stack and save it into program counter
    //6. if dump on - needs to be dumped according to specifications
    //Basic Syntax: RETURN <id>     EXIT <base-id>:<value>
    //<id> is a function identifier
    //<value> is the value being returned from the function
    //<base-id> is the actual id of the function
    //ex: for RETURN f<<2>>, the <base-id> is f
    //EX: RETURN f<<2>>     EXIT f : 4

    private String function = "";
    private String baseID = "";

    @Override
    public void init(ArrayList<String> arguments) {
        if (arguments.size() > 1) {
            function = arguments.get(1);
            baseID = arguments.get(1).split("<<", 2)[0];
        }
        else {
            //do nothing
        }
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.popFrame();
        virtualMachine.returnPC();
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        System.out.print("RETURN " + function + "\tEXIT " + baseID + " : ");
        virtualMachine.setArgumentsCount(1);
        virtualMachine.printArguments();
        System.out.println("");
    }
}
