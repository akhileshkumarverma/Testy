package com.sdl.selenium.bootstrap.form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextField extends com.sdl.selenium.web.form.TextField {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextField.class);

    public TextField() {
        setClassName("TextField");
        setType("text"); // TODO try to move in TextField
    }

    public TextField(Locator container) {
        this();
        setContainer(container);
    }

    public TextField(Locator container, String label) {
        this(container);
        setLabel(label);
    }
}