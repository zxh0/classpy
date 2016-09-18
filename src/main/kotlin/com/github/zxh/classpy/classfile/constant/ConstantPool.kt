package com.github.zxh.classpy.classfile.constant;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.classfile.ClassParseException;
import com.github.zxh.classpy.classfile.reader.ClassReader;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.helper.StringHelper;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The constant pool in class file.
 */
class ConstantPool(val cpCount: U2) : ClassComponent() {

    private var constants:Array<ConstantInfo?> = arrayOf()


    override fun readContent(reader: ClassReader) {
        constants = arrayOfNulls<ConstantInfo>(cpCount.value)
        // The constant_pool table is indexed from 1 to constant_pool_count - 1.
        for (i in 1..cpCount.value - 1)
        {
            val c = readConstantInfo(reader)
            setConstantName(c, i)
            constants[i] = c
            // http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.5
            // All 8-byte constants take up two entries in the constant_pool table of the class file.
            // If a CONSTANT_Long_info or CONSTANT_Double_info structure is the item in the constant_pool
            // table at index n, then the next usable item in the pool is located at index n+2.
            // The constant_pool index n+1 must be valid but is considered unusable.
            if (c is ConstantLongInfo || c is ConstantDoubleInfo)
            {
                i++
            }
        }
        loadConstantDesc()
        reader.constantPool = this
    }
    
    private fun readConstantInfo(reader: ClassReader): ConstantInfo {
        val tag = reader.getByte(reader.position);
        
        val ci = ConstantFactory.create(tag);
        ci.read(reader);
        
        return ci;
    }
    
    // like #32: (Utf8)
    private fun setConstantName(constant: ConstantInfo, idx: Int) {
        val idxStr = StringHelper.formatIndex(cpCount.value, idx);
        val constantName = constant.javaClass.simpleName
                .replace("Constant", "")
                .replace("Info", "");
        constant.name = idxStr + " (" + constantName + ")"
    }
    
    private fun loadConstantDesc() {
        for (c in constants) {
            if (c != null) {
                c.desc = c.loadDesc(this)
            }
        }
    }

//    @Override
//    public List<ClassComponent> getSubComponents() {
//        return Arrays.stream(constants)
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//    }

    fun getUtf8String(index: Int): String {
        return getConstant(ConstantUtf8Info::class.java, index).getString();
    }
    
    fun getUtf8Info(index: Int): ConstantUtf8Info {
        return getConstant(ConstantUtf8Info::class.java, index);
    }
    
    fun getClassInfo(index: Int): ConstantClassInfo {
        return getConstant(ConstantClassInfo::class.java, index);
    }
    
    fun getNameAndTypeInfo(index: Int): ConstantNameAndTypeInfo {
        return getConstant(ConstantNameAndTypeInfo::class.java, index);
    }
    
    fun getFieldrefInfo(index: Int): ConstantFieldrefInfo {
        return getConstant(ConstantFieldrefInfo::class.java, index);
    }
    
    fun getMethodrefInfo(index: Int): ConstantMethodrefInfo {
        return getConstant(ConstantMethodrefInfo::class.java, index);
    }
    
    fun getInterfaceMethodrefInfo(index: Int): ConstantInterfaceMethodrefInfo {
        return getConstant(ConstantInterfaceMethodrefInfo::class.java, index);
    }

    private fun <T> getConstant(classOfT:Class<T>, index:Int):T {
        val c = constants[index]
        if (c.getClass() !== classOfT)
        {
            throw ClassParseException("Constant#" + index
                    + " is not " + classOfT.getSimpleName() + "!")
        }
        return classOfT.cast(c)
    }

    fun getConstantDesc(index:Int):String {
        val c = constants[index]
        return c.desc!!
    }
    
}
