package com.sdl.selenium.extjs6.button;

import com.sdl.selenium.extjs6.menu.Menu;
import com.sdl.selenium.web.WebLocator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * See split button examples <a href="http://examples.sencha.com/extjs/6.0.2/examples/kitchensink/#split-buttons">here</a>
 */
@Slf4j
public class SplitButton extends Button {

    /**
     * See split button examples <a href="http://examples.sencha.com/extjs/6.0.2/examples/kitchensink/#split-buttons">here</a>
     */
    public SplitButton() {
        setClassName("SplitButton");
    }

    public SplitButton(WebLocator container) {
        this();
        setContainer(container);
    }

    public SplitButton(WebLocator container, String text) {
        this(container);
        setText(text);
    }

    public void clickOnMenu(String... menuOptions) {
        assertReady();
        Menu menu = new Menu();
        if (menu.showMenu(this) || menu.showMenu(this)) {
            for (String val : menuOptions) {
                menu.clickOnMenu(val);
            }
        } else {
            log.debug("(" + toString() + ") The element arrow could not be located.");
        }
    }

    public List<String> getAllMenuValues() {
        Menu menu = new Menu();
        if (!menu.showMenu(this)) {
            menu.ready();
            menu.showMenu(this);
        }
        List<String> menuValues = menu.getMenuValues();
        menu.hideMenu(this);
        return menuValues;
    }
}