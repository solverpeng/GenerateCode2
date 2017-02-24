<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao.impl;

import ${basepackage}.dao.I${className}Dao;

import org.springframework.stereotype.Repository;

<#include "/java_imports.include">
@Repository("${classNameLower}Dao")
public class ${className}DaoImpl extends BaseIbatisDao<${className},${table.idColumn.javaType}> implements I${className}Dao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "${className}";
	}
	
	public ${className} getById(${table.idColumn.javaType} id){
		return super.getById( id);
	}
	
	public ${className} getObject(${className}Query query){
		return (${className})getSqlMapClientTemplate().queryForObject("${className}.findPage",query);
	}
	
	public void deleteById(${table.idColumn.javaType} id){
		super.deleteById(id);
	}
	
	/** 插入数据 */
	public void save(${className} entity){
		if(StringUtils.isEmpty(entity.get${table.idColumn.columnName}())) {
			entity.set${table.idColumn.columnName}(IdGenerator.genUUIDStr());
		}
		super.save(entity);
	}
	
	/** 更新数据 */
	public void update(${className} entity){
		super.update(entity);
	}

	public boolean isUnique(${className} entity, String uniquePropertyNames){
		return super.isUnique( entity, uniquePropertyNames);
	}
	
	public List<${className}> findAll(){
		return super.findAll();
	}
	
	public void saveOrUpdate(${className} entity) {
		if(StringUtils.isEmpty(entity.get${table.idColumn.columnName}())) {
			entity.set${table.idColumn.columnName}(IdGenerator.genUUIDStr());
			super.save(entity);
		}
		else 
			super.update(entity);
	}
	
	public Page findPage(${className}Query query) {
		return pageQuery("${className}.findPage",query);
	}
	
	public List<${className}> findList(${className}Query query){
		return listQuery("${className}.findPage",query);
	}
	
	public Integer findListCount(${className}Query query){
		return countQuery("${className}.findPage",query);
	}
	
	<#list table.columns as column>
	<#if column.unique && !column.pk>
	public ${className} getBy${column.columnName}(${column.javaType} v) {
		return (${className})getSqlMapClientTemplate().queryForObject("${className}.getBy${column.columnName}",v);
	}
	</#if>
	</#list>
}
