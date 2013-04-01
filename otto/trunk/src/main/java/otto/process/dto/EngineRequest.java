/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otto.process.dto;

/**
 *
 * @author PIERRE
 */
public class EngineRequest {
    private String gain1;
    private String gain2;
    private String move1;
    private String move2;
    private String tray;
    private String value;

    public EngineRequest(String gain1, String gain2, String move1, String move2, String tray, String value) {
        this.gain1 = gain1;
        this.gain2 = gain2;
        this.move1 = move1;
        this.move2 = move2;
        this.tray = tray;
        this.value = value;
    }

    public String getGain1() {
        return gain1;
    }

    public String getGain2() {
        return gain2;
    }

    public String getMove1() {
        return move1;
    }

    public String getMove2() {
        return move2;
    }

    public String getTray() {
        return tray;
    }

    public String getValue() {
        return value;
    }

    
}
