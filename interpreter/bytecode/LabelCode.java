package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends ByteCode {
    //a ByteCode that has no functionality
    //sole purpose is to mark locations in the program where other ByteCodes can jump to
    //used to address resolution so other ByteCodes know where they are supposed to jump to
    //1. takes 1 argument - a label which is used to denote a location in the program
    //2. dumping is optional

    private String label;

    @Override
    public void init(ArrayList<String> arguments) {
        label = arguments.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        //do nothing
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        System.out.println("LABEL " + label);
    }

    public String getLabel() {
        return label;
    }
}
