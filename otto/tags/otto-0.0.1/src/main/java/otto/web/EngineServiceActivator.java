package otto.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.util.LinkedMultiValueMap;

import otto.process.dto.EngineRequestDTO;
import otto.process.dto.EngineResultDTO;
import otto.process.engines.Engine;

/**
 * @author Thomas
 * 
 */
public class EngineServiceActivator {

    private Map<String, Engine> engines;

    /**
     * fait appel au moteur de jeu
     * 
     * @param httpParam
     *            les paramètres d'appel au moteur
     * @return le résulat calculé par le moteur
     */
    public Message<Map<String, String>> call(Message<LinkedMultiValueMap<String, String>> httpParam) {

        Map<String, String> httpParamMap = httpParam.getPayload().toSingleValueMap();
        Map<String, String> httpParamReturnMap = new HashMap<String, String>();

        Engine engine = this.engines.get(httpParamMap.get(ParamConstantes.SET));

        EngineRequestDTO req = new EngineRequestDTO(Integer.valueOf(httpParamMap.get(ParamConstantes.GAIN1)),
                Integer.valueOf(httpParamMap.get(ParamConstantes.GAIN2)), httpParamMap.get(ParamConstantes.MOVE1),
                httpParamMap.get(ParamConstantes.MOVE2), httpParamMap.get(ParamConstantes.TRAY),
                Integer.valueOf(httpParamMap.get(ParamConstantes.TIMEOUT)), Integer.valueOf(httpParamMap
                        .get(ParamConstantes.TURN)));
        EngineResultDTO result = engine.process(req);

        httpParamReturnMap.put(ParamConstantes.GAME, httpParamMap.get(ParamConstantes.GAME));
        httpParamReturnMap.put(ParamConstantes.MOVE_ID, httpParamMap.get(ParamConstantes.MOVE_ID));
        httpParamReturnMap.put(ParamConstantes.VALUE, result.getValue());

        return MessageBuilder.withPayload(httpParamReturnMap)
                .setHeader(ParamConstantes.REFEREE, httpParamMap.get(ParamConstantes.REFEREE)).build();
    }

    /**
     * @return the engines
     */
    public Map<String, Engine> getEngines() {
        return this.engines;
    }

    /**
     * @param engines
     *            the engines to set
     */
    public void setEngines(Map<String, Engine> engines) {
        this.engines = engines;
    }
}
