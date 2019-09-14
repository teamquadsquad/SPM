package model;

public class code_model {

    private int lineNo;
    private String statement;
    private String sizeTokens;
    private int cs;
    private int ctc;
    private int cnc;
    private int ci;
    private int tw;
    private int cps;
    private int cr;

    public code_model(int lineNo, String statement, String sizeTokens, int cs, int ctc, int cnc, int ci, int tw, int cps, int cr) {
        this.lineNo = lineNo;
        this.statement = statement;
        this.sizeTokens = sizeTokens;
        this.cs = cs;
        this.ctc = ctc;
        this.cnc = cnc;
        this.ci = ci;
        this.tw = tw;
        this.cps = cps;
        this.cr = cr;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getSizeTokens() {
        return sizeTokens;
    }

    public void setSizeTokens(String sizeTokens) {
        this.sizeTokens = sizeTokens;
    }

    public int getCs() {
        return cs;
    }

    public void setCs(int cs) {
        this.cs = cs;
    }

    public int getCtc() {
        return ctc;
    }

    public void setCtc(int ctc) {
        this.ctc = ctc;
    }

    public int getCnc() {
        return cnc;
    }

    public void setCnc(int cnc) {
        this.cnc = cnc;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public int getTw() {
        return tw;
    }

    public void setTw(int tw) {
        this.tw = tw;
    }

    public int getCps() {
        return cps;
    }

    public void setCps(int cps) {
        this.cps = cps;
    }

    public int getCr() {
        return cr;
    }

    public void setCr(int cr) {
        this.cr = cr;
    }
}
