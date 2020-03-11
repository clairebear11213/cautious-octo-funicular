
package interpreter;

import interpreter.bytecode.ByteCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;


public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    
    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN loadCodes.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }
    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts. Can also use the split function in the String class.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {
        //1. read next ByteCode from the source file
        //2. build an instance of the corresponding class:
            //LIT 2 -> instance of LitCode class
        //3. read any additional arguments for the given ByteCode (if exists)
            //once all arguments are parsed, we will pass these arguments to the ByteCode's init function
        //4. store the fully initialized ByteCode instance into the program data-structure
        //5. once all ByteCodes are loaded, we will resolve all symbolic addresses
            //the Program class will hold the ByteCode program loaded from the file
            //it will also resolve symbolic addresses in the program
            //before:                       ->      after:
            //0. FALSEBRANCH continue<<6>>  ->      FALSEBRANCH 10
            //1. LIT 2                      ->      LIT 2
            //2. LIT 2                      ->      LIT 2
            //3. BOP ==                     ->      BOP ==
            //4. FALSEBRANCH continue<<9>>  ->      FALSEBRANCH 9
            //5. LIT 1                      ->      LIT 1
            //6. ARGS 1                     ->      ARGS 1
            //7. CALL Write                 ->      CALL Write
            //8. STORE 0 i                  ->      STORE 0 i
            //9. LABEL continue<<9>>        ->      LABEL continue<<9>>
            //10. LABEL continue<<6>>       ->      LABEL continue<<6>>
            //NOTE: you should not modify the original source code file, these changes are made to the Program object

        ArrayList<ByteCode> loadedByteCodes = new ArrayList<>();
        String fileLine;

        try {
            while ((fileLine = byteSource.readLine()) != null) {
                //String code = "HALT";
                //String className = CodeTable.get(code);
                //Class c = Class.forName("interpreter.bytecode."+className);
                //ByteCode bc = (ByteCode)
                //c. getDeclaredConstructor().newInstance();

                ArrayList<String> tokens = new ArrayList<>(Arrays.asList(fileLine.split(" ")));

                String code = tokens.get(0);
                String className = CodeTable.getClassName(code);
                Class c = Class.forName("interpreter.bytecode." + className);
                ByteCode bc = (ByteCode) c.getDeclaredConstructor().newInstance();

                bc.init(tokens);
                loadedByteCodes.add(bc);
            }
        } catch (IOException e) {
            System.out.println("Exception in ByteCodeLoader.loadCodes() .readLine: " + e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Exception in ByteCodeLoader.loadCodes() Class.forName: " + e);
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("Exception in ByteCodeLoader.loadCodes() .getDeclaredConstructor: " + e);
            e.printStackTrace();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.out.println("Exception in ByteCodeLoader.loadCodes() .newInstance(): " + e);
            e.printStackTrace();
        }

        Program loadedProgram = new Program(loadedByteCodes);
        loadedProgram.resolveAddress();

        return loadedProgram;
    }
}
