package com.github.zxh.classpy.pecoff.header.coff;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.Util;
import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author zxh
 */
public class Characteristics extends PeComponent {

    private int flags;
    
    @Override
    protected void readContent(PeReader reader) {
        flags = reader.readUInt16().getValue();
        setDesc(Util.toHexString(flags));
    }

    @Override
    public List<? extends FileComponent> getSubComponents() {
        return Stream.of(CharacteristicFlags.values())
                .filter(c -> (c.value & flags) != 0)
                .map(c -> emptyComponent(c.toString()))
                .collect(Collectors.toList());
    }
    
    private static FileComponent emptyComponent(String name) {
        FileComponent fc = new FileComponent();
        fc.setName(name);
        return fc;
    }
    
}
