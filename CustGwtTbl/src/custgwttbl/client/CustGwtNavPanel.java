/*
 * CustGwtNavPanel.java        1.0.0
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

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is the navigation panel which comes on top of the table
 */

public class CustGwtNavPanel extends Composite {
    private HorizontalPanel horizontalPanel = new HorizontalPanel();
    private Button nextBtn = new Button("Next");
    private Button prevBtn = new Button("Previous");
    private VerticalPanel prevBtnPanel = new VerticalPanel();
    private VerticalPanel nextBtnPanel = new VerticalPanel();
    private HorizontalPanel extraPanel = new HorizontalPanel();
    private CustGwtDataSource dataSource;
    private Image loadImage;
    private CustGwtNavPanelListener panelClickListener = new CustGwtNavPanelListener();

    /**
     * Creates a navigation panel
     * 
     * @param inDataSource
     *            CustGwtDataSource object
     * @see custgwttbl.client.CustGwtDataSource
     */
    public CustGwtNavPanel(CustGwtDataSource inDataSource) {
	this.dataSource = inDataSource;
	horizontalPanel
		.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	horizontalPanel.setWidth("100%");
	horizontalPanel.setSpacing(2);
	if (!CustGwtUtil.isNull(inDataSource.getLoadImageUrl())) {
	    loadImage = new Image(inDataSource.getLoadImageUrl());
	    loadImage.addStyleName(CustGwtUtil.CUST_GWT_TABLE_NAV_IMG_STYLE);
	    horizontalPanel.add(loadImage);
	}
	horizontalPanel.add(extraPanel);
	horizontalPanel.setCellWidth(extraPanel, "100%");
	prevBtnPanel.setSpacing(2);
	nextBtnPanel.setSpacing(2);
	prevBtnPanel.add(prevBtn);
	nextBtnPanel.add(nextBtn);
	prevBtn.addClickListener(panelClickListener);
	nextBtn.addClickListener(panelClickListener);
	horizontalPanel.add(prevBtnPanel);
	horizontalPanel.add(nextBtnPanel);
	initWidget(horizontalPanel);
	prevBtn.addStyleName(CustGwtUtil.CUST_GWT_TABLE_NAV_PREVBTN_STYLE);
	nextBtn.addStyleName(CustGwtUtil.CUST_GWT_TABLE_NAV_NEXTBTN_STYLE);
	this.setNextButtonEnabled(false);
	this.setPrevButtonEnabled(false);
	this.addStyleName(CustGwtUtil.CUST_GWT_TABLE_NAVPANEL_STYLE);
    }

    /**
     * Shows load image if show is true
     * 
     * @param show
     *            true or false
     */
    public void showLoadImage(boolean show) {
	if (this.loadImage != null) {
	    this.loadImage.setVisible(show);
	}
    }

    protected void setNextButtonEnabled(boolean bEnable) {
	nextBtn.setEnabled(bEnable);
    }

    protected void setPrevButtonEnabled(boolean bEnable) {
	prevBtn.setEnabled(bEnable);
    }

    /**
     * Returns CustGwtDataSource object associated with this
     * 
     * @return CustGwtDataSource
     * @see custgwttbl.client.CustGwtDataSource
     */
    public CustGwtDataSource getDataSource() {
	return dataSource;
    }

    private class CustGwtNavPanelListener implements ClickListener {
	public void onClick(Widget sender) {
	    if (sender == nextBtn) {
		dataSource.showNextPage();
	    } else if (sender == prevBtn) {
		dataSource.showPreviousPage();
	    }
	}
    }
}
