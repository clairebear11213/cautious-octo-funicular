package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

import java.util.Scanner;

public class ReadCode extends ByteCode {
    //used to read user input from the keyboard
    //only integers should be accepted from users
    //you may use Scanners or BufferedReaders to read input from the user
    //1. when asking for user input, use prompt: "Please enter an integer : "
    //2. needs to verify that the value given is actually a number
    //if an invalid number is given - state that the input is invalid and ask for another value
    //continue to do so until a value is given
    //3. if dump on - simply print "READ" to the console

    @Override
    public void init(ArrayList<String> arguments) {
        //do nothing
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        Scanner scan = new Scanner(System.in);
        Integer read = null;

        do {
            try {
                System.out.print("Please enter an integer : ");
                read = Integer.parseInt((scan.nextLine()));
            } catch (NumberFormatException e) {
                System.err.println("Input is invalid.");
            }
        } while (read == null);

        virtualMachine.pushRunTimeStack(read);
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        System.out.println("READ");
    }
}
