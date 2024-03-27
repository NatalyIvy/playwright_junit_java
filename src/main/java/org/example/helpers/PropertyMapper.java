package org.example.helpers;

import java.io.PrintStream;

public interface PropertyMapper extends AppPropertyMapper, RunPropertyMapper{
    void list(PrintStream out);
}