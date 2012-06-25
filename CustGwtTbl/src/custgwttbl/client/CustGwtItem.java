/*
 * CustGwtItem.java        1.0.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 *
 * Author: Sanjib Acharya
 */

package custgwttbl.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is the item which will be displayed , in a grid it will be a grid cell.
 */

public class CustGwtItem extends Composite {

    private VerticalPanel vPanel = new VerticalPanel();
    private ItemConstant specifier;
    private Widget innerWidget;
    public static final ItemConstant NON_EDITABLE = new ItemConstant();
    public static final ItemConstant EDITABLE = new ItemConstant();
    public static final ItemConstant NON_EDITABLE_HTML = new ItemConstant();
    public static final ItemConstant IMAGE = new ItemConstant();
    private AbsolutePanel imagePanel;
    private Image image;
    private CustGwtItemBean itemBean;

    public static class ItemConstant {

	private ItemConstant() {

	}
    }

    /**
     * Creates a item.
     * 
     * @param specifier
     *            ItemConstant
     * @param newItemBean
     *            the bean used to create this item
     */
    public CustGwtItem(ItemConstant specifier, CustGwtItemBean newItemBean) {

	this.specifier = specifier;
	this.itemBean = newItemBean;
	if (specifier == CustGwtItem.EDITABLE) {
	    TextBox tBox = new TextBox();
	    vPanel.add(tBox);
	    this.innerWidget = tBox;
	    if (this.itemBean.getItemHeight() != -1) {
		setItemHeight(this.itemBean.getChangedHeightInPixel());
	    }
	    tBox.addStyleName(CustGwtUtil.CUST_GWT_ITEM_EDITABLE_STYLE);
	} else if (specifier == CustGwtItem.NON_EDITABLE_HTML) {
	    HTML htmlWid = new HTML();
	    vPanel.add(htmlWid);
	    this.innerWidget = htmlWid;
	    setTextAlign(this.itemBean.getTextAlign());
	    if (this.itemBean.getItemHeight() != -1) {
		setItemHeight(this.itemBean.getHeightInPixel());
	    }
	    htmlWid.addStyleName(CustGwtUtil.CUST_GWT_ITEM_NON_EDITABLE_HTML_STYLE);
	} else if (specifier == CustGwtItem.IMAGE) {
	    imagePanel = new AbsolutePanel();
	    vPanel.add(imagePanel);
	    this.innerWidget = imagePanel;
	    if (this.itemBean.getItemHeight() != -1) {
		setItemHeight(this.itemBean.getHeightInPixel());
	    }
	    imagePanel.addStyleName(CustGwtUtil.CUST_GWT_ITEM_IMAGE_STYLE);
	} else {
	    Label label = new Label();
	    this.innerWidget = label;
	    vPanel.add(label);
	    setTextAlign(this.itemBean.getTextAlign());
	    if (this.itemBean.getItemHeight() != -1) {
		setItemHeight(this.itemBean.getHeightInPixel());
	    }
	    label.addStyleName(CustGwtUtil.CUST_GWT_ITEM_NON_EDITABLE_STYLE);
	}
	decorateTableCell();
	vPanel.setWidth("100%");
	vPanel.setHeight("100%");
	initWidget(vPanel);
	this.addStyleName(CustGwtUtil.CUST_GWT_ITEM_STYLE);
    }

    /**
     * Sets the item width
     * 
     * @param width
     *            item width
     */
    public void setItemWidth(String width) {

	vPanel.getWidget(0).setWidth(width);
    }

    /**
     * Sets the item height
     * 
     * @param height
     *            item height
     */
    public void setItemHeight(String height) {

	vPanel.getWidget(0).setHeight(height);
    }

    private void decorateTableCell() {

	if (this.itemBean.getItemLength() != -1) {
	    setItemWidth(this.itemBean.getLengthInPixel());
	}
	setWordWrap(this.itemBean.getWordWrap());

    }

    /**
     * Sets the text horizontal alignment It is valid for NON_EDITABLE and
     * NON_EDITABLE_HTML item
     * 
     * @param align
     *            horizontal alignment
     */
    public void setTextAlign(
	    HasHorizontalAlignment.HorizontalAlignmentConstant align) {
	if (align == null) {
	    return;
	}
	if (this.getSpecifier() == CustGwtItem.NON_EDITABLE) {
	    Label label = (Label) vPanel.getWidget(0);
	    label.setHorizontalAlignment(align);
	} else if (this.getSpecifier() == CustGwtItem.NON_EDITABLE_HTML) {
	    HTML htmlWid = (HTML) vPanel.getWidget(0);
	    htmlWid.setHorizontalAlignment(align);
	}
    }

    /**
     * Sets the items text. For IMAGE it will be image Url.
     * 
     * @param text
     *            text of the item
     */
    public void setText(String text) {

	if (text == null) {
	    text = "";
	}
	if (this.getSpecifier() == CustGwtItem.EDITABLE) {
	    TextBox tBox = (TextBox) vPanel.getWidget(0);
	    tBox.setText(text);
	} else if (this.getSpecifier() == CustGwtItem.NON_EDITABLE) {
	    Label label = (Label) vPanel.getWidget(0);
	    label.setText(text);
	} else if (this.getSpecifier() == CustGwtItem.NON_EDITABLE_HTML) {
	    HTML htmlWid = (HTML) vPanel.getWidget(0);
	    htmlWid.setHTML(text);
	} else if (this.getSpecifier() == CustGwtItem.IMAGE) {
	    if (CustGwtUtil.isNull(text)) {
		if (image != null) {
		    imagePanel.remove(image);
		}
		imagePanel.setTitle("");
	    } else {
		if (image != null) {
		    image.setUrl(text);
		} else {
		    image = new Image(text);
		    imagePanel.add(image, 5, 5);
		}
	    }
	}
	if (this.itemBean.isTextAsTitle()) {
	    if (this.getSpecifier() == CustGwtItem.NON_EDITABLE
		    || this.getSpecifier() == CustGwtItem.EDITABLE) {
		if (!CustGwtUtil.isNull(text)) {
		    this.getInnerWidget().setTitle(text);
		}
	    }
	}
    }

