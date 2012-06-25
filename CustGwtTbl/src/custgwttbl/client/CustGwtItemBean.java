/*
 * CustGwtItemBean.java        1.0.0
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

import java.util.HashMap;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

/**
 * This is the item bean which holds the information of item. If it used in a
 * grid then items for each cell of a particular column depends on the bean
 * assigned to it. When grid gets created it takes the information from the
 * bean.
 */

public class CustGwtItemBean {

    private String name = "";
    private String label;
    private int seq;
    private boolean editable;
    private String dataType;
    private int itemLength = -1;
    private boolean wordWrap;
    private int itemHeight = -1;
    private HashMap valuesTable = new HashMap();
    private boolean hasHTML;
    private boolean isRequired;
    private String columnWidth;// for grid
    private boolean hasImage;
    private HasHorizontalAlignment.HorizontalAlignmentConstant textAlign = null;
    private String titleText;
    private boolean isTextAsTitle;
    private int extraHight = -2;

    /**
     * Creates an empty bean object
     */
    public CustGwtItemBean() {

    }

    /**
     * Returns the extra height set for the editable component
     * 
     * @return extra height
     */
    public int getExtraHight() {

	return extraHight;
    }

    /**
     * This is the extra height which will be added or reduced while puting the
     * editing component. This is useful to make UI stable while setting the
     * editable component. Default is -2.If you are using CSS no need to set
     * this.
     * 
     * @param newExtraHight
     *            extra height
     */
    public void setExtraHight(int newExtraHight) {

	this.extraHight = newExtraHight;
    }

    /**
     * Retruns whether text will be set as title
     * 
     * @return true if text is set as title(tool tip text) too
     */
    public boolean isTextAsTitle() {

	return isTextAsTitle;
    }

    /**
     * Sets whether the text will be shown as title(tool tip text). It is valid
     * for only NON_EDITABLE and EDITABLE item. If your non editable component
     * text is large, then you can set word wrap true for that component. But
     * word wrapping may distort the grid UI. So either you can increase the
     * height of the row or you can keep wrod wrap false and set setTextAsTitle
     * as true, so in that case user can see the large inner text as tool tip
     * for that component.
     * 
     * 
     * @param newTextAsTitle
     *            true or false
     */
    public void setTextAsTitle(boolean newTextAsTitle) {

	this.isTextAsTitle = newTextAsTitle;
    }

    /**
     * Returns the tool tip text for the item
     * 
     * @return title text
     */
    public String getTitleText() {

	return titleText;
    }

    /**
     * Sets the tool tip text for the item.If you set it, this will be shown as
     * toll tip for all components of that row for a grid
     * 
     * @param newTitleText
     *            tool tip for item
     */
    public void setTitleText(String newTitleText) {

	this.titleText = newTitleText;
    }

    /**
     * Gets the horizontal alignment of the text
     * 
     * @return alignment of the inner text
     */
    public HasHorizontalAlignment.HorizontalAlignmentConstant getTextAlign() {

	return textAlign;
    }

    /**
     * Sets the horizontal alignment of the text of the item. This is valid for
     * NON_EDITABLE and NON_EDITABLE_HTML.If you set it in CSS , then setting it
     * will effect the whole column for a grid.
     * 
     * @param newTextAlign
     *            horizontal alignment of the item
     */
    public void setTextAlign(
	    HasHorizontalAlignment.HorizontalAlignmentConstant newTextAlign) {

	this.textAlign = newTextAlign;
    }

    /**
     * Returns whether item has a image in it
     * 
     * @return true if it has an image
     */
    public boolean hasImage() {

	return hasImage;
    }

    /**
     * Sets true if item has image. If it is true it will cosider the text as an
     * image url and try to fetch that image and show as an item content.
     * 
     * @param bImage
     *            true if item has image
     */
    public void setHasImage(boolean bImage) {

	if (bImage) {
	    this.setEditable(false);
	}
	this.hasImage = bImage;
    }

    /**
     * Gets the column width if it is a grid
     * 
     * @return column width
     */
    public String getColumnWidth() {

	return columnWidth;
    }

