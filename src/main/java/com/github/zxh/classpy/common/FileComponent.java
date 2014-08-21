package com.github.zxh.classpy.common;

import java.util.List;

/**
 *
 * @author zxh
 */
public interface FileComponent {
    
    public int getOffset();
    public int getLength();
    public String getName();
    public void setName(String name);
    
    public List<? extends FileComponent> getSubComponents();
    
}
