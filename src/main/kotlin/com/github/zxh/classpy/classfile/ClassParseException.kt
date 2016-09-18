package com.github.zxh.classpy.classfile;

//@SuppressWarnings("serial")
class ClassParseException:RuntimeException {
    constructor(message:String) : super(message) {}
    constructor(cause:Throwable) : super(cause) {}
}
