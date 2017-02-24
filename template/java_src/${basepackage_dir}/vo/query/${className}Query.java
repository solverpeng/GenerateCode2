<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.vo.query;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

<#include "/java_imports.include">
public class ${className}Query extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 1L;
	<@generateFields/>
	<@generateProperties/>

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	public static Map<String,String> property2column=new HashMap<String,String>();

    static{
    	<#list table.columns as column>
    	<#if column.isDateTimeColumn >
    	property2column.put("${column.columnNameLower}String", "${column.sqlName}");
    	<#else>
    	property2column.put("${column.columnNameLower}", "${column.sqlName}");
    	</#if>
    	
		</#list>
    }

    public static String getColumn(String property){
		return property2column.get(property);
	}
	
}

<#macro generateFields>

	<#list table.columns as column>
	/** ${column.columnAlias} */
	<#if column.isDateTimeColumn >
	private ${column.javaType} ${column.columnNameLower}Begin;
	private ${column.javaType} ${column.columnNameLower}End;
	<#else>
	private ${column.javaType} ${column.columnNameLower};
	</#if>
	</#list>

</#macro>

<#macro generateProperties>
	<#list table.columns as column>
	<#if column.isDateTimeColumn >
	public ${column.javaType} get${column.columnName}Begin() {
		return this.${column.columnNameLower}Begin;
	}
	
	public void set${column.columnName}Begin(${column.javaType} value) {
		this.${column.columnNameLower}Begin = value;
	}	
	
	public ${column.javaType} get${column.columnName}End() {
		return this.${column.columnNameLower}End;
	}
	
	public void set${column.columnName}End(${column.javaType} value) {
		this.${column.columnNameLower}End = value;
	}
	
	<#else>
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	</#if>	
	</#list>
</#macro>
