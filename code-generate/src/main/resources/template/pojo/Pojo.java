package $

{package_pojo};
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.*;
import java.io.Serializable;
<#list typeSet as set>
import ${set};
</#list>

/****
 * @Author:唐嘉
 * @Description:<#list models as model>${model.desc!""},</#list>
 * @Date ${now}
 *****/
@Table(name = "${TableName}")
public class $ {
    Table
} implements Serializable{

<#list models as model>
<#if model.id==true>
@Id
<#if model.identity=='YES'>
@GeneratedValue(strategy = GenerationType.IDENTITY)
</#if>
</#if>
<#if model.simpleType=='Long'>
@JsonSerialize(using = ToStringSerializer.class)
</#if>
@Column(name = "${model.column}")
private ${model.simpleType}${model.name};//${model.desc!""}

</#list>


<#list models as model>
//get方法
public ${model.simpleType}get${model.upperName}(){
        return ${model.name};
        }

//set方法
public void set${model.upperName}(${model.simpleType}${model.name}){
        this.${model.name}=${model.name};
        }
</#list>


        }
