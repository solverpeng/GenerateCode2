<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<#include "/java_imports.include">
public interface I${className}Manager{
	
	public Page findPage(${className}Query query);
	
	public List<${className}> findList(${className}Query query);
	
	public Integer findListCount(${className}Query query);

	public ${className} getById(${table.idColumn.javaType} id);
	
	public ${className} getObject(${className}Query query);
	
	public List<${className}> findAll();
	
	/** 根据id检查是否插入或是更新数据 */
	public void saveOrUpdate(${className} entity);
	
	/** 插入数据 */
	public void save(${className} entity);
	
	public void removeById(${table.idColumn.javaType} id);
	
	public void update(${className} entity);
	
	public boolean isUnique(${className} entity, String uniquePropertyNames);
	<#list table.columns as column>
		<#if column.unique && !column.pk>
		@Transactional(readOnly=true)
		public ${className} getBy${column.columnName}(${column.javaType} v);
		
		</#if>
	</#list>
}
