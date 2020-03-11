package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class HaltCode extends ByteCode {
    //used to alert the virtual machine that program execution is to be stopped
    //not allowed to kill the interpreter program
    //may not call System.out.exit to stop the execution of the program
    //1. Notify the VirtualMachine that execution needs to be Halted
    //2. Halt takes not arguments
    //3. Halt ByteCodes are not Dumped
    //4. Halt cannot execute a system.exit function call

    @Override
    public void init(ArrayList<String> arguments) {
        //do nothing
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.halt();
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        System.out.println("HALT");
    }
}