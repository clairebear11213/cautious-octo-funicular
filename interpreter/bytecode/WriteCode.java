package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class WriteCode extends ByteCode {
    //used to display information to the console
    //the only thing Write is allowed to display is the top value of the runtime stack
    //no other information is allowed to be shown
    //1. prints the top of the runtime stack to the console
    //2. NO OTHER info can be printed by Write when printing the value
    //3. if dump on - simply print "WRITE" to the console

    @Override
    public void init(ArrayList<String> arguments) {
        //do nothing
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println(virtualMachine.peekRunTimeStack());
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        System.out.println("WRITE");
    }
}