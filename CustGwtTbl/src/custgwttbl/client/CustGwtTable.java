/*
 * CustGwtTable.java        1.0.0
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

import java.util.ArrayList;
import java.util.Vector;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;

/**
 * This is the main table class
 */

public class CustGwtTable extends Composite {
    private DockPanel panelBase = new DockPanel();
    private FlexTable grid;
    private Vector vItemBeanList;
    private CustGwtDataSource dataSource;
    private boolean editable;
    private int itemsPerPage;
    private int selectedRow = -1;
    private boolean isUpdateable;
    private boolean wordWrap;
    private CustGwtNavPanel navPanel;
    private Vector dataChangeListenerCollection;
    private boolean insertMode;
    private CustGwtTableListener tableListener = new CustGwtTableListener();
    private String stage = "";

    /**
     * Creates a Table Widget
     * 
     * @param inDataSource
     *            CustGwtDataSource object
     * @param inItemBnList
     *            list of CustGwtItemBean objects
     * @param inItemsPerPage
     *            no of rows to be displayed per page
     * @param showLoadImageOnStartUp
     *            whether to show the load image in the start up
     */
    public CustGwtTable(CustGwtDataSource inDataSource, Vector inItemBnList,
	    int inItemsPerPage, boolean showLoadImageOnStartUp) {
	stage = "Initializing table";
	try {
	    this.itemsPerPage = inItemsPerPage;
	    this.dataSource = inDataSource;
	    this.dataSource.setTable(this);
	    this.vItemBeanList = inItemBnList;
	    this.grid = new FlexTable();
	    this.grid.addTableListener(tableListener);
	    this.prepareTable(inItemBnList, inItemsPerPage);
	    stage = "Initializing nav panel";
	    navPanel = new CustGwtNavPanel(inDataSource);
	    ScrollPanel scrollPanel = new ScrollPanel(grid);
	    scrollPanel.addStyleName(CustGwtUtil.CUST_GWT_TABLE_SCROLL_STYLE);
	    panelBase.add(scrollPanel, DockPanel.CENTER);
	    panelBase.add(navPanel, DockPanel.NORTH);
	    if (showLoadImageOnStartUp) {
		this.navPanel.showLoadImage(true);
	    }
	    this.grid.setWidth("100%");
	    this.grid.addStyleName(CustGwtUtil.CUST_GWT_TABLE_STYLE);
	    this.addStyleForRow(0, CustGwtUtil.CUST_GWT_TABLE_CELL_HEADER_STYLE);
	    this.addStyleForCells(CustGwtUtil.CUST_GWT_TABLE_CELL_STYLE);
	    initWidget(panelBase);
	    this.grid.setCellSpacing(0);
	    this.grid.setCellPadding(0);
	    this.setWidth("100%");
	} catch (Exception excep) {
	    CustGwtUtil.showAlertOnError(excep, stage);
	}
    }

    /**
     * Returns the inner table which is a FlexTable
     * 
     * @return FlexTable
     */
    public FlexTable getInnerTable() {
	return this.grid;
    }

    private void addStyleForCells(String styleName) {
	FlexTable.FlexCellFormatter formatter = this.grid
		.getFlexCellFormatter();
	for (int i = 1; i <= this.getRowCount(); i++) {
	    for (int j = 0, n = this.vItemBeanList.size(); j < n; j++) {
		formatter.addStyleName(i, j, styleName);
	    }
	}
    }

    private void prepareTable(Vector inItemBnList, int inItemsPerPage) {
	stage = "Preparing table";
	try {
	    inItemsPerPage++;
	    for (int i = 0; i < inItemsPerPage; i++) {
		for (int j = 0, n = inItemBnList.size(); j < n; j++) {
		    if (i == 0) {
			CustGwtItemBean itemBean = (CustGwtItemBean) inItemBnList
				.get(j);
			String colHeader = CustGwtUtil.stringNoNull(itemBean
				.getLabel());
			this.setText(i, j, colHeader);
			String colWidth = itemBean.getColumnWidth();
			if (!CustGwtUtil.isNull(colWidth)) {
			    this.setColumnWidth(j, colWidth);
			}
		    } else {
			this.setText(i, j, "");
		    }
		}
	    }
	} catch (Exception excep) {
	    CustGwtUtil.showAlertOnError(excep, stage);
	}
    }

