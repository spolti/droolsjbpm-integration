package org.kie.services.client.serialization.jaxb.impl.query;

import java.util.Date;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.namespace.QName;

import org.kie.api.runtime.manager.audit.VariableInstanceLog;

@XmlRootElement(name="variable-info")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbVariableInfo {

    @XmlElement(required=true)
    @XmlSchemaType(name="string")
    private String name;

    @XmlAnyElement
    private Object value;
    
    @XmlElement
    @XmlSchemaType(name="dateTime")
    private Date lastModificationDate;

    public JaxbVariableInfo() { 
        // default for JAXB
    }
    
    public JaxbVariableInfo(String name, Object value) { 
        this.name = name;
        if( value instanceof String ) { 
            this.value = getStringJaxbElement((String) value);
        } else { 
            this.value = value;
        }
    }
    
    public JaxbVariableInfo(String name, Object value, Date date) { 
        this(name, value);
        this.lastModificationDate = date;
    }
    
    public JaxbVariableInfo(VariableInstanceLog varLog) {
        this(varLog.getVariableId(), getStringJaxbElement(varLog.getValue()), varLog.getDate());
    }
    
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Object getValue() {
        if( value == null ) { 
            return value;
        }
        if( value instanceof JAXBElement ) { 
            return ((JAXBElement) value).getValue();
        }
        return value;
    }

    public void setValue( Object value ) {
        if( value instanceof String ) { 
           this.value = getStringJaxbElement((String) value); 
        }
        this.value = value;
    }

    private static JAXBElement<String> getStringJaxbElement(String value) { 
        return new JAXBElement<String>(new QName("string"), String.class, value);
    }
    
    public Date getModificationDate() {
        return lastModificationDate;
    }

    public void setModificationDate( Date modificationDate ) {
        this.lastModificationDate = modificationDate;
    }
}
