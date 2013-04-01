/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otto.process.engines;

import otto.process.dto.EngineRequest;
import otto.process.dto.EngineResult;

/**
 *
 * @author PIERRE
 */
public interface Engine {
    public EngineResult process(EngineRequest req);
}
