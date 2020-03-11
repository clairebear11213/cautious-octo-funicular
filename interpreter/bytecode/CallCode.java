package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends ByteCode implements AddressLabel {
    //what the VirtualMachine uses to jump to locations in the program to execute sections of code we call Functions
    //when encountered, jump to the corresponding label in the program
    //also responsible of keeping track of where control should return to when a function completes its execution
    //1. takes 1 argument - a label to jump to
    //2. must go through address resolution to figure out where it needs to jump to in the Program before the program is run
    //3. must store a return address onto the Return Address Stack
    //4. must Jump to the address in the program that corresponds to a label (address computed during address resolution)
    //5. if dump on - needs to be dumped according to the specifications
    //Basic Syntax: CALL <id>   <base-id> (<args>)
    //<id> is a function identifier
    //<base-id> is the actual id of the function
    //ex: for CALL f<<2>>, <base-id> is f
    //<args> are the function arguments
    //EX: If we assume the RuntimeStack has values [0,1,2] [3,4,5]
    //and we execute a CALL f<<3>>
    //before the CALL code is executed, an ARGS 3 has been executed
    //then the dumping of the call code: CALL f<<3>>    f(3,4,5)
    //also strip any brackets < and > from function names for dumping
    //the ARGS just seen tells us that we have a function with 3 arguments, which are the top 3 levels of the stack
    //the 1st arg was pushed 1st, etc

    private String label;
    private int address;
    private String baseID;

    @Override
    public void init(ArrayList<String> arguments) {
        label = arguments.get(1);
        baseID = arguments.get(1).split("<<", 2)[0];
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.savePC();
        virtualMachine.setPC(address - 1);  //to allow dump
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        System.out.print("CALL " + label + " " + baseID + "(");
        virtualMachine.printArguments();
        System.out.println(")");
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setAddress(int newAddress) {
        address = newAddress;
    }
}
