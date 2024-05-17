package org.example.helpers.env;

import java.io.PrintStream;

public interface PropertyMapper extends AppPropertyMapper, RunPropertyMapper{
    void list(PrintStream out);
}