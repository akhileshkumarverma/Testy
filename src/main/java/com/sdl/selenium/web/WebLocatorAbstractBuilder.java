package com.sdl.selenium.web;

import com.sdl.selenium.web.utils.Utils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * This class is used to simple construct xpath for WebLocator's
 */
public abstract class WebLocatorAbstractBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLocatorAbstractBuilder.class);

    private XPathBuilder pathBuilder = new XPathBuilder();

    public XPathBuilder getPathBuilder() {
        return pathBuilder;
    }

//    public void setPathBuilder(XPathBuilder pathBuilder) {
//        this.pathBuilder = pathBuilder;
//    }

//    private String className = "WebLocator";
//    private String tag = "*";
//    private String elPath;
//    private String baseCls;
//    private String cls;
//    private List<String> classes;
//    private List<String> excludeClasses;
//    private String name;
//    private String text;
//    protected List<SearchType> defaultSearchTextType = new ArrayList<SearchType>();
//    private Set<SearchType> searchTextType = WebLocatorConfig.getSearchTextType();
//    private List<SearchType> searchLabelType = new ArrayList<SearchType>();
//    private String style;
//    private String elCssSelector;
//    private String title;
//    private Map<String, String> templates = new LinkedHashMap<String, String>();
//    private Map<String, String> templatesValues = new LinkedHashMap<String, String>();
//    private Map<String, String> elPathSuffix = new LinkedHashMap<String, String>();

//    private String infoMessage;

//    private String label;
//    private String labelTag = "label";
//    private String labelPosition = WebLocatorConfig.getDefaultLabelPosition();

//    private int position = -1;

    //private int elIndex; // TODO try to find how can be used

//    private boolean visibility;
//    private long renderMillis = WebLocatorConfig.getDefaultRenderMillis();
//    private int activateSeconds = 60;

