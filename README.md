# Classpy

Classpy is a GUI tool for investigating Java class files.

## Inspiration

This tool is mainly inspired by [javap](http://docs.oracle.com/javase/8/docs/technotes/tools/windows/javap.html) and [JavaClassViewer](http://www.codeproject.com/Articles/35915/Java-Class-Viewer). I reinvent the wheel for the following two reasons:

    1. Learn Java class file format and bytecode through parsing it
    2. Try JavaFX 8

## Features

* Is able to parse class files described by [JVMS8](http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html)
* Displays class file as tree and hex text
* The corresponding hex text is highlighted when you select a tree node

## Build
```shell
cd path/to/classpy
gradle uberjar
```

## Run
```shell
cd path/to/classpy
gradle run
```

## Screenshots

![Screenshot1](https://raw.githubusercontent.com/zxh0/classpy/master/screenshot.png)
![Screenshot2](https://raw.githubusercontent.com/zxh0/classpy/master/screenshot2.png)
