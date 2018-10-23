# Classpy

Classpy is a GUI tool for investigating Java class file, Lua binary chunk, Wasm binary code, and other binary file formats.

## Inspiration

This tool is mainly inspired by [javap](http://docs.oracle.com/javase/8/docs/technotes/tools/windows/javap.html) and [JavaClassViewer](http://www.codeproject.com/Articles/35915/Java-Class-Viewer). I reinvent the wheel for the following two reasons:

    1. Learn Java class file format and bytecode through parsing it
    2. Try JavaFX 8

## Features

* Understands class files described by [JVMS9](https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-4.html)
* Supports [Lua](https://www.lua.org/) 5.3 binary chunk format
* Supports [Wasm](https://webassembly.org/) binary format
* Supports [Bitcoin](https://en.wikipedia.org/wiki/Bitcoin) raw block and transaction format
* Displays parsed class file as tree and hex text
* The corresponding hex text is highlighted when you select a tree node

## Build
```shell
cd path/to/classpy
./gradlew uberjar
```

## Run
```shell
cd path/to/classpy
./gradlew run
```

## Screenshots

![Screenshot1](https://raw.githubusercontent.com/zxh0/classpy/master/screenshot.png)
![Screenshot2](https://raw.githubusercontent.com/zxh0/classpy/master/screenshot2.png)
![Screenshot3](https://raw.githubusercontent.com/zxh0/classpy/master/screenshot3.png)
![Screenshot4](https://raw.githubusercontent.com/zxh0/classpy/master/screenshot4.png)
