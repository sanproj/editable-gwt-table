/*
 * CustGwtTableDataChangeListener.java        1.0.0
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

import java.util.EventListener;

/**
 * This is the data change listener which needs to be registered with the table
 */

public interface CustGwtTableDataChangeListener extends EventListener {

    /**
     * Fires when data gets updated
     * 
     * @param e
     *            CustGwtTableDataChangeEvent
     * @param row
     *            row index
     * @param col
     *            column index
     */
    public void dataUpdated(CustGwtTableDataChangeEvent e, int row, int col);

    /**
     * Fires when data gets inserted
     * 
     * @param e
     *            CustGwtTableDataChangeEvent
     * @param row
     *            row index
     */
    public void dataInserted(CustGwtTableDataChangeEvent e, int row);

    /**
     * Fires when data gets deleted
     * 
     * @param e
     *            CustGwtTableDataChangeEvent
     * @param row
     *            row index
     */
    public void dataDeleted(CustGwtTableDataChangeEvent e, int row);
}
