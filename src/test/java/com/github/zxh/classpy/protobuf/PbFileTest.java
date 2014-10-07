package com.github.zxh.classpy.protobuf;

import com.github.zxh.classpy.protobuf.Msg.MyMsg;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;

/**
 *
 * @author zxh
 */
public class PbFileTest {
    
    @Test
    public void pb2File() throws IOException {
        MyMsg msg = MyMsg.newBuilder()
                .setFInt32(1)
                .build();
        msg.writeTo(new FileOutputStream("msg.pb"));
    }
    
    @Test
    public void pb() throws Exception {
        ClassLoader cl = PbFileTest.class.getClassLoader();
        Path pbFilePath = Paths.get(cl.getResource("msg.pb").toURI());
        byte[] pbBytes = Files.readAllBytes(pbFilePath);
        
        PbParser parser = new PbParser();
        parser.parse(pbBytes);
    }
    
}
