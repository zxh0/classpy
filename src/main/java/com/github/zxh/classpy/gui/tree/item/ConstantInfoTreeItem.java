package com.github.zxh.classpy.gui.tree.item;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.cp.ConstantClassInfo;
import com.github.zxh.classpy.classfile.cp.ConstantDoubleInfo;
import com.github.zxh.classpy.classfile.cp.ConstantFloatInfo;
import com.github.zxh.classpy.classfile.cp.ConstantInfo;
import com.github.zxh.classpy.classfile.cp.ConstantIntegerInfo;
import com.github.zxh.classpy.classfile.cp.ConstantLongInfo;
import com.github.zxh.classpy.classfile.cp.ConstantUtf8Info;
import com.github.zxh.classpy.gui.tree.ClassTreeItem;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

/**
 *
 * @author zxh
 */
public class ConstantInfoTreeItem extends LazyTreeItem {

    public ConstantInfoTreeItem(ConstantInfo c) {
        super(c);
    }

    @Override
    protected void buildChildren(ObservableList<TreeItem<ClassComponent>> children) {
        ConstantInfo c = (ConstantInfo) getValue();
        children.add(new ClassTreeItem(c.getTag()));
        if (c instanceof ConstantUtf8Info) {
            ConstantUtf8Info utf8 = (ConstantUtf8Info) c;
            children.add(new ClassTreeItem(utf8.getByteCount()));
            children.add(new ClassTreeItem(utf8.getBytes()));
        } else if (c instanceof  ConstantIntegerInfo) {
            ConstantIntegerInfo i = (ConstantIntegerInfo) c;
            children.add(new ClassTreeItem(i.getBytes()));
        } else if (c instanceof ConstantFloatInfo) {
            ConstantFloatInfo f = (ConstantFloatInfo) c;
            children.add(new ClassTreeItem(f.getBytes()));
        } else if (c instanceof  ConstantLongInfo) {
            ConstantLongInfo l = (ConstantLongInfo) c;
            //children.add(new ClassTreeItem(l.getHighBytes()));
            //children.add(new ClassTreeItem(l.getLowBytes()));
        } else if (c instanceof  ConstantDoubleInfo) {
            ConstantDoubleInfo d = (ConstantDoubleInfo) c;
            //children.add(new ClassTreeItem(d.getHighBytes()));
            //children.add(new ClassTreeItem(d.getLowBytes()));
        } else if (c instanceof ConstantClassInfo) {
            ConstantClassInfo cls = (ConstantClassInfo) c;
            children.add(new ClassTreeItem(cls.getNameIndex()));
        }
    }
    
}
