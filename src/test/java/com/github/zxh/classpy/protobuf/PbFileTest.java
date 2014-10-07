package com.github.zxh.classpy.protobuf;

import com.github.zxh.classpy.protobuf.Msg.MyEnum;
import com.github.zxh.classpy.protobuf.Msg.MyMsg;
import com.github.zxh.classpy.protobuf.Msg.Scalars;
import com.google.protobuf.ByteString;
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
        Scalars noOptionals = Scalars.newBuilder()
                .setFBool(true)
                .build();
        
        Scalars nonInts = Scalars.newBuilder()
                .setFBool(true)
                .setFFloat(3.14f)
                .setFDouble(2.71828)
                .setFString("protobuf")
                .setFBytes(ByteString.copyFromUtf8("protobuf"))
                .build();
        
        Scalars smallInts = Scalars.newBuilder()
                .setFBool(false)
                .setFInt32(1)
                .setFUint32(2)
                .setFSint32(3)
                .setFFixed32(4)
                .setFSfixed32(5)
                .setFInt64(6)
                .setFUint64(7)
                .setFSint64(8)
                .setFFixed64(9)
                .setFSfixed64(10)
                .build();
        
        MyMsg msg = MyMsg.newBuilder()
                .setNoOptionals(noOptionals)
                .setNonInts(nonInts)
                .setSmallInts(smallInts)
                .setBigInts(smallInts) // todo
                .setMyEnum(MyEnum.ONE)
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
