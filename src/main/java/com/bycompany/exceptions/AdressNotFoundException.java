package com.bycompany.exceptions;

import java.io.FileNotFoundException;

public class AdressNotFoundException extends FileNotFoundException {
    public AdressNotFoundException(String s) {
        super(s);
    }
}
