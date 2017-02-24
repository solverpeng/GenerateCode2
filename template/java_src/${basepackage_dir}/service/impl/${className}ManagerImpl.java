<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${basepackage}.service.I${className}Manager;

<#include "/java_imports.include">
@Service("${classNameLower}Manager")
@Transactional
public class ${className}ManagerImpl extends BaseManager<${className},${table.idColumn.javaType}> implements I${className}Manager{
	@Autowired
	private I${className}Dao ${classNameLower}Dao;
	
	public EntityDao getEntityDao() {
		return (EntityDao)this.${classNameLower}Dao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(${className}Query query) {
		return ${classNameLower}Dao.findPage(query);
	}

	@Transactional(readOnly=true)
	public List<${className}> findList(${className}Query query){
		return ${classNameLower}Dao.findList(query);
	}

	public Integer findListCount(${className}Query query){
		return ${classNameLower}Dao.findListCount(query);
	}

	@Transactional(readOnly=true)
	public ${className} getById(${table.idColumn.javaType} id){
		return super.getById( id);
	}

	@Transactional(readOnly=true)
	public ${className} getObject(${className}Query query){
		return ${classNameLower}Dao.getObject(query);
	}
	
	@Transactional
	public void removeById(${table.idColumn.javaType} id){
		super.removeById(id);
	}
	
	/** 插入数据 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void save(${className} entity){
		super.save(entity);
	}
	
	/** 更新数据 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void update(${className} entity){
		super.update(entity);
	}

	@Transactional(readOnly=true)
	public boolean isUnique(${className} entity, String uniquePropertyNames){
		return super.isUnique( entity, uniquePropertyNames);
	}

	@Transactional(readOnly=true)
	public List<${className}> findAll(){
		return super.findAll();
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void saveOrUpdate(${className} entity) {
		super.saveOrUpdate(entity);
	}

<#list table.columns as column>
	<#if column.unique && !column.pk>
	@Transactional(readOnly=true)
	public ${className} getBy${column.columnName}(${column.javaType} v) {
		return ${classNameLower}Dao.getBy${column.columnName}(v);
	}	
	
	</#if>
</#list>
}
