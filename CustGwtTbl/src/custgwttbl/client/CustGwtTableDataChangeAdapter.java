/*
 * CustGwtTableDataChangeAdapter.java        1.0.0
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

/**
 * This is the adapter class of CustGwtTableDataChangeListener
 */

public class CustGwtTableDataChangeAdapter implements
	CustGwtTableDataChangeListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * custgwttbl.client.CustGwtTableDataChangeListener#dataDeleted(custgwttbl
     * .client.CustGwtTableDataChangeEvent, int)
     */
    public void dataDeleted(CustGwtTableDataChangeEvent e, int row) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see custgwttbl.client.CustGwtTableDataChangeListener#dataInserted(
     * custgwttbl.client.CustGwtTableDataChangeEvent, int)
     */
    public void dataInserted(CustGwtTableDataChangeEvent e, int row) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * custgwttbl.client.CustGwtTableDataChangeListener#dataUpdated(custgwttbl
     * .client.CustGwtTableDataChangeEvent, int, int)
     */
    public void dataUpdated(CustGwtTableDataChangeEvent e, int row, int col) {

    }
}
