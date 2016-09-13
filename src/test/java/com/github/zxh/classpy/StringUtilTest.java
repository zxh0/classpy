package com.github.zxh.classpy;

import com.github.zxh.classpy.helper.StringUtil;

import static org.junit.Assert.assertEquals;

/**
 * 
 * @author zxh
 */
public class StringUtilTest {
    
    //@Test
    public void cutAndAppendEllipsis() {
        assertEquals("aaaaa", StringUtil.cutAndAppendEllipsis("aaaaa", 5));
        assertEquals("aa...", StringUtil.cutAndAppendEllipsis("aaaaaa", 5));
        assertEquals("aa...", StringUtil.cutAndAppendEllipsis("aa\ud801\udc00aa", 5));
        assertEquals("a...", StringUtil.cutAndAppendEllipsis("a\ud801\udc00aaa", 5));
        
        assertEquals("...", StringUtil.cutAndAppendEllipsis("\naaa", 5));
        assertEquals("a...", StringUtil.cutAndAppendEllipsis("a\naaa", 5));
    }
    
}
