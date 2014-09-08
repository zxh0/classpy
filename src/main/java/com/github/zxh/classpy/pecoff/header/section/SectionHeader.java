package com.github.zxh.classpy.pecoff.header.section;

import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;

/**
 *
 * @author zxh
 */
public class SectionHeader extends PeComponent {

    private SectionName name;
    
    @Override
    protected void readContent(PeReader reader) {
        name = new SectionName();
        name.read(reader);
        // todo
    }
    
}
