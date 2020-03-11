package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode implements AddressLabel {
    //used to execute conditional jumps - control structures like if-statements, loops
    //has one argument - a Label that will mark a place in the program to jump to
    //removes the top value of the run time stack and checks to see if value is 0
    //if 0: jump to the corresponding label
    //else: move to the next ByteCode in the program
    //needs its label address to be calculated before the program begins executing
    //requires finding where the destination of the jump is going to be numerically (address in the program)
    //1. takes one argument - a label to jump to
    //2. label address will need to be resolved - requires computing where FB will jump to if the value popped is 0
    //3. Remove the top value of the stack
    //if 0 - jump to label
    //if not 0 - move to next ByteCode
    //if dump is on, FB ByteCode if required to be dumped

    private String label;
    private int address;

    @Override
    public void init(ArrayList<String> arguments) {
        label = arguments.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        if (virtualMachine.popRunTimeStack() == 0) {
            virtualMachine.setPC(address - 1);  //to allow dump
        }
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        System.out.println("FALSEBRANCH " + label);
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