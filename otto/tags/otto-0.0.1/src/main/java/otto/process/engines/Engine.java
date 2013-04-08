/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otto.process.engines;

import otto.process.dto.EngineRequestDTO;
import otto.process.dto.EngineResultDTO;

/**
 *
 * @author PIERRE
 */
public interface Engine {
    public EngineResultDTO process(EngineRequestDTO req);
}
