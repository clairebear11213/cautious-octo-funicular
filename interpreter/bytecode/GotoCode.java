package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends ByteCode implements AddressLabel {
    //used to jump to Labels
    //considered an unconditional jump - regardless of the state of the program, take the jump
    //has one argument - label it needs to jump to
    //like FB, Goto's label needs to go through address resolution as well
    //1. has one argument - a label to jump to
    //2. Label must have its address resolved before the program begins executing
    //3. if dump is on - Goto is required to be dumped

    private String label;
    private int address;

    @Override
    public void init(ArrayList<String> arguments) {
        label = arguments.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.setPC(address - 1);  //to allow dump
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        System.out.println("GOTO " + label);
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
