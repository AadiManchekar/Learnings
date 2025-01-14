package com.aadi.demo1.controller;

import java.beans.PropertyEditorSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LastNamePropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws java.lang.IllegalArgumentException {
        log.info("inside LastNamePropertyEditor");
        setValue(text.trim().toLowerCase());
    }
}