    /**
     * Sets the column width. You can set it is % or in pixel. e.g 12% or 20px
     * 
     * @param newColumnWidth
     *            coulm width
     */
    public void setColumnWidth(String newColumnWidth) {

	this.columnWidth = newColumnWidth;
    }

    /**
     * Returns true if content of the item is html
     * 
     * @return true if item content is html
     */
    public boolean hasHTML() {

	return hasHTML;
    }

    /**
     * Sets whether content of the item should be considered as html or not. If
     * it is true, item content will be put as html. It is useful for showing
     * hyperlink as item content.
     * 
     * @param bHtml
     *            true if content to be considered as html
     */
    public void setHTML(boolean bHtml) {

	if (bHtml) {
	    this.setEditable(false);
	}
	hasHTML = bHtml;
    }

    /**
     * Sets whether content of item should be wrapped or not. This is valid for
     * NON_EDITABLE and NON_EDITABLE_HTML item content.
     * 
     * @param newWrap
     *            true if item content to be wrapped
     */
    public void setWordWrap(boolean newWrap) {

	wordWrap = newWrap;
    }

    /**
     * Returns true if item content to be wrapped.
     * 
     * @return true if item content to be wrapped
     */
    public boolean getWordWrap() {

	return wordWrap;
    }

    /**
     * Sets the item height. If You set height in CSS already , then Setting
     * this will effect only for all the cells in the particular column for a
     * grid.
     * 
     * @param newHeight
     *            height of the item.
     */
    public void setItemHeight(int newHeight) {

	itemHeight = newHeight;
    }

    /**
     * Returns the item height.
     * 
     * @return item height
     */
    public int getItemHeight() {

	return itemHeight;
    }

    /**
     * Sets the item length. If You set length/width in CSS already, then
     * Setting this will effect only for all the cells in the particular column
     * for a grid.
     * 
     * @param newLength
     *            item length
     */
    public void setItemLength(int newLength) {

	itemLength = newLength;
    }

    /**
     * Gets the item length
     * 
     * @return item length
     */
    public int getItemLength() {

	return itemLength;
    }

    /**
     * Gets the data type of the item
     * 
     * @return data type
     */
    public String getDataType() {

	return dataType;
    }

    /**
     * Sets the data type of the item. It is not implemented yet. This will
     * derive the editable item. For now editable item is only text box.
     * 
     * @param newDataType
     *            data type of the item
     */
    public void setDataType(String newDataType) {

	dataType = newDataType;
    }

    /**
     * Sets whether the item is editable or not
     * 
     * @param newEdit
     *            true if item is editable
     */
    public void setEditable(boolean newEdit) {

	if (!this.hasHTML() && !this.hasImage()) {
	    editable = newEdit;
	}
    }

    /**
     * Returns true if item is editable.
     * 
     * @return true if item is editable
     */
    public boolean isEditable() {

	return editable;
    }

    /**
     * Gets the name of this item.
     * 
     * @return name of the item.
     */
    public String getName() {

	return name;
    }

    /**
     * Sets the name of the item. It can be a unique identifier.
     * 
     * @param newName
     *            name of the item
     */
    public void setName(String newName) {

	name = newName;
    }

    /**
     * Gets the item Label
     * 
     * @return label of the item
     */
    public String getLabel() {

	return label;
    }

    /**
     * Sets the label of the item. This will be displayed in the column header
     * for grid.
     * 
     * @param newLabel
     *            label of the item
     */
    public void setLabel(String newLabel) {

	label = newLabel;
    }

    /**
     * Gets the sequence of the item.
     * 
     * @return sequence of the item
     */
    public int getSeq() {

	return seq;
    }

    /**
     * Sets the sequence of the item.
     * 
     * @param newSeq
     *            the sequence of the item
     */
    public void setSeq(int newSeq) {

	seq = newSeq;
    }

    String getChangedHeightInPixel() {

	return (this.getItemHeight() + this.getExtraHight()) + "px";
    }

    /**
     * Returns the item length in pixel
     * 
     * @return item length
     */
    public String getLengthInPixel() {

	return this.getItemLength() + "px";
    }

