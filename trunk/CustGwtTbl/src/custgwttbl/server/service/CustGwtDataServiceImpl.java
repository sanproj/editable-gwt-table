/*
 * CustGwtServiceImpl.java        1.0.0
 *
 *
 */

package custgwttbl.server.service;

import java.util.ArrayList;
import custgwttbl.client.CustGwtDataHolder;
import custgwttbl.client.service.CustGwtDataService;
import custgwttbl.client.service.CustGwtServiceException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * This is an example of remote rpc servlet. Need to add logic as per
 * requirements
 * 
 */

public class CustGwtDataServiceImpl extends RemoteServiceServlet implements
	CustGwtDataService {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private final String dataSource = "java:/CustGwtTableDS";

    /*
     * (non-Javadoc)
     * 
     * @see
     * custgwttbl.client.service.CustGwtDataService#executeCommand(custgwttbl
     * .client.CustGwtDataHolder)
     */
    public CustGwtDataHolder executeCommand(CustGwtDataHolder dataHolder)
	    throws CustGwtServiceException {
	String command = dataHolder.getCommand();
	String paramAndValues = dataHolder.getParamAndValues();
	// Need to process parameters and values as per implementation
	int pageNo = dataHolder.getPageNo();
	int recordsPerPage = dataHolder.getRecordsPerPage();
	ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
	ArrayList<String> dataRow = new ArrayList<String>();
	dataRow.add("Value");
	dataList.add(dataRow);
	dataHolder.setReturnValues(dataList);
	return dataHolder;
    }
}
