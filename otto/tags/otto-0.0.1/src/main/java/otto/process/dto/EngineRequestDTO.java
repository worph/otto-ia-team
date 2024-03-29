/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package otto.process.dto;

/**
 *
 * @author PIERRE
 */
public class EngineRequestDTO {
    private Integer gain1;
    private Integer gain2;
    private String move1;
    private String move2;
    private String tray;
    private Integer timeout;
    private Integer turn;

    public EngineRequestDTO(Integer gain1, Integer gain2, String move1, String move2, String tray, Integer timeout, Integer turn) {
        this.gain1 = gain1;
        this.gain2 = gain2;
        this.move1 = move1;
        this.move2 = move2;
        this.tray = tray;
        this.timeout = timeout;
        this.turn = turn;
    }

    public Integer getTurn() {
        return turn;
    }
    
    public Integer getGain1() {
        return gain1;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public Integer getGain2() {
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
    
}
