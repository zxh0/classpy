package com.github.zxh.classpy.common;

import org.junit.Assert;
import org.junit.Test;

public class UtilTest {
    
    @Test
    public void cutAndAppendEllipsis() {
        Assert.assertEquals("aaaaa", Util.cutAndAppendEllipsis("aaaaa", 5));
        Assert.assertEquals("aa...", Util.cutAndAppendEllipsis("aaaaaa", 5));
        Assert.assertEquals("aa...", Util.cutAndAppendEllipsis("aa\ud801\udc00aa", 5));
        Assert.assertEquals("a...", Util.cutAndAppendEllipsis("a\ud801\udc00aaa", 5));
    }
    
}
