package ${package_pojo};

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

<#list typeSet as set>
import ${set};
</#list>

/****
 * @Author:唐嘉
 * @Description:<#list models as model>${model.desc!""},</#list>
 * @Date ${now}
 *****/
@Getter
@Setter
@Table(name = "${TableName}")
public class ${Table} implements Serializable{

    <#list models as model>
    /**
     * ${model.desc!""}
     */
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
    private ${model.simpleType} ${model.name};

    </#list>

}
