package com.blogbackend.utils;

import java.io.IOException;

public class NotFoundException extends IOException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
