package com.github.zxh.classpy.classfile.attribute;

import com.github.zxh.classpy.classfile.ClassFileComponent;
import com.github.zxh.classpy.classfile.datatype.U2;
import com.github.zxh.classpy.classfile.jvm.AccessFlagType;

/*
Module_attribute {
    u2 attribute_name_index;
    u4 attribute_length;

    u2 module_name_index;
    u2 module_flags;
    u2 module_version_index;

    u2 requires_count;
    {   u2 requires_index;
        u2 requires_flags;
        u2 requires_version_index;
    } requires[requires_count];

    u2 exports_count;
    {   u2 exports_index;
        u2 exports_flags;
        u2 exports_to_count;
        u2 exports_to_index[exports_to_count];
    } exports[exports_count];

    u2 opens_count;
    {   u2 opens_index;
        u2 opens_flags;
        u2 opens_to_count;
        u2 opens_to_index[opens_to_count];
    } opens[opens_count];

    u2 uses_count;
    u2 uses_index[uses_count];

    u2 provides_count;
    {   u2 provides_index;
        u2 provides_with_count;
        u2 provides_with_index[provides_with_count];
    } provides[provides_count];
}
 */
public class ModuleAttribute extends AttributeInfo {

    {
        u2cp ("module_name_index");
        u2   ("module_flags");
        u2cp ("module_version_index");
        u2   ("requires_count");
        table("requires", Require.class);
        u2   ("exports_count");
        table("exports", Export.class);
        u2   ("opens_count");
        table("opens", Open.class);
        u2   ("uses_count");
        table("uses_index", U2.class);
        u2   ("provides_count");
        table("provides", Provide.class);
    }

    public static class Require extends ClassFileComponent {

        {
            u2cp("requires_index");
            u2af("requires_flags", AccessFlagType.AF_REQUIRES);
            u2cp("requires_version_index");
        }

    }

    public static class Export extends ClassFileComponent {

        {
            u2   ("exports_index");
            u2   ("exports_flags");
            u2   ("exports_to_count");
            table("exports_to", U2.class); ;
        }

    }

    public static class Open extends ClassFileComponent {

        {
            u2   ("opens_index");
            u2   ("opens_flags");
            u2   ("opens_to_count");
            table("opens_to_index", U2.class);
        }

    }

    public static class Provide extends ClassFileComponent {

        {
            u2   ("provides_index");
            u2   ("provides_with_count");
            table("provides_with_index", U2.class);
        }

    }

}
