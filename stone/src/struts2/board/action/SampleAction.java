package struts2.board.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 *  Sample 액션
 * @since 2013.08.30
 * @author stoneis.pe.kr
 */
public class SampleAction extends ActionSupport {

	private static final long serialVersionUID = -7984186696609132970L;
	private String str = "";
	
    public String execute() throws Exception {
    	str = "blog.naver.com/musasin84";
        return SUCCESS;
    }

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}



}
