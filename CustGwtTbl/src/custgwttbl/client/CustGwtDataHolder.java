/*
 * CustGwtDataHolder.java        1.0.0
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
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * This holds the data to transfer . This is a serialized object. All
 * explanations are based on the generic rpc servlet
 * {@link custgwttbl.server.service.CustGwtDataServiceImpl}. If you use
 * your own rpc servlet then you can set parameters as your requirement, but the
 * return values should be always ArrayList of ArrayList, if it is fetching data
 * set for displaying in the grid
 */

public class CustGwtDataHolder implements IsSerializable {

    private static final long serialVersionUID = 1L;
    private String command;// select,update etc, may be custom for procedure
    // calling
    // paramname|value~paramname|value...while update and insert it may be
    // column|value
    private String paramAndValues;
    private ArrayList returnValues;
    private int pageNo = 1;
    private int recordsPerPage;
    private String status;
    public static final int PAGE_PREV = 0;
    public static final int PAGE_NEXT = 1;
    public static final int PAGE_CURR = 2;
    private int pageCode = PAGE_CURR;
    private boolean hasNext;
    private String preparedStatementSQL;

    /**
     * Creates an empty CustGwtDataHolder with page no is 1.
     */
    public CustGwtDataHolder() {

    }

    /**
     * Determines whether result set has more value.
     * 
     * @return true if result set has more value, otherwise false
     */
    public boolean hasNext() {

	return hasNext;
    }

    /**
     * Set value to indicate the presence of more values in the resultset.
     * 
     * @param newHasNext
     *            , true if resultset has more values otherwise false
     */
    public void setHasNext(boolean newHasNext) {

	this.hasNext = newHasNext;
    }

    /**
     * Returns the page code This determines whether the current data set is the
     * result of clicking on previous button or next button or current execution
     * 
     * @return PAGE_PREV, PAGE_NEXT or PAGE_CURR
     */
    public int getPageCode() {

	return pageCode;
    }

    /**
     * Sets the page code. This is done internally while rpc call from data
     * source.This indicates for which page the call is executed
     * 
     * @param newPageCode
     *            , value is PAGE_PREV, PAGE_NEXT or PAGE_CURR
     */
    final void setPageCode(int newPageCode) {

	if (newPageCode != CustGwtDataHolder.PAGE_CURR
		&& newPageCode != CustGwtDataHolder.PAGE_NEXT
		&& newPageCode != CustGwtDataHolder.PAGE_PREV) {
	    newPageCode = CustGwtDataHolder.PAGE_CURR;
	}
	this.pageCode = newPageCode;
    }

    /**
     * Returns the status of rpc execution. This is not used by the generic rpc
     * servlet <code> CustGwtServiceImpl </code>.
     * 
     * @return current command
     */
    public String getStatus() {

	return status;
    }

    /**
     * Sets the status of rpc execution. This status can be customized message.
     * This is not used by the generic rpc servlet
     * <code> CustGwtServiceImpl </code>. It can be used in custom rpc servlets
     * 
     * @param newStatus
     *            , the status of rpc execution
     */
    public void setStatus(String newStatus) {

	status = newStatus;
    }

    /**
     * Sets the command for rpc call
     * 
     * @param newCommand
     *            , value is select, update, delete, insert for generic rpc
     *            servlet <code> CustGwtServiceImpl </code> call or user defined
     *            for custom rpc servlet
     */
    public void setCommand(String newCommand) {

	command = newCommand;
    }

    /**
     * Returns the command for the rpc call
     * 
     * @return current command
     */
    public String getCommand() {

	return command;
    }

    /**
     * Returns the current page no.
     * 
     * @return current page no
     */
    public int getPageNo() {

	return pageNo;
    }

    /**
     * Sets the current page no which before rpc call. This will automaticaly set
     * page code as current
     * 
     * @param newPageNo
     *            is the page no which will be brought during immediate rpc call
     */
    public void setPageNo(int newPageNo) {

	if (newPageNo <= 0) {
	    newPageNo = 1;
	}
	pageNo = newPageNo;
	this.setPageCode(CustGwtDataHolder.PAGE_CURR);
    }

    /**
     * Returns no of records which will be displayed in the grid at a time.
     * 
     * @return no of records per page
     */
    public int getRecordsPerPage() {

	return recordsPerPage;
    }

    /**
     * Sets no of records which will be displayed in the grid at a time.
     * 
     * @param newRecordsPerPage
     *            no of records per page
     */
    public void setRecordsPerPage(int newRecordsPerPage) {

	recordsPerPage = newRecordsPerPage;
    }

    /**
     * Returns the param name value list.
     * 
     * @return param name value list
     */
    public String getParamAndValues() {

	return paramAndValues;
    }

    /**
     * Sets param and value list for the rpc call. If the generic rpc servlet is
     * called it expects the following format
     * paramName|paramValue|paramDataType~paramName|paramValue|paramDataType...
     * paramName can be anything, but the value should be in exact sequence as
     * your prepared statement which is set by calling method
     * {@link #setPreparedStatementSQL(String newPreparedStatementSQL)}. View
     * the generic rpc
     * {@link custgwttbl.server.service.CustGwtDataServiceImpl}.
     * 
     * @param newParamAndValues
     *            name and value list
     */
    public void setParamAndValues(String newParamAndValues) {

	paramAndValues = newParamAndValues;
    }

    /**
     * Returns the return values queried from rpc servlet. It is the arryalist
     * of arraylist which is having values for a single row
     * 
     * @return arryalist of arraylist
     */
    public ArrayList getReturnValues() {

	return returnValues;
    }

    /**
     * Sets the values queried from rpc servlet. This values will be displayed
     * in the grid after a select query execution It should be arryalist of
     * arraylist which is having values for a single row
     * 
     * @param newReturnValues
     *            arryalist of arraylist
     */
    public void setReturnValues(ArrayList newReturnValues) {

	returnValues = newReturnValues;
    }

    /**
     * Returns prepared statement sql set for execution in database.
     * 
     * @return prepared statement sql
     */
    public String getPreparedStatementSQL() {

	return preparedStatementSQL;
    }

    /**
     * Sets prepared statement sql for execution. e.g select a, b, c from xyz
     * where z=? and s=? and userid=?. Then your argument for
     * {@link #setParamAndValues(String newParamAndValues)} will be like below,
     * z|5|number~s|hello|varchar~userid|23|number
     * 
     * @param newPreparedStatementSQL
     *            prepared statement sql
     */
    public void setPreparedStatementSQL(String newPreparedStatementSQL) {

	this.preparedStatementSQL = newPreparedStatementSQL;
    }
}
