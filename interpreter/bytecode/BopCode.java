package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class BopCode extends ByteCode {
    //used to implement binary operations for the Interpreter Project
    //will need to remove 2 values from the runtime stack and operate on them according to an operation
    //the result needs to be pushed back to the top of the stack
    //order of the operands matter - operands will be pushed in the correct order, but popped in the reverse order
    //1. must pop 2 values from the runtime stack
    //2. must push 1 value, the result, back to the top of the runtime stack
    //3. must implement the following binary operations:
    //Addition: +       Subtraction: -      Division: /     Multiplication: *
    //Equality: ==      Not-Equal To: !=
    //Less Then: <      Less-Than Equal To: <=
    //Greater Than: >   Greater Than Equal To: >=
    //Logical OR: |     Logical AND: &
    //4. if dump on - required to be dumped

    private String operand;

    @Override
    public void init(ArrayList<String> arguments) {
        operand = arguments.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        //pop op2, pop op1, find operation & equate
        int op2 = virtualMachine.popRunTimeStack();
        int op1 = virtualMachine.popRunTimeStack();

        switch (operand) {
            case "+":
                virtualMachine.pushRunTimeStack(op1 + op2);
                break;
            case "-":
                virtualMachine.pushRunTimeStack(op1 - op2);
                break;
            case "/":
                virtualMachine.pushRunTimeStack(op1 / op2);
                break;
            case "*":
                virtualMachine.pushRunTimeStack(op1 * op2);
                break;
            case "==":
                virtualMachine.pushRunTimeStack((op1 == op2) ? 1 : 0);
                break;
            case "!=":
                virtualMachine.pushRunTimeStack((op1 != op2) ? 1 : 0);
                break;
            case "<":
                virtualMachine.pushRunTimeStack((op1 < op2) ? 1 : 0);
                break;
            case "<=":
                virtualMachine.pushRunTimeStack((op1 <= op2) ? 1 : 0);
                break;
            case ">":
                virtualMachine.pushRunTimeStack((op1 > op2) ? 1 : 0);
                break;
            case ">=":
                virtualMachine.pushRunTimeStack((op1 >= op2) ? 1 : 0);
                break;
            case "|":  //op1 true OR op2 true
                virtualMachine.pushRunTimeStack(((op1 != 0) || (op2 != 0)) ? 1 : 0);
                break;
            case "&":  //op1 true AND op2 true
                virtualMachine.pushRunTimeStack(((op1 != 0) && (op2 != 0)) ? 1 : 0);
                break;
            default:
                //do nothing
                break;
        }
    }

    @Override
    public void dump(VirtualMachine virtualMachine) {
        System.out.println("BOP " + operand);
    }
}
