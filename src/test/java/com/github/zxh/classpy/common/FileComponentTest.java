package com.github.zxh.classpy.common;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zxh
 */
public class FileComponentTest {
    
    @Test
    public void _toString() {
        // name == null && desc == null
        FileComponent fc = new FileComponent();
        assertEquals(FileComponent.class.getSimpleName(), fc.toString());
        
        // desc == null
        fc.setName("nama");
        assertEquals("nama", fc.toString());
        
        // name == null
        fc.setName(null);
        fc.setDesc("dasc");
        assertEquals("dasc", fc.toString());
        
        fc.setName("nama");
        fc.setDesc("dasc");
        assertEquals("nama: dasc", fc.toString());
    }
    
}
