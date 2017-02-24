<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package ${basepackage}.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.web.scope.Flash;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;
import com.solverpeng.common.ajax.AjaxResult;

<#include "/java_imports.include">
public class ${className}Action extends BaseStruts2Action implements Preparable,ModelDriven{
	private final static Log logger = LogFactory.getLog(${className}Action.class);
	
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "create_time desc"; 
	
	//forward paths
	protected static final String QUERY_JSP = "${jspFileBasePath}/query.jsp";
	protected static final String LIST_JSP= "${jspFileBasePath}/list.jsp";
	protected static final String CREATE_JSP = "${jspFileBasePath}/create.jsp";
	protected static final String EDIT_JSP = "${jspFileBasePath}/edit.jsp";
	protected static final String SHOW_JSP = "${jspFileBasePath}/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!${actionBasePath}/list.do";
	
	@Autowired
	private I${className}Manager ${classNameLower}Manager;
	
	private ${className} ${classNameLower};
	<#list table.compositeIdColumns as column>
	${column.javaType} id = null;
	</#list>
	private String[] items;
	private String param;
	
	public void setParam(String param){
		this.param = param;
	}

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			${classNameLower} = new ${className}();
		} else {
			${classNameLower} = (${className})${classNameLower}Manager.getById(id);
		}
	}
	
	
	public Object getModel() {
		return ${classNameLower};
	}
	
	<#list table.compositeIdColumns as column>
	public void set${column.columnName}(${column.javaType} val) {
		this.id = val;
	}
	</#list>	

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		return LIST_JSP;
	}
	
	public String listAjax(){
		Map map = new HashMap();
		try{
			${className}Query query = newQuery(${className}Query.class,DEFAULT_SORT_COLUMNS);
			Page page = ${classNameLower}Manager.findPage(query);
			map.put("total", page.getTotalCount());
			map.put("rows", page.getResult());
		}catch(Exception e){
			logger.error("${className}查询操作异常,请联系管理员！",e);
		}
		return writeAjaxResponse(JSON.toJSONString(map));
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		AjaxResult result = new AjaxResult();
		try {
			${classNameLower}Manager.save(${classNameLower});
			result.setCode(AjaxResult.RESULT_CODE_0000);
		} catch (Exception e) {
			result.setCode(AjaxResult.RESULT_CODE_0001);
			if(e.getMessage()!=null){
				result.setMessage(e.getMessage());
			}else{
				result.setMessage("${className}保存操作异常,请联系管理员！");
			}
			logger.error(result.getMessage(),e);
		}
		return writeAjaxResponse(result);
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		AjaxResult result = new AjaxResult();
		try {
			${classNameLower}Manager.update(${classNameLower});
			result.setCode(AjaxResult.RESULT_CODE_0000);
		} catch (Exception e) {
			result.setCode(AjaxResult.RESULT_CODE_0001);
			if(e.getMessage()!=null){
				result.setMessage(e.getMessage());
			}else{
				result.setMessage("${className}更新操作异常,请联系管理员！");
			}
			logger.error(result.getMessage(),e);
		}
		return writeAjaxResponse(result);
	}
	
	/**删除对象*/
	public String delete() {
		
		AjaxResult result = new AjaxResult();
		try {
			if(items!=null){
				for(String ids:items){
					String[] idArray = ids.split(",");
					for(String id:idArray)
					${classNameLower}Manager.removeById(id);
				}
			}
			result.setCode(AjaxResult.RESULT_CODE_0000);
		} catch (Exception e) {
			result.setCode(AjaxResult.RESULT_CODE_0001);
			if(e.getMessage()!=null){
				result.setMessage(e.getMessage());
			}else{
				result.setMessage("${className}保存操作异常,请联系管理员！");
			}
			logger.error(result.getMessage(),e);
		}
		return writeAjaxResponse(result);
	}

}
