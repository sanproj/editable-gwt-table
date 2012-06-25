/*
 * CustGwtServiceException.java        1.0.0
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

package custgwttbl.client.service;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * This is serializable custom exception class
 */

public class CustGwtServiceException extends Exception implements
	IsSerializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    private String message;
    private String detailMessage;

    /**
     * Creates an CustGwtServiceException object
     */
    public CustGwtServiceException() {

	super();
    }

    /**
     * Creates an CustGwtServiceException object
     * 
     * @param inMessage
     *            error message for this exception
     */
    public CustGwtServiceException(String inMessage) {

	super(inMessage);
	this.message = inMessage;
    }

    /**
     * Creates an CustGwtServiceException object
     * 
     * @param inMessage
     *            error message for this exception
     * @param inDetailMesage
     *            detail error message for this exception
     */
    public CustGwtServiceException(String inMessage, String inDetailMesage) {

	super(inMessage);
	this.message = inMessage;
	this.detailMessage = inDetailMesage;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#getMessage()
     */
    public String getMessage() {

	return this.message;
    }

    /**
     * @return detail error message
     */
    public String getDetailMessage() {

	return this.detailMessage;
    }
}
