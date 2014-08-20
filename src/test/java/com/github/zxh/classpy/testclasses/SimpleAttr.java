package com.github.zxh.classpy.testclasses;

import java.io.IOException;

// SourceFileAttribute
// InnerClassesAttribute
// BootstrapMethodsAttribute
public class SimpleAttr {
    
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
    
}