//    private WebLocator container;
//    private List<WebLocator> childNodes;

    protected WebLocatorAbstractBuilder() {
        setTemplate("visibility", "count(ancestor-or-self::*[contains(@style, 'display: none')]) = 0");
        setTemplate("id", "@id='%s'");
        setTemplate("name", "@name='%s'");
        setTemplate("class", "contains(concat(' ', @class, ' '), ' %s ')");
        setTemplate("excludeClass", "not(contains(@class, '%s'))");
        setTemplate("cls", "@class='%s'");
    }

    // =========================================
    // ========== setters & getters ============
    // =========================================

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setTag(String)}
     * <p>tag (type of DOM element)</p>
     * <pre>default to "*"</pre>
     */
    public String getTag() {
        return pathBuilder.getTag();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     *
     * @param tag (type of DOM element) eg. input or h2
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setTag(final String tag) {
        pathBuilder.setTag(tag);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setId(String)}
     */
    public String getId() {
        return pathBuilder.getId();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     * use new WebLocator(By.id("ID"))
     *
     * @param id eg. id="buttonSubmit"
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setId(final String id) {
        pathBuilder.setId(id);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setElPath(String)}
     * <p>returned value does not include containers path</p>
     */
    public String getElPath() {
        return pathBuilder.getElPath();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     * Once used all other attributes will be ignored. Try using this class to a minimum!
     *
     * @param elPath absolute way (xpath) to identify element
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setElPath(final String elPath) {
        pathBuilder.setElPath(elPath);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setBaseCls(String)}
     */
    public String getBaseCls() {
        return pathBuilder.getBaseCls();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     *
     * @param baseCls base class
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setBaseCls(final String baseCls) {
        pathBuilder.setBaseCls(baseCls);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setCls(String)}
     */
    public String getCls() {
        return pathBuilder.getCls();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     * <p>Find element with <b>exact math</b> of specified class (equals)</p>
     *
     * @param cls class of element
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setCls(final String cls) {
        pathBuilder.setCls(cls);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     * <p>Example:</p>
     * <pre>
     *     WebLocator element = new WebLocator().setClasses("bg-btn", "new-btn");
     * </pre>
     *
     * @return value that has been set in {@link #setClasses(String...)}
     */
    public List<String> getClasses() {
        return pathBuilder.getClasses();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)</b></p>
     * <p>Use it when element must have all specified css classes (order is not important).</p>
     * <ul>
     * <li>Provided classes must be conform css rules.</li>
     * </ul>
     *
     * @param classes list of classes
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setClasses(final String... classes) {
        pathBuilder.setClasses(classes);
        return (T) this;
    }

    public List<WebLocator> getChildNodes() {
        return pathBuilder.getChildNodes();
    }

    public <T extends WebLocatorAbstractBuilder> T setChildNotes(final WebLocator... childNotes) {
        pathBuilder.setChildNotes(childNotes);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setExcludeClasses(String...)}
     */
    public List<String> getExcludeClasses() {
        return pathBuilder.getExcludeClasses();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     *
     * @param excludeClasses list of class to be excluded
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setExcludeClasses(final String... excludeClasses) {
        pathBuilder.setExcludeClasses(excludeClasses);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setName(String)}
     */
    public String getName() {
        return pathBuilder.getName();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     *
     * @param name eg. name="buttonSubmit"
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setName(final String name) {
        pathBuilder.setName(name);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setText(String, SearchType...)}
     */
    public String getText() {
        return pathBuilder.getText();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     *
     * @param text       with which to identify the item
     * @param searchType type search text element: see more details see {@link SearchType}
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setText(final String text, final SearchType... searchType) {
        pathBuilder.setText(text, searchType);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setSearchTextType(SearchType...)}
     */
    public Set<SearchType> getSearchTextType() {
        return pathBuilder.getSearchTextType();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     *
     * @param searchTextType accepted values are: {@link SearchType#EQUALS}
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setSearchTextType(SearchType... searchTextType) {
        pathBuilder.setSearchTextType(searchTextType);
        return (T) this;
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     *
     * @param searchLabelType accepted values are: {@link SearchType}
     * @return this element
     */
    private <T extends WebLocatorAbstractBuilder> T setSearchLabelType(SearchType... searchLabelType) {
        pathBuilder.setSearchLabelType(searchLabelType);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setStyle(String)}
     */
    public String getStyle() {
        return pathBuilder.getStyle();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     *
     * @param style of element
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setStyle(final String style) {
        pathBuilder.setStyle(style);
        return (T) this;
    }

    /**
     * <p><b>not implemented yet</b></p>
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setElCssSelector(String)}
     */
    public String getElCssSelector() {
        return pathBuilder.getElCssSelector();
    }

    /**
     * <p><b>not implemented yet</b></p>
     * <p><b>Used for finding element process (to generate css address)<b></p>
     *
     * @param elCssSelector cssSelector
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setElCssSelector(final String elCssSelector) {
        pathBuilder.setElCssSelector(elCssSelector);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     * <p><b>Title only applies to Panel, and if you set the item "setTemplate("title", "text()='%s'")" a template.<b></p>
     *
     * @return value that has been set in {@link #setTitle(String)}
     */
    public String getTitle() {
        return pathBuilder.getTitle();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     * <p><b>Title only applies to Panel, and if you set the item "setTemplate("title", "text()='%s'")" a template.<b></p>
     *
     * @param title of element
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setTitle(String title) {
        pathBuilder.setTitle(title);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setElPathSuffix(String)}
     */
    public String getElPathSuffix() {
        return pathBuilder.getElPathSuffix();
    }

    /**
     * @param elPathSuffix additional identification xpath element that will be added at the end
     * @return this element
     * @deprecated use setElPathSuffix(String key, String elPathSuffix)
     */
    public <T extends WebLocatorAbstractBuilder> T setElPathSuffix(String elPathSuffix) {
        pathBuilder.setElPathSuffix(elPathSuffix);
        return (T) this;
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     * <p>Example:</p>
     * <pre>
     *     TODO
     * </pre>
     *
     * @param key          suffix key
     * @param elPathSuffix additional identification xpath element that will be added at the end
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setElPathSuffix(String key, String elPathSuffix) {
        pathBuilder.setElPathSuffix(key, elPathSuffix);
        return (T) this;
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     * <p>Example:</p>
     * <pre>
     *     TODO
     * </pre>
     *
     * @param key   suffix key
     * @param value
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setTemplateValue(String key, String value) {
        pathBuilder.setTemplateValue(key, value);
        return (T) this;
    }

    /**
     * For customize template please see here: See http://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#dpos
     *
     * @param key   name template
     * @param value template
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setTemplate(String key, String value) {
        pathBuilder.setTemplate(key, value);
        return (T) this;
    }

    public <T extends WebLocatorAbstractBuilder> T addToTemplate(String key, String value) {
        pathBuilder.addToTemplate(key, value);
        return (T) this;
    }

    public String getTemplate(String key) {
        return pathBuilder.getTemplate(key);
    }

    /**
     * <p><b><i>Used in logging process</i><b></p>
     *
     * @return value that has been set in {@link #setInfoMessage(String)}
     */
    public String getInfoMessage() {
        return pathBuilder.getInfoMessage();
    }

    /**
     * <p><b><i>Used in logging process</i><b></p>
     *
     * @param infoMessage info Message
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setInfoMessage(final String infoMessage) {
        pathBuilder.setInfoMessage(infoMessage);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setVisibility(boolean)}
     */
    public boolean isVisibility() {
        return pathBuilder.isVisibility();
    }

    public <T extends WebLocatorAbstractBuilder> T setVisibility(final boolean visibility) {
        pathBuilder.setVisibility(visibility);
        return (T) this;
    }

    public long getRenderMillis() {
        return pathBuilder.getRenderMillis();
    }

    public <T extends WebLocatorAbstractBuilder> T setRenderMillis(final long renderMillis) {
        pathBuilder.setRenderMillis(renderMillis);
        return (T) this;
    }

    /**
     * @param renderSeconds
     * @param <T>
     * @return
     * @deprecated use setRenderMillis
     */
    public <T extends WebLocatorAbstractBuilder> T setRenderSeconds(final int renderSeconds) {
        pathBuilder.setRenderMillis(renderSeconds * 1000);
        return (T) this;
    }

    public int getActivateSeconds() {
        return pathBuilder.getActivateSeconds();
    }

    public <T extends WebLocatorAbstractBuilder> T setActivateSeconds(final int activateSeconds) {
        pathBuilder.setActivateSeconds(activateSeconds);
        return (T) this;
    }

    // TODO verify what type must return
    @Deprecated
    public WebLocator getContainer() {
        return pathBuilder.getContainer();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     *
     * @param container parent containing element.
     * @return this element
     * @deprecated
     */
    public <T extends WebLocatorAbstractBuilder> T setContainer(WebLocator container) {
        pathBuilder.setContainer(container);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setLabel(String, SearchType...)}
     */
    public String getLabel() {
        return pathBuilder.getLabel();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     *
     * @param label text label element
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setLabel(String label, final SearchType... searchType) {
        pathBuilder.setLabel(label, searchType);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setLabel(String, SearchType...)}
     */
    public String getLabelTag() {
        return pathBuilder.getLabelTag();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     *
     * @param labelTag label tag element
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setLabelTag(String labelTag) {
        pathBuilder.setLabelTag(labelTag);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setLabelPosition(String)}
     */
    public String getLabelPosition() {
        return pathBuilder.getLabelPosition();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     *
     * @param labelPosition position of this element reported to label
     * @return this element
     * @see <a href="http://www.w3schools.com/xpath/xpath_axes.asp">http://www.w3schools.com/xpath/xpath_axes.asp"</a>
     */
    public <T extends WebLocatorAbstractBuilder> T setLabelPosition(String labelPosition) {
        pathBuilder.setLabelPosition(labelPosition);
        return (T) this;
    }

    /**
     * <p><b><i>Used for finding element process (to generate xpath address)</i><b></p>
     *
     * @return value that has been set in {@link #setPosition(int)}
     */
    public int getPosition() {
        return pathBuilder.getPosition();
    }

    /**
     * <p><b>Used for finding element process (to generate xpath address)<b></p>
     * <p>Result Example:</p>
     * <pre>
     *     //*[contains(@class, 'x-grid-panel')][position() = 1]
     * </pre>
     *
     * @param position starting index = 1
     * @return this element
     */
    public <T extends WebLocatorAbstractBuilder> T setPosition(int position) {
        pathBuilder.setPosition(position);
        return (T) this;
    }

    // =========================================
    // =============== Methods =================
    // =========================================

    /**
     * <p>Used only to identify class type of current object</p>
     * <p> Not used for css class!</p>
     *
     * @return string
     */
    public String getClassName() {
        return pathBuilder.getClassName();
    }

    protected void setClassName(final String className) {
        pathBuilder.setClassName(className);
    }

    protected boolean hasCls() {
        return pathBuilder.hasCls();
    }

    protected boolean hasClasses() {
        return pathBuilder.hasClasses();
    }

    protected boolean hasChildNodes() {
        return pathBuilder.hasChildNodes();
    }

    protected boolean hasExcludeClasses() {
        return pathBuilder.hasExcludeClasses();
    }

    protected boolean hasBaseCls() {
        return pathBuilder.hasBaseCls();
    }

    protected boolean hasName() {
        return pathBuilder.hasName();
    }

    protected boolean hasText() {
        return pathBuilder.hasText();
    }

    protected boolean hasStyle() {
        return pathBuilder.hasStyle();
    }

    protected boolean hasElPath() {
        return pathBuilder.hasElPath();
    }

    protected boolean hasTag() {
        return pathBuilder.hasTag();
    }

    protected boolean hasElCssSelector() {
        return pathBuilder.hasElCssSelector();
    }

    protected boolean hasLabel() {
        return pathBuilder.hasLabel();
    }

    protected boolean hasTitle() {
        return pathBuilder.hasTitle();
    }

    protected boolean hasPosition() {
        return pathBuilder.hasPosition();
    }

    // =========================================
    // ============ XPath Methods ==============
    // =========================================

    /**
     * Containing baseCls, class, name and style
     *
     * @return baseSelector
     */
    protected String getBasePathSelector() {
        // TODO use disabled
        // TODO verify what need to be equal OR contains
        List<String> selector = new ArrayList<String>();
        CollectionUtils.addIgnoreNull(selector, getBasePath());
        CollectionUtils.addIgnoreNull(selector, getItemPathText());

        if (!WebDriverConfig.isIE()) {
            if (hasStyle()) {
                selector.add("contains(@style ,'" + getStyle() + "')");
            }
            // TODO make specific for WebLocator
            if (isVisibility()) {
//               TODO selector.append(" and count(ancestor-or-self::*[contains(replace(@style, '\s*:\s*', ':'), 'display:none')]) = 0");
                CollectionUtils.addIgnoreNull(selector, applyTemplate("visibility", isVisibility()));
            }
        }

        return selector.isEmpty() ? "" : StringUtils.join(selector, " and ");
    }

    protected String getBasePath() {
        List<String> selector = new ArrayList<String>();
        if (hasName()) {
            selector.add(applyTemplate("name", getName()));
        }
        if (hasBaseCls()) {
            selector.add(applyTemplate("class", getBaseCls()));
        }
        if (hasCls()) {
            selector.add(applyTemplate("cls", getCls()));
        }
        if (hasClasses()) {
            for (String cls : getClasses()) {
                selector.add(applyTemplate("class", cls));
            }
        }
        if (hasExcludeClasses()) {
            for (String excludeClass : getExcludeClasses()) {
                selector.add(applyTemplate("excludeClass", excludeClass));
            }
        }
        if (hasTitle()) {
            addTemplate(selector, "title", getTitle());
        }
        for (Map.Entry<String, String> entry : pathBuilder.getTemplatesValues().entrySet()) {
            addTemplate(selector, entry.getKey(), entry.getValue());
        }
        for (String suffix : pathBuilder.getElPathsSuffix().values()) {
            selector.add(suffix);
        }
        addChildNotesToSelector(selector);
        return selector.isEmpty() ? null : StringUtils.join(selector, " and ");
    }

    private void addChildNotesToSelector(List<String> selector) {
        if (hasChildNodes()) {
            for (WebLocator el : getChildNodes()) {
                WebLocator breakElement = null;
                WebLocator elIterator = el;
                while (elIterator.getContainer() != null && breakElement == null) {
                    if (elIterator.getContainer() == this) {
                        elIterator.setContainer(null);
                        breakElement = elIterator;
                    } else {
                        elIterator = elIterator.getContainer();
                    }
                }
                selector.add("count(." + el.getPath() + ") > 0");
                if (breakElement != null) {
                    breakElement.setContainer((WebLocator) this);
                }
            }
        }
    }

    private void addTemplate(List<String> selector, String key, Object... arguments) {
        String tpl = applyTemplate(key, arguments);
        if (StringUtils.isNotEmpty(tpl)) {
            selector.add(tpl);
        }
    }

    protected String applyTemplate(String key, Object... arguments) {
        String tpl = pathBuilder.getTemplate(key);
        if (StringUtils.isNotEmpty(tpl)) {
            return String.format(tpl, arguments);
        }
        return null;
    }

    /**
     * this method is meant to be overridden by each component
     *
     * @param disabled disabled
     * @return String
     */
    protected String getItemPath(boolean disabled) {
        String selector = getBaseItemPath();
        if (!disabled) {
            String enabled = applyTemplate("enabled", getTemplate("enabled"));
            if (enabled != null) {
                selector += StringUtils.isNotEmpty(selector) ? " and " + enabled : enabled;
            }
        }
        selector = "//" + getTag() + (StringUtils.isNotEmpty(selector) ? "[" + selector + "]" : "");
        return selector;
    }

    /**
     * Construct selector if WebLocator has text
     *
     * @return String
     */
    protected String getItemPathText() {
        String selector = null;
        if (hasText()) {
            selector = "";
            String text = getText();

            if (pathBuilder.getTemplate("text") != null) {
                return String.format(pathBuilder.getTemplate("text"), text);
            }

            boolean hasContainsAll = pathBuilder.getSearchTextType().contains(SearchType.CONTAINS_ALL);
            if (!(hasContainsAll || pathBuilder.getSearchTextType().contains(SearchType.CONTAINS_ANY))) {
                text = Utils.getEscapeQuotesText(text);
            }
            String pathText = "text()";

            boolean isDeepSearch = pathBuilder.getSearchTextType().contains(SearchType.DEEP_CHILD_NODE) || pathBuilder.getSearchTextType().contains(SearchType.DEEP_CHILD_NODE_OR_SELF);
            boolean useChildNodesSearch = isDeepSearch || pathBuilder.getSearchTextType().contains(SearchType.CHILD_NODE);
            if (useChildNodesSearch) {
                selector += "count(" + (isDeepSearch ? "*//" : "") + "text()[";
                pathText = ".";
            }

            selector += getTextSearchTypePath(text, hasContainsAll, pathText);

            if (useChildNodesSearch) {
                selector += "]) > 0";
            }

            if (pathBuilder.getSearchTextType().contains(SearchType.DEEP_CHILD_NODE_OR_SELF)) {
                String selfPath = getTextSearchTypePath(text, hasContainsAll, "text()");
                selector = "(" + selfPath + " or " + selector + ")";
            }

            if (pathBuilder.getSearchTextType().contains(SearchType.HTML_NODE)) {
                String a = "normalize-space(concat(./*[1]//text(), ' ', text()[1], ' ', ./*[2]//text(), ' ', text()[2], ' ', ./*[3]//text(), ' ', text()[3], ' ', ./*[4]//text(), ' ', text()[4], ' ', ./*[5]//text(), ' ', text()[5]))=" + text;
                String b = "normalize-space(concat(text()[1], ' ', ./*[1]//text(), ' ', text()[2], ' ', ./*[2]//text(), ' ', text()[3], ' ', ./*[3]//text(), ' ', text()[4], ' ', ./*[4]//text(), ' ', text()[5], ' ', ./*[5]//text()))=" + text;

                selector = "(" + a + " or " + b + ")";
            }
        }
        return selector;
    }

    private String getTextSearchTypePath(String text, boolean hasContainsAll, String pathText) {
        String selector;
        if (pathBuilder.getSearchTextType().contains(SearchType.TRIM)) {
            pathText = "normalize-space(" + pathText + ")";
        }

        if (pathBuilder.getSearchTextType().contains(SearchType.EQUALS)) {
            selector = pathText + "=" + text;
        } else if (pathBuilder.getSearchTextType().contains(SearchType.STARTS_WITH)) {
            selector = "starts-with(" + pathText + "," + text + ")";
        } else if (hasContainsAll || pathBuilder.getSearchTextType().contains(SearchType.CONTAINS_ANY)) {
            String splitChar = String.valueOf(text.charAt(0));
            Pattern pattern = Pattern.compile(Pattern.quote(splitChar));
            String[] strings = pattern.split(text.substring(1));
            for (int i = 0; i < strings.length; i++) {
                strings[i] = "contains(" + pathText + ",'" + strings[i] + "')";
            }
            String operator = hasContainsAll ? " and " : " or ";
            selector = hasContainsAll ? StringUtils.join(strings, operator) : "(" + StringUtils.join(strings, operator) + ")";
        } else {
            selector = "contains(" + pathText + "," + text + ")";
        }
        return selector;
    }

    private String getBaseItemPath() {
        return getBasePathSelector();
    }

    /**
     * @return final xpath (including containers xpath), used for interacting with browser
     */
    public final String getPath() {
        return pathBuilder.getPath(false);
    }

    /**
     * @param disabled disabled
     * @return String
     */
    public String getPath(boolean disabled) {
        String returnPath;
        if (hasElPath()) {
            returnPath = getElPath();

            String baseItemPath = getBaseItemPath();
            if (baseItemPath != null && !baseItemPath.equals("")) {
                // TODO "inject" baseItemPath to elPath
//                logger.warn("TODO must inject: \"" + baseItemPath + "\" in \"" + returnPath + "\"");
            }
        } else {
            returnPath = getItemPath(disabled);
        }

        returnPath = afterItemPathCreated(returnPath);
        if (disabled) {
            returnPath += applyTemplate("disabled", getTemplate("disabled"));
        }

        // add container path
        if (getContainer() != null) {
            returnPath = getContainer().getPath() + returnPath;
        }

//        logger.debug(returnPath);
        return returnPath;
    }

    @Override
    public String toString() {
        String info = getInfoMessage();
        if (info == null || "".equals(info)) {
            info = itemToString();
        }
        if (WebLocatorConfig.isLogUseClassName() && !getClassName().equals(info)) {
            info += " - " + getClassName();
        }
        // add container path
        if (WebLocatorConfig.isLogContainers() && getContainer() != null) {
            info = getContainer().toString() + " -> " + info;
        }
        return info;
    }

    public String itemToString() {
        String info;
        if (hasText()) {
            info = getText();
        } else if (hasName()) {
            info = getName();
        } else if (hasClasses()) {
            List<String> classes = pathBuilder.getClasses();
            info = classes.size() == 1 ? classes.get(0) : classes.toString();
        } else if (hasCls()) {
            info = getCls();
        } else if (hasLabel()) {
            info = getLabel();
        } else if (hasTitle()) {
            info = getTitle();
        } else if (hasBaseCls()) {
            info = getBaseCls();
        } else if (hasElPath()) {
            info = getElPath();
        } else if (hasTag()) {
            info = getTag();
        } else {
            info = getClassName();
        }
        return info;
    }


    protected String afterItemPathCreated(String itemPath) {
        if (hasLabel()) {
            // remove '//' because labelPath already has and include
            if (itemPath.indexOf("//") == 0) {
                itemPath = itemPath.substring(2);
            }
            itemPath = getLabelPath() + getLabelPosition() + itemPath;
        }
        itemPath = addPositionToPath(itemPath);
        return itemPath;
    }

    protected String addPositionToPath(String itemPath) {
        if (hasPosition()) {
            itemPath += "[position() = " + getPosition() + "]";
        }
        return itemPath;
    }

    protected String getLabelPath() {
        if (pathBuilder.getSearchLabelType().size() == 0) {
            pathBuilder.getSearchLabelType().add(SearchType.EQUALS);
        }
        SearchType[] st = new SearchType[pathBuilder.getSearchLabelType().size()];
        for (int i = 0; i < pathBuilder.getSearchLabelType().size(); i++) {
            st[i] = pathBuilder.getSearchLabelType().get(i);
        }
        return new WebLocator().setText(getLabel(), st).setTag(getLabelTag()).getPath();
    }
}