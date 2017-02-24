<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.org.rapid_framework.util.MapAndObject;

<#include "/java_imports.include">
public interface I${className}Dao{

	public Object getById(${table.idColumn.javaType} id);
	
	public ${className} getObject(${className}Query query);
	
	public void deleteById(${table.idColumn.javaType} id);
	
	/** 插入数据 */
	public void save(${className} entity);
	
	/** 更新数据 */
	public void update(${className} entity);

	/** 根据id检查是否插入或是更新数据 */
	public void saveOrUpdate(${className} entity);

	public boolean isUnique(${className} entity, String uniquePropertyNames);
	
	public List<${className}> findAll();
	
	public Page findPage(${className}Query query);
	
	public List<${className}> findList(${className}Query query);
	
	public Integer findListCount(${className}Query query);
	
	<#list table.columns as column>
	<#if column.unique && !column.pk>
	public ${className} getBy${column.columnName}(${column.javaType} v);
	</#if>
	</#list>

}