    /**
     * Gets the content of the item For image it is image Url
     * 
     * @return item content
     */
    public String getText() {

	String returnText = "";
	if (this.getSpecifier() == CustGwtItem.EDITABLE) {
	    TextBox tBox = (TextBox) vPanel.getWidget(0);
	    returnText = tBox.getText();
	} else if (this.getSpecifier() == CustGwtItem.NON_EDITABLE) {
	    Label label = (Label) vPanel.getWidget(0);
	    returnText = label.getText();
	} else if (this.getSpecifier() == CustGwtItem.NON_EDITABLE_HTML) {
	    HTML htmlWid = (HTML) vPanel.getWidget(0);
	    returnText = htmlWid.getHTML();
	} else if (this.getSpecifier() == CustGwtItem.IMAGE) {
	    if (image != null) {
		returnText = CustGwtUtil.stringNoNull(image.getUrl());
	    }
	}
	return returnText;
    }

    /**
     * Gets the mapped DB value for this item. It calls
     * {@link custgwttbl.client.CustGwtItemBean#getDBValue(String displayValue)}
     * 
     * @return DB value of the item's content
     */
    public String getDBText() {

	return this.itemBean.getDBValue(this.getText());
    }

    /**
     * Sets focus in the EDITABLE component
     * 
     * @param focus
     *            true to set focus in the item
     */
    public void setFocus(boolean focus) {

	if (this.getSpecifier() == CustGwtItem.EDITABLE) {
	    TextBox tBox = (TextBox) vPanel.getWidget(0);
	    tBox.setFocus(focus);
	}
    }

    /**
     * Sets the word wrap for this cell
     * 
     * @param wrap
     *            true or false
     */
    public void setWordWrap(boolean wrap) {

	if (this.getSpecifier() == CustGwtItem.NON_EDITABLE) {
	    Label label = (Label) vPanel.getWidget(0);
	    label.setWordWrap(wrap);
	} else if (this.getSpecifier() == CustGwtItem.NON_EDITABLE_HTML) {
	    HTML htmlWid = (HTML) vPanel.getWidget(0);
	    htmlWid.setWordWrap(true);
	}
    }

    /**
     * Adds a keyboard listener to the item Valid for only EDITABLE item.
     * 
     * @param listener
     *            a keyboard listener
     */
    public void addKeyboardListener(KeyboardListener listener) {

	if (this.getSpecifier() == CustGwtItem.EDITABLE) {
	    TextBox tBox = (TextBox) vPanel.getWidget(0);
	    tBox.addKeyboardListener(listener);
	}
    }

    /**
     * Removes a keyboard listener from the item
     * 
     * @param listener
     *            a keyboard listener
     */
    public void removeKeyboardListener(KeyboardListener listener) {

	if (this.getSpecifier() == CustGwtItem.EDITABLE) {
	    TextBox tBox = (TextBox) vPanel.getWidget(0);
	    tBox.removeKeyboardListener(listener);
	}
    }

    /**
     * Adds a focus listener to the item Valid for only EDITABLE item.
     * 
     * @param listener
     *            a focus listener
     */
    public void addFocusListener(FocusListener listener) {

	if (this.getSpecifier() == CustGwtItem.EDITABLE) {
	    TextBox tBox = (TextBox) vPanel.getWidget(0);
	    tBox.addFocusListener(listener);
	}
    }

    /**
     * Removes a focus listener from the item
     * 
     * @param listener
     *            a focus listener
     */
    public void removeFocusListener(FocusListener listener) {

	if (this.getSpecifier() == CustGwtItem.EDITABLE) {
	    TextBox tBox = (TextBox) vPanel.getWidget(0);
	    tBox.removeFocusListener(listener);
	}
    }

    /**
     * Returns true if current item is editable item
     * 
     * @return true or false
     */
    public boolean isInEditingMode() {

	boolean bEdit = false;
	if (this.getSpecifier() == CustGwtItem.EDITABLE) {
	    bEdit = true;
	}
	return bEdit;
    }

    /**
     * Returns ItemConstant for the current item
     * 
     * @return specifier for this item
     * @see custgwttbl.client.CustGwtItem.ItemConstant
     */
    public ItemConstant getSpecifier() {

	return specifier;
    }

    /**
     * Returns the inner widget of the item It may be a label, html or textbox
     * etc
     * 
     * @return inner widget
     */
    public Widget getInnerWidget() {

	return innerWidget;
    }

    /**
     * Returns the item bean associated with this item
     * 
     * @return ItemBean CustGwtItemBean
     * @see custgwttbl.client.CustGwtItemBean
     */
    public CustGwtItemBean getItemBean() {

	return itemBean;
    }
}
