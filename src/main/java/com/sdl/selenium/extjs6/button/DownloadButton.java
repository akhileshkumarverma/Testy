package com.sdl.selenium.extjs6.button;

import com.sdl.selenium.bootstrap.button.Download;
import com.sdl.selenium.web.SearchType;

public class DownloadButton extends SplitButton implements Download {

    public DownloadButton() {
        setClassName("DownloadButton");
    }

    public DownloadButton(Locator container) {
        this();
        setContainer(container);
    }

    public DownloadButton(Locator container, String text) {
        this(container);
        setText(text, SearchType.EQUALS);
    }

    /**
     * if WebDriverConfig.isSilentDownload() is true, se face silentDownload, is is false se face download with AutoIT.
     * Download file with AutoIT, works only on FireFox. SilentDownload works FireFox and Chrome
     * Use only this: button.download("C:\\TestSet.tmx");
     * return true if the downloaded file is the same one that is meant to be downloaded, otherwise returns false.
     *
     * @param fileName e.g. "TestSet.tmx"
     */
    @Override
    public boolean download(String fileName) {
        openBrowse();
        return executor().download(fileName, 10000L);
    }

    /**
     * if WebDriverConfig.isSilentDownload() is true, se face silentDownload, is is false se face download with AutoIT.
     * @param name e.g. TBX
     * @param fileName e.g. "TestSet.tmx"
     * @return true if the downloaded file is the same one that is meant to be downloaded, otherwise returns false.
     */
    public boolean downloadFromMenu(String name, String fileName) {
        clickOnMenu(name);
        return executor().download(fileName, 10000L);
    }

    private void openBrowse() {
        executor().browse(this);
    }
}