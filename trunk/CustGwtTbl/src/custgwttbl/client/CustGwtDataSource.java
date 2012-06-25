/*
 * CustGwtDataSource.java        1.0.0
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import custgwttbl.client.service.CustGwtDataServiceAsync;
import custgwttbl.client.service.CustGwtDataServiceUtil;

/**
 * This is the datasource which executes the rpc call.
 */

public class CustGwtDataSource {

    private String servletPath;
    private CustGwtTable table;
    private CustGwtDataHolder dataHolder;
    private String loadImageUrl;

    /**
     * Creates an CustGwtDataSource with the specified servlet path.
     * 
     * @param newServletPath
     *            servlet path for the rpc call
     */
    public CustGwtDataSource(String newServletPath) {

	servletPath = newServletPath;
    }

    /**
     * Returns the load image Url.
     * 
     * @return Load image Url
     */
    public String getLoadImageUrl() {

	return loadImageUrl;
    }

    /**
     * Sets load image Url.This image is displayed while rpc call is in process
     * 
     * @param newUrl
     *            , the image Url
     */
    public void setLoadImageUrl(String newUrl) {

	this.loadImageUrl = newUrl;
    }

    /**
     * Sets the table associated with this data source.
     * 
     * @param newTable
     *            , the table
     */
    public void setTable(CustGwtTable newTable) {

	this.table = newTable;
    }

    /**
     * Returns the servlet path.
     * 
     * @return servlet path
     */
    public String getServletPath() {

	return servletPath;
    }

    /**
     * Sets the servlet path for the rpc call.
     * 
     * @param newServletPath
     *            , servlet path
     */
    public void setServletPath(String newServletPath) {

	servletPath = newServletPath;
    }

    /**
     * Refresh the current page
     */
    public void refreshPage() {

	this.dataHolder.setPageCode(CustGwtDataHolder.PAGE_CURR);
	this.executeCommand(this.dataHolder);
    }

    /**
     * This will bring the next set of data
     */
    public void showNextPage() {

	int nextPage = this.dataHolder.getPageNo() + 1;
	this.dataHolder.setPageNo(nextPage);
	this.dataHolder.setPageCode(CustGwtDataHolder.PAGE_NEXT);
	this.executeCommand(this.dataHolder);
    }

    /**
     * This will bring the previous set of data
     */
    public void showPreviousPage() {

	int prevPage = this.dataHolder.getPageNo() - 1;
	if (prevPage == 0) {
	    Window.alert("No previous page available.");
	    return;
	}
	if (prevPage == 1) {
	    this.table.getNavPanel().setPrevButtonEnabled(false);
	}
	this.dataHolder.setPageNo(prevPage);
	this.dataHolder.setPageCode(CustGwtDataHolder.PAGE_PREV);
	this.executeCommand(this.dataHolder);
    }

    /**
     * This method executes the rpc call. It takes all the parameters from the
     * dataholder object.
     * 
     * @param dataHolder
     */
    public void executeCommand(CustGwtDataHolder dataHolder) {

	executeCommand(dataHolder, this.getServletPath());
    }

    // this servlet path and data holder object
    // will be stored only for select command
    // this is best for updation, deletion and insertion,
    // if the sevlet is different than the one used in select
    /**
     * This method executes the rpc call with the new servlet path. If the
     * command is 'select', It stores the dataholder object, and newServletPath.
     * 
     * @param dataHolder
     *            the data holder object
     * @param newServletPath
     *            new servlet path
     */
    public void executeCommand(CustGwtDataHolder dataHolder,
	    String newServletPath) {

	String command = CustGwtUtil.stringNoNull(dataHolder.getCommand());
	if (command.equalsIgnoreCase("select")) {
	    this.dataHolder = dataHolder;
	    this.setServletPath(newServletPath);
	    if (table != null) {
		table.getNavPanel().showLoadImage(true);
	    }
	}
	CustGwtDataServiceAsync service = CustGwtDataServiceUtil
		.getServiceInstance();
	ServiceDefTarget endpoint = (ServiceDefTarget) service;
	endpoint.setServiceEntryPoint(newServletPath);
	service.executeCommand(dataHolder, new CustGwtDataServiceCallBack());
    }

    private class CustGwtDataServiceCallBack implements AsyncCallback {

	public CustGwtDataServiceCallBack() {

	}

	public void onFailure(Throwable caught) {

	    int pageNo = dataHolder.getPageNo();
	    String command = CustGwtUtil.stringNoNull(dataHolder.getCommand());
	    if (command.equalsIgnoreCase("select")) {
		if (dataHolder.getPageCode() == CustGwtDataHolder.PAGE_NEXT) {
		    dataHolder.setPageNo(pageNo - 1);
		} else if (dataHolder.getPageCode() == CustGwtDataHolder.PAGE_PREV) {
		    dataHolder.setPageNo(pageNo + 1);
		}
		table.getNavPanel().showLoadImage(false);
	    }
	    try {
		throw caught;
	    } catch (InvocationException e) {
		Window.alert("Error invoking service : " + e.getMessage());
	    } catch (Throwable e) {
		Window.alert(e.getMessage());
	    }
	}

	public void onSuccess(Object result) {

	    table.getNavPanel().showLoadImage(false);
	    CustGwtDataHolder dataHolder = (CustGwtDataHolder) result;
	    String command = CustGwtUtil.stringNoNull(dataHolder.getCommand());
	    if (!command.equalsIgnoreCase("select")) {
		return;
	    }
	    if (dataHolder.getPageNo() == 1) {
		table.getNavPanel().setPrevButtonEnabled(false);
	    } else {
		table.getNavPanel().setPrevButtonEnabled(true);
	    }
	    table.getNavPanel().setNextButtonEnabled(dataHolder.hasNext());
	    dataHolder.setHasNext(false);
	    ArrayList dataList = dataHolder.getReturnValues();
	    table.changeData(dataList);
	}
    }
}
