package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode {
    //used to turn dumping ON and OFF
    //Dumping in the interpreter project is only done when dumping is ON
    //1. has 1 argument - either "ON" or "OFF"
    //2. must request the VirtualMachine to turn dumping either "ON" or "OFF"
    //3. NOT TO BE DUMPED

    private boolean dumpVal;

    @Override
    public void init(ArrayList<String> arguments) {
        dumpVal = arguments.get(1).equals("ON");
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.setDump(dumpVal);
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        //do nothing
    }
}
