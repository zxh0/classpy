package com.github.zxh.classpy.luacout;

import com.github.zxh.classpy.common.BytesComponent;
import com.github.zxh.classpy.common.BytesParser;

/**
 * luac.out parser.
 */
public class LuacOutParser implements BytesParser {

    @Override
    public BytesComponent parse(byte[] bytes) {
        LuacOutReader reader = new LuacOutReader(bytes);
        LuacOutFile root = new LuacOutFile();
        root.read(reader);
        postRead(root);
        return root;
    }

    private static void postRead(LuacOutComponent parent) {
        for (BytesComponent kid : parent.getComponents()) {
            postRead((LuacOutComponent) kid);
        }
        parent.postRead();
    }

}
