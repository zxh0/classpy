package com.github.zxh.classpy.common;

import com.github.zxh.classpy.classfile.Util;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * 
 * @author zxh
 */
public class UtilTest {
    
    @Test
    public void cutAndAppendEllipsis() {
        assertEquals("aaaaa", Util.cutAndAppendEllipsis("aaaaa", 5));
        assertEquals("aa...", Util.cutAndAppendEllipsis("aaaaaa", 5));
        assertEquals("aa...", Util.cutAndAppendEllipsis("aa\ud801\udc00aa", 5));
        assertEquals("a...", Util.cutAndAppendEllipsis("a\ud801\udc00aaa", 5));
        
        assertEquals("...", Util.cutAndAppendEllipsis("\naaa", 5));
        assertEquals("a...", Util.cutAndAppendEllipsis("a\naaa", 5));
    }
    
}
