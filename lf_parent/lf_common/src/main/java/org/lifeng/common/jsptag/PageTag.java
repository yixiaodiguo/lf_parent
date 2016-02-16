package org.lifeng.common.jsptag;


import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.lifeng.common.entity.Pagination;

public class PageTag extends TagSupport {

	private static final long serialVersionUID = 3800432936996690839L;

	private String name;
	private String formID;
	private Pagination pagination;
	
	
	@Override
	public int doStartTag() throws JspException {
		try{
			setPagination((Pagination)pageContext.getRequest().getAttribute("page"));
			int p =  pagination.getCurrentPage(); 
			int pn = pagination.getTotalPages();
			
			StringBuilder sb = new StringBuilder();	
			sb.append("<link href='").append(pageContext.getServletContext().getContextPath()).append("/css/page.css' rel='stylesheet' type='text/css' />");
			sb.append("<script type='text/javascript'>")
			.append("function goPage(formID, p){document.getElementById('currentPage').value=p;document.getElementById(formID).submit();}")
			.append("function gotoPage(formID, pn){var gotop = document.getElementById('gotoPageNum').value;if(gotop > pn){document.getElementById('currentPage').value=pn;}else if(gotop < 1){document.getElementById('currentPage').value=1;}else{document.getElementById('currentPage').value=gotop;}document.getElementById(formID).submit();}")
			.append("</script>");
			if(pn == 0){
			  sb.append("<div class='page'>");
		      sb.append("<input type=\"hidden\" name=\"page.currentPage\" id=\"currentPage\" value=\"" + p+"\"/> ");
		      sb.append("<input type=\"hidden\" name=\"page.totalPages\" id=\"totalPages\" value=\"" + pn+"\"/> ");
		      sb.append("<input type=\"hidden\" name=\"page.totalCount\" id=\"totalCount\" value=\"" + pagination.getTotalCount()+"\"/> ");
		      sb.append(allHtml(pn));
			  sb.append("</div>");
			  pageContext.getOut().write(sb.toString());
		      return EVAL_PAGE;
		    }
		    
			if(pn < p){
		        p = pn;
		    };
		    sb.append("<div class='page'>");
		    if(p <= 1){
		        p = 1;
		        sb.append(pHtml(-10));
		    }else{
		        sb.append(aHtml(p - 1, -10));
		        sb.append(aHtml(1));
		    }
		    int start = 2;
		    int end = (pn < 9) ? pn: 9;
		    if (p >= 7) {
		        sb.append(" <span class='dotNum'>...</span> ");
		        start = p - 4;
		        int e = p + 4;
		        end = (pn < e) ? pn: e;
		    }
		    for (int i = start; i < p; i++) {
		        sb.append(aHtml(i));
		    };
		    sb.append(pHtml(p));
		    for (int i = p + 1; i <= end; i++) {
		        sb.append(aHtml(i));
		    };
		    if (end < pn) {
		        sb.append(" <span class='dotNum'>...</span> ");
		        sb.append(aHtml(pn));
		    };
		    if (p < pn) {
		        sb.append(aHtml(p + 1, -20));
		    }else{
		    	sb.append(pHtml(-20));
		    };
		    sb.append("<input type=\"hidden\" name=\"page.currentPage\" id=\"currentPage\" value=\"" + p+"\"/> ");
		    sb.append("<input type=\"hidden\" name=\"page.totalPages\" id=\"totalPages\" value=\"" + pn+"\"/> ");
		    sb.append("<input type=\"hidden\" name=\"page.totalCount\" id=\"totalCount\" value=\"" + pagination.getTotalCount()+"\"/> ");
			sb.append(allHtml(pn));
			sb.append(goHtml(pn));
			sb.append("</div>");
			pageContext.getOut().write(sb.toString());
		}catch(IOException e){
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}


	private String aHtml(int p) {
		return " <a href=\"javascript:goPage('"+ formID +"', "+ p +");\" class='aNum'>" + p + "</a> ";
	}
	
	private String aHtml(int p, int pp) {
		if(pp == -10){
			return " <a href=\"javascript:goPage('"+ formID +"', "+ p +");\" class='preNum'>上一页</a> ";
		}else if(pp == -20){
			return " <a href=\"javascript:goPage('"+ formID +"', "+ p +");\" class='nextNum'>下一页</a> ";
		}else{
			return " <a href=\"javascript:goPage('"+ formID +"', "+ p +");\" class='aNum'>" + p + "</a> ";
		}
	}
	
	
	private String pHtml(int p) {
		if(p == -10){
			return " <span class='preNum'>上一页</span> ";
		}else if(p == -20){
			return " <span class='nextNum'>下一页</span> ";
		}else{
			return " <span class='currentNum'>" + p + "</span> ";
		}
	}
	
	private String allHtml(int pn) {
		return " <span class='allNum'>共" + pn + "页," + pagination.getTotalCount() + "条记录</span> ";
	}

	private String goHtml(int pn) {
		if(pn<=1){
			return "";
		}
		return " <span class='goPageNum'><input type=\"button\" class=\"gobtn\" value=\"GO\" onClick=\"gotoPage('"+ formID +"', "+ pn +")\"></input><input type=\"text\" class=\"goNum\" id=\"gotoPageNum\"  size=\"5\" maxlength=\"5\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" />跳转到</span> ";
	}

	/**
	 * setters and getters
	 * @return
	 */
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormID() {
		return formID;
	}
	public void setFormID(String formID) {
		this.formID = formID;
	}


	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
}
