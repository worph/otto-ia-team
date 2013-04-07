/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otto.process.engines;

import java.util.Random;
import otto.process.dto.EngineRequestDTO;
import otto.process.dto.EngineResultDTO;

/**
 *
 * @author PIERRE
 */
public class EngineShiFuMi implements Engine{

    @Override
    public EngineResultDTO process(EngineRequestDTO req) {
        Random rand = new Random();
        int value = rand.nextInt(3)+1;
        return new EngineResultDTO(String.valueOf(value));
    }
    
}