    /**
     * Sets the column width
     * 
     * @param col
     *            column index
     * @param width
     *            width of the column
     * @see custgwttbl.client.CustGwtItemBean#setColumnWidth(String)
     */
    public void setColumnWidth(int col, String width) {
	this.grid.getColumnFormatter().setWidth(col, width);
    }

    /**
     * Returns No of columns
     * 
     * @return no of column
     */
    public int getColumnCount() {
	if (this.vItemBeanList != null) {
	    return this.vItemBeanList.size();
	}
	return 0;
    }

    /**
     * Returns no of rows
     * 
     * @return no of rows
     */
    public int getRowCount() {
	return this.itemsPerPage;
    }

    /**
     * Sets text to the table cell
     * 
     * @param row
     *            row index
     * @param col
     *            column index
     * @param text
     *            text to set
     */
    public void setText(int row, int col, String text) {
	try {
	    stage = "Setting text for Row:" + row + " Column:" + col;
	    CustGwtItemBean ib = (CustGwtItemBean) this.vItemBeanList.get(col);
	    CustGwtItem tableCell;
	    if (row == 0) {
		tableCell = ib.getNonEditableHeaderComponent();
	    } else {
		tableCell = ib.getNonEditableComponent();
	    }
	    this.grid.setWidget(row, col, tableCell);
	    tableCell.setText(text);
	} catch (Exception excep) {
	    CustGwtUtil.showAlertOnError(excep, stage);
	}
    }

    /**
     * Gets the text associated with the table cell
     * 
     * @param row
     *            rowindex
     * @param col
     *            column index
     * @return text
     */
    public String getText(int row, int col) {
	try {
	    stage = "Getting text for Row:" + row + " Column:" + col;
	    CustGwtItem currCell = (CustGwtItem) this.grid.getWidget(row, col);
	    return CustGwtUtil.stringNoNull(currCell.getDBText());
	} catch (Exception excep) {
	    CustGwtUtil.showAlertOnError(excep, stage);
	}
	return "";
    }

    /**
     * Set whether table will be editable or not
     * 
     * @param newEdit
     *            true if editable
     */
    public void setEditable(boolean newEdit) {
	editable = newEdit;
	for (int i = 0, n = vItemBeanList.size(); i < n; i++) {
	    CustGwtItemBean itemBean = (CustGwtItemBean) vItemBeanList.get(i);
	    itemBean.setEditable(newEdit);
	}
    }

    /**
     * Set to change the visibility of a row
     * 
     * @param row
     *            row index
     * @param visible
     *            true if row is visible
     */
    public void setRowVisible(int row, boolean visible) {
	RowFormatter formatter = this.grid.getRowFormatter();
	formatter.setVisible(row, visible);
    }

    /**
     * Set to change the visibility of a column
     * 
     * @param col
     *            column index
     * @param visible
     *            true if column is visible
     */
    public void setColumnVisible(int col, boolean visible) {
	for (int i = 0; i <= this.getRowCount(); i++) {
	    CellFormatter formatter = this.grid.getCellFormatter();
	    formatter.setVisible(i, col, visible);
	}
    }

    /**
     * Sets whether text in the table will be wrapped or not
     * 
     * @param newWrap
     *            true if text should be wrape
     * @see custgwttbl.client.CustGwtItemBean#setWordWrap(boolean)
     */
    public void setWordWrap(boolean newWrap) {
	wordWrap = newWrap;
	for (int i = 0, n = vItemBeanList.size(); i < n; i++) {
	    CustGwtItemBean itemBean = (CustGwtItemBean) vItemBeanList.get(i);
	    itemBean.setWordWrap(newWrap);
	}
    }