    /**
     * Returns the item height in pixel.
     * 
     * @return item height
     */
    public String getHeightInPixel() {

	return this.getItemHeight() + "px";
    }

    /**
     * Retruns the editable item for this bean set up. If it is not editable,
     * returns null.
     * 
     * @return editable CustGwtItem
     */
    public CustGwtItem getEditableComponent() {

	if (this.isEditable()) {
	    // now it will be TextBox, we will define component based on data
	    // type
	    // later on,
	    CustGwtItem tableCell = new CustGwtItem(CustGwtItem.EDITABLE, this);
	    tableCell = getModifiedTableCell(tableCell, false);
	    return tableCell;
	}
	return null; // no editable component,cell is not editable
    }

    /**
     * Returns non editable component for column header of the grid
     * 
     * @return non editable CustGwtItem
     */
    public CustGwtItem getNonEditableHeaderComponent() {

	CustGwtItem tableCell;
	if (this.hasHTML()) {
	    tableCell = new CustGwtItem(CustGwtItem.NON_EDITABLE_HTML, this);
	    tableCell.getInnerWidget().removeStyleName(
		    CustGwtUtil.CUST_GWT_ITEM_NON_EDITABLE_HTML_STYLE);
	} else {
	    tableCell = new CustGwtItem(CustGwtItem.NON_EDITABLE, this);
	    tableCell.getInnerWidget().removeStyleName(
		    CustGwtUtil.CUST_GWT_ITEM_NON_EDITABLE_STYLE);
	}
	tableCell.getInnerWidget().addStyleName(
		CustGwtUtil.CUST_GWT_TABLE_ITEM_HEADER_STYLE);
	return tableCell;
    }

    /**
     * Returns non editable item
     * 
     * @return non editable CustGwtItem
     */
    public CustGwtItem getNonEditableComponent() {

	CustGwtItem tableCell;
	if (this.hasImage()) {
	    tableCell = new CustGwtItem(CustGwtItem.IMAGE, this);
	} else if (this.hasHTML()) {
	    tableCell = new CustGwtItem(CustGwtItem.NON_EDITABLE_HTML, this);
	} else {
	    tableCell = new CustGwtItem(CustGwtItem.NON_EDITABLE, this);
	}
	tableCell = getModifiedTableCell(tableCell, false);
	return tableCell;
    }

    private CustGwtItem getModifiedTableCell(CustGwtItem tableCell,
	    boolean isHeader) {

	if (!isHeader) {
	    if (!CustGwtUtil.isNull(this.getTitleText())) {
		if (tableCell.getInnerWidget() != null) {
		    tableCell.getInnerWidget().setTitle(this.getTitleText());
		}
	    }
	}
	return tableCell;
    }

    /**
     * This returns key,value cache for this bean.
     * 
     * @return kay value cache
     */
    public HashMap getValuesTable() {

	return valuesTable;
    }

    /**
     * Sets the key,value cache for this bean This stores the
     * value.(displayvalue, dbvalue)
     * 
     * @param newValuesTable
     *            key value cache
     */
    public void setValuesTable(HashMap newValuesTable) {

	this.valuesTable = newValuesTable;
    }

    /**
     * Return the DB value which was cached earlier by
     * {@link #setValuesTable(HashMap newValuesTable)}. If no db value found ,
     * it will return the display value
     * 
     * @param displayValue
     *            is the key to be searched in the cache
     * @return db value against that display value.
     */
    public String getDBValue(String displayValue) {

	String dbVal = (String) this.getValuesTable().get(displayValue);
	if (CustGwtUtil.isNull(dbVal)) {
	    return displayValue;
	}
	return dbVal;
    }

    /**
     * Returns true if it is a requred field , if item is editable
     * 
     * @return tru if required
     */
    public boolean isRequired() {

	return isRequired;
    }

    /**
     * Sets true if it is a required field while editing.
     * 
     * @param newRequired
     *            true if item is required
     */
    public void setRequired(boolean newRequired) {

	this.isRequired = newRequired;
    }
}
