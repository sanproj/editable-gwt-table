/*
 * CustGwtUtil.java        1.0.0
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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.user.client.Window;

/**
 * This is the utility class
 */

public class CustGwtUtil {
    /**
     * Table style name
     */
    public static final String CUST_GWT_TABLE_STYLE = "custgwttable";
    /**
     * CustGwtItem style
     */
    public static final String CUST_GWT_ITEM_STYLE = "custgwtitem";
    /**
     * CustGwtItem when as table header
     */
    public static final String CUST_GWT_TABLE_ITEM_HEADER_STYLE = "custgwttableitemheader";
    /**
     * CustGwtTable cell style (used as cell formatter style)
     */
    public static final String CUST_GWT_TABLE_CELL_STYLE = "custgwttablecell";
    /**
     * CustGwtTable cell when as header(used as cell formatter style)
     */
    public static final String CUST_GWT_TABLE_CELL_HEADER_STYLE = "custgwttablecellheader";
    /**
     * Style name when row is in selected state
     */
    public static final String CUST_GWT_TABLE_ROW_SELECTED_STYLE = "custgwttablerowselected";
    /**
     * Table scroll pane style
     */
    public static final String CUST_GWT_TABLE_SCROLL_STYLE = "custgwttablescroll";
    /**
     * Editable item style
     */
    public static final String CUST_GWT_ITEM_EDITABLE_STYLE = "custgwtitemeditable";
    /**
     * Non editable html item style
     */
    public static final String CUST_GWT_ITEM_NON_EDITABLE_HTML_STYLE = "custgwtitemnoneditablehtml";
    /**
     * Non editable image item style
     */
    public static final String CUST_GWT_ITEM_IMAGE_STYLE = "custgwtitemimage";
    /**
     * Normal non editable style
     */
    public static final String CUST_GWT_ITEM_NON_EDITABLE_STYLE = "custgwtitemnoneditable";
    /**
     * Navigation panel previous button style
     */
    public static final String CUST_GWT_TABLE_NAV_PREVBTN_STYLE = "custgwttablenavprevbtn";
    /**
     * Navigation panel next button style
     */
    public static final String CUST_GWT_TABLE_NAV_NEXTBTN_STYLE = "custgwttablenavnextbtn";
    /**
     * Navigation panel style
     */
    public static final String CUST_GWT_TABLE_NAVPANEL_STYLE = "custgwttablenavpanel";
    /**
     * Navigation panel style
     */
    public static final String CUST_GWT_TABLE_NAV_IMG_STYLE = "custgwttablenavimg";

    /**
     * Returns true if inVal is null or inVal length is 0.
     * 
     * @param inVal
     *            string
     * @return true or false
     */
    public static boolean isNull(String inVal) {
	if ((inVal == null) || (inVal.trim().length() == 0)) {
	    return true;
	}
	return false;
    }

    /**
     * Returns a blank string if {@link #isNull(String inVal)} returns true,
     * Otherwise returns inString
     * 
     * @param inString
     *            String
     * @return blank string or inString
     */
    public static String stringNoNull(String inString) {
	if (CustGwtUtil.isNull(inString)) {
	    return "";
	} else {
	    return inString;
	}
    }

    /**
     * Shows alert message
     * 
     * @param excep
     *            Exception
     * @param stage
     *            where it happened , user defined
     */
    public static void showAlertOnError(Throwable excep, String stage) {
	if (excep != null) {
	    stage = CustGwtUtil.stringNoNull(stage);
	    if (!CustGwtUtil.isNull(stage)) {
		stage = "STAGE : " + stage + " ERROR : ";
	    }
	    String stacktrace = stage + CustGwtUtil.getStacktraceString(excep);
	    Window.alert(stacktrace);
	}
    }

    /**
     * Sets the default error handler for GWT application. Set it for any GWT
     * application when <code>onModuleLoad()</code> starts
     */
    public static void initializeErrorHandling() {
	GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
	    public void onUncaughtException(Throwable error) {
		CustGwtUtil.showAlertOnError(error, "");
	    }
	});
    }

    /**
     * Gets the error trace as string upto level 5
     * 
     * @param error
     *            exception
     * @return stacktrace string
     */
    public static String getStacktraceString(Throwable error) {
	StackTraceElement[] traceElem = error.getStackTrace();
	StringBuffer sb = new StringBuffer(1024);
	sb.append(error.toString());
	int length = traceElem.length;
	for (int i = 0; i < length; i++) {
	    if (i == 0) {
		sb.append(" : at \n");
	    }
	    if (i > 4) {
		sb.append("......");
		break;
	    }
	    sb.append(traceElem[i].toString());
	    sb.append("\n");
	}
	return sb.toString();
    }
}
