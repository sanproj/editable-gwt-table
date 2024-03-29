/*
 * CustGwtDataService.java        1.0.0
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

import com.google.gwt.user.client.rpc.RemoteService;

import custgwttbl.client.CustGwtDataHolder;

/**
 * This is the remote service interface
 */

public interface CustGwtDataService extends RemoteService {

    /**
     * This method gets executed in the servlet. Excecutes the sql command for
     * that dataHolder object.
     * 
     * 
     * @param dataHolder
     *            the serialized object which carries data
     * @return CustGwtDataHolder object
     * @throws CustGwtServiceException
     */
    public CustGwtDataHolder executeCommand(CustGwtDataHolder dataHolder)
	    throws CustGwtServiceException;
}
