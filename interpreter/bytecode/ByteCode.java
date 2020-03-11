package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public abstract class ByteCode {
    public abstract void init(ArrayList<String> arguments);
    public abstract void execute(VirtualMachine virtualMachine);
    public abstract void dump(VirtualMachine virtualMachine);
}