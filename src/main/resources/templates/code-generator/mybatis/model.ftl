package ${packageName}.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.wdit.common.persistence.DataEntity;


public class ${classInfo.className} extends DataEntity<${classInfo.className}> {

    <#list classInfo.fieldList as fieldItem >
    <#if fieldItem.columnName ?ends_with("status")>
    /**
    * ${fieldItem.fieldComment}
    */
    public static final ${fieldItem.fieldClass} ${fieldItem.columnName ? upper_case}_XX = null;
    public static final List<${fieldItem.fieldClass}> ${fieldItem.columnName ? upper_case}_SCOPE = Arrays.asList(STATUS_XX);

    </#if>
    </#list>

    <#list classInfo.fieldList as fieldItem >
    <#if fieldItem.columnName != "id" && fieldItem.columnName != "create_date"
    && fieldItem.columnName != "update_date" &&  fieldItem.columnName != "del_flag"
    &&  fieldItem.columnName != "remarks">
    private ${fieldItem.fieldClass} ${fieldItem.fieldName}; //${fieldItem.fieldComment}
    </#if>
    </#list>

    public static class Query implements Serializable {

        public static final String PROP_EXAMPLE = "example";
        private String example;

    }

    @Override
    protected Object createQueryObject() {
        return new Query();
    }
}