    /**
     * Returns true if text of the cells to be wraped
     * 
     * @return true or false
     */
    public boolean isWordWrap() {
	return wordWrap;
    }

    /**
     * Rturns true if table is editable
     * 
     * @return true or false
     */
    public boolean isEditable() {
	return editable;
    }

    private void editCell(final int row, final int col, Widget widget) {
	stage = "Editing cell for Row:" + row + " Column:" + col;
	try {
	    CustGwtItemBean ib = (CustGwtItemBean) this.vItemBeanList.get(col);
	    final CustGwtItem tableCell = ib.getEditableComponent();
	    if (tableCell == null) {
		return;
	    }
	    String currText = ((CustGwtItem) widget).getText().trim();
	    grid.setWidget(row, col, tableCell);
	    tableCell.setFocus(true);
	    tableCell.setText(currText);
	    tableCell.addKeyboardListener(new KeyboardListener() {
		public void onKeyDown(Widget sender, char keyCode, int modifiers) {
		}

		public void onKeyPress(Widget sender, char keyCode,
			int modifiers) {
		}

		public void onKeyUp(Widget sender, char keyCode, int modifiers) {
		    switch (keyCode) {
		    case KeyboardListener.KEY_ENTER: {
			updateRecord(row, col);
			break;
		    }
		    case KeyboardListener.KEY_TAB: {
			updateRecord(row, col);
			break;
		    }
		    }
		}
	    });
	    tableCell.addFocusListener(new FocusListener() {
		public void onFocus(Widget sender) {
		}

		public void onLostFocus(Widget sender) {
		    updateRecord(row, col);
		}
	    });
	} catch (Exception excep) {
	    CustGwtUtil.showAlertOnError(excep, stage);
	}
    }

    private void updateRecord(int row, int column) {
	stage = "Updating Row:" + row + " Column:" + column;
	try {
	    CustGwtItemBean ib = (CustGwtItemBean) this.vItemBeanList
		    .get(column);
	    CustGwtItem toBeCell = ib.getNonEditableComponent();
	    if (this.getSelectedRow() == row) {
		toBeCell.getInnerWidget().addStyleName(
			CustGwtUtil.CUST_GWT_TABLE_ROW_SELECTED_STYLE);
	    }
	    CustGwtItem currCell = (CustGwtItem) grid.getWidget(row, column);
	    String displayText = currCell.getText();
	    if (CustGwtUtil.isNull(displayText)) {
		if (ib.isRequired()) {
		    currCell.setFocus(true);
		    Window.alert("You must enter value for the field");
		    return;
		}
	    }
	    toBeCell.setText(displayText);
	    grid.setWidget(row, column, toBeCell);
	    if (this.isUpdateable()) {
		// insertMode will be true when some insert button clicked or
		// something else
		// insert mode will become false again if save buttonn is
		// clicked
		// or something else , will implement those later
		// delete will happen from some other buton or something
		// so need not to consider delete here
		// we will consider only update here
		if (this.insertMode) {
		    return;
		}
		fireDataUpdateEvent(row, column);
	    }
	} catch (Exception excep) {
	    CustGwtUtil.showAlertOnError(excep, stage);
	}
    }

    private void fireDataUpdateEvent(int row, int col) {
	if (this.dataChangeListenerCollection != null) {
	    for (int i = 0, n = this.dataChangeListenerCollection.size(); i < n; i++) {
		CustGwtTableDataChangeListener listener = (CustGwtTableDataChangeListener) dataChangeListenerCollection
			.get(i);
		if (listener != null) {
		    CustGwtTableDataChangeEvent event = new CustGwtTableDataChangeEvent(
			    this);
		    listener.dataUpdated(event, row, col);
		}
	    }
	}
    }

    private void fireDataInsertEvent(int row) {
	if (this.dataChangeListenerCollection != null) {
	    for (int i = 0, n = this.dataChangeListenerCollection.size(); i < n; i++) {
		CustGwtTableDataChangeListener listener = (CustGwtTableDataChangeListener) dataChangeListenerCollection
			.get(i);
		if (listener != null) {
		    CustGwtTableDataChangeEvent event = new CustGwtTableDataChangeEvent(
			    this);
		    listener.dataInserted(event, row);
		}
	    }
	}
    }

