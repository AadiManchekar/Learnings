package com.aadi.demo1.controller;

import java.beans.PropertyEditorSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirstNamePropertyEditor extends PropertyEditorSupport{

    @Override
    public void setAsText(String text) throws java.lang.IllegalArgumentException {
        log.info("inside FirstNamePropertyEditor");
        setValue(text.trim().toLowerCase());
    }
}
