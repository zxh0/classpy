package com.github.zxh.classpy.pecoff.header.section;

import com.github.zxh.classpy.pecoff.PeComponent;
import com.github.zxh.classpy.pecoff.PeReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zxh
 */
public class SectionTable extends PeComponent {

    private final int numberOfSections;
    private final List<SectionHeader> rows = new ArrayList<>();

    public SectionTable(int numberOfSections) {
        this.numberOfSections = numberOfSections;
    }
    
    @Override
    protected void readContent(PeReader reader) {
        for (int i = 0; i < numberOfSections; i++) {
            SectionHeader row = new SectionHeader();
            row.read(reader);
            rows.add(row);
        }
    }

    @Override
    public List<SectionHeader> getSubComponents() {
        return rows;
    }
    
}