    private void fireDataDeleteEvent(int row) {
	if (this.dataChangeListenerCollection != null) {
	    for (int i = 0, n = this.dataChangeListenerCollection.size(); i < n; i++) {
		CustGwtTableDataChangeListener listener = (CustGwtTableDataChangeListener) dataChangeListenerCollection
			.get(i);
		if (listener != null) {
		    CustGwtTableDataChangeEvent event = new CustGwtTableDataChangeEvent(
			    this);
		    listener.dataDeleted(event, row);
		}
	    }
	}
    }

    /**
     * Clears all current data from the cells
     */
    public void clearData() {
	for (int i = 1; i <= this.getRowCount(); i++) {
	    for (int j = 0, n = vItemBeanList.size(); j < n; j++) {
		this.setText(i, j, "");
	    }
	}
    }

    /**
     * Modifiy the current data of the table
     * 
     * @param dataList
     *            new data set
     */
    public void changeData(ArrayList dataList) {
	if (dataList == null || dataList.size() == 0) {
	    return;
	}
	clearData();
	int totalRows = dataList.size();
	for (int i = 0; i < totalRows; i++) {
	    ArrayList data = (ArrayList) dataList.get(i);
	    for (int j = 0, n = data.size(); j < n; j++) {
		String val = (String) data.get(j);
		this.setText(i + 1, j, val);
	    }
	}
	if (totalRows > this.itemsPerPage) {
	    this.itemsPerPage = totalRows;
	}
    }

    /**
     * Adds a data change listener
     * 
     * @param listener
     *            new CustGwtTableDataChangeListener
     */
    public void addDataChangeListener(CustGwtTableDataChangeListener listener) {
	if (dataChangeListenerCollection == null) {
	    dataChangeListenerCollection = new Vector();
	}
	dataChangeListenerCollection.add(listener);
    }

    /**
     * Removes a data change listener
     * 
     * @param listener
     *            a CustGwtTableDataChangeListener
     */
    public void removeDataChangeListener(CustGwtTableDataChangeListener listener) {
	if (dataChangeListenerCollection != null) {
	    dataChangeListenerCollection.remove(listener);
	}
    }

    /**
     * Select the row of the table
     * 
     * @param row
     *            row index
     */
    private void selectRow(int row) {
	if (this.getSelectedRow() == row) {
	    return;
	}
	if (this.getSelectedRow() != -1) {
	    this.removeStyleFromItems(this.getSelectedRow(),
		    CustGwtUtil.CUST_GWT_TABLE_ROW_SELECTED_STYLE);
	}
	this.addStyleForItems(row,
		CustGwtUtil.CUST_GWT_TABLE_ROW_SELECTED_STYLE);
	this.setSelectedRow(row);
    }

    /**
     * Adds style for the inner widgets of the row
     * 
     * @param row
     *            row index
     * @param styleName
     *            style name
     */
    public void addStyleForItems(int row, String styleName) {
	for (int i = 0, n = vItemBeanList.size(); i < n; i++) {
	    this.getCustGwtItem(row, i).getInnerWidget()
		    .addStyleName(styleName);
	}
    }

    /**
     * Removes style from the inner widgets of the row
     * 
     * @param row
     *            row index
     * @param styleName
     *            style name
     */
    public void removeStyleFromItems(int row, String styleName) {
	for (int i = 0, n = vItemBeanList.size(); i < n; i++) {
	    this.getCustGwtItem(row, i).getInnerWidget()
		    .removeStyleName(styleName);
	}
    }

    /**
     * Adds style for the row
     * 
     * @param row
     *            row index
     * @param styleName
     *            style name
     */
    public void addStyleForRow(int row, String styleName) {
	FlexTable.FlexCellFormatter formatter = this.grid
		.getFlexCellFormatter();
	for (int i = 0, n = vItemBeanList.size(); i < n; i++) {
	    formatter.addStyleName(row, i, styleName);
	}
    }

