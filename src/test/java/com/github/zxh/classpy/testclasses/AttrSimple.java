package com.github.zxh.classpy.testclasses;

import java.io.IOException;

// SourceFileAttribute
// InnerClassesAttribute
// BootstrapMethodsAttribute
public class AttrSimple {
    
    @Deprecated
    public void deprecatedAttribute() {
        
    }
    
    public void exceptionsAttribute() throws IOException, RuntimeException {
        
    }
    
    public void invokeDynamic() {
        Runnable r = () -> {};
    }
    
    public void enclosingMethodAttribute() {
        new Runnable() {

            @Override
            public void run() {
                //
            }
            
        }.run();
    }
    
    // todo
    public void codeAttribute() {
        int a = 1;
        int b = 2;
        try {
            int c = a / b;
        } catch (Exception e) {
            //
        }
    }
    
}