    /**
     * Removes style from the row
     * 
     * @param row
     *            row index
     * @param styleName
     *            style name
     */
    public void removeStyleForRow(int row, String styleName) {
	FlexTable.FlexCellFormatter formatter = this.grid
		.getFlexCellFormatter();
	for (int i = 0, n = vItemBeanList.size(); i < n; i++) {
	    formatter.removeStyleName(row, i, styleName);
	}
    }

    /**
     * Adds style for the column
     * 
     * @param col
     *            column index
     * @param styleName
     *            style name
     */
    public void addStyleForColumn(int col, String styleName) {
	FlexTable.FlexCellFormatter formatter = this.grid
		.getFlexCellFormatter();
	for (int i = 1; i <= this.getRowCount(); i++) {
	    formatter.addStyleName(i, col, styleName);
	}
    }

    /**
     * Removes style from the column
     * 
     * @param col
     *            column index
     * @param styleName
     *            style name
     */
    public void removeStyleForColumn(int col, String styleName) {
	FlexTable.FlexCellFormatter formatter = this.grid
		.getFlexCellFormatter();
	for (int i = 1; i <= this.getRowCount(); i++) {
	    formatter.removeStyleName(i, col, styleName);
	}
    }

    /**
     * Adds style for the cell
     * 
     * @param row
     *            row index
     * @param col
     *            column index
     * @param styleName
     *            style name
     */
    public void addStyleForCell(int row, int col, String styleName) {
	FlexTable.FlexCellFormatter formatter = this.grid
		.getFlexCellFormatter();
	formatter.addStyleName(row, col, styleName);
    }

    /**
     * Removes style from the cell
     * 
     * @param row
     *            row index
     * @param col
     *            column index
     * @param styleName
     *            style name
     */
    public void removeStyleForCell(int row, int col, String styleName) {
	FlexTable.FlexCellFormatter formatter = this.grid
		.getFlexCellFormatter();
	formatter.removeStyleName(row, col, styleName);
    }

    /**
     * Gets the current cell widget
     * 
     * @param row
     *            row index
     * @param col
     *            column index
     * @return CustGwtItem
     */
    public CustGwtItem getCustGwtItem(int row, int col) {
	CustGwtItem tableCell = (CustGwtItem) grid.getWidget(row, col);
	return tableCell;
    }

    /**
     * Gets the CustGwtDataSource object associated with the table
     * 
     * @return CustGwtDataSource
     */
    public CustGwtDataSource getDataSource() {
	return dataSource;
    }

    /**
     * Returns the index of the currently selected row
     * 
     * @return row index
     */
    public int getSelectedRow() {
	return selectedRow;
    }

    private void setSelectedRow(int newSelectedRow) {
	this.selectedRow = newSelectedRow;
    }

    /**
     * Returns true if table is updateable
     * 
     * @return true or false
     */
    public boolean isUpdateable() {
	return isUpdateable;
    }

    /**
     * Sets table's updateable flag
     * 
     * @param newUpdateable
     *            true if table is updateable
     */
    public void setUpdateable(boolean newUpdateable) {
	this.isUpdateable = newUpdateable;
    }

    /**
     * Gets the list of CustGwtItemBean asscociated with the table
     * 
     * @return list of CustGwtItemBean
     */
    public Vector getVItemBeanList() {
	return vItemBeanList;
    }

    /**
     * Returns the current navigation panel of the table
     * 
     * @return CustGwtNavPanel
     */
    public CustGwtNavPanel getNavPanel() {
	return navPanel;
    }

    private class CustGwtTableListener implements TableListener {
	public void onCellClicked(SourcesTableEvents sender, int row, int col) {
	    if (row == 0) {
		return;
	    }
	    CustGwtItem tableCell = (CustGwtItem) grid.getWidget(row, col);
	    if (tableCell.isInEditingMode()) {
		return;
	    }
	    selectRow(row);
	    editCell(row, col, tableCell);
	}
    }
}
