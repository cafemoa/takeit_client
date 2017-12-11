package mkworld29.mobile.com.cafemoa.xmlEntity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by eunseo on 2017-12-12.
 */

public class _Response{
    @Element(name="data")
    public Data data;

    @Attribute(name="error")
    public int error;
    @Attribute(name="errormsg", required = false)
    public String errormsg;
    @Attribute(name="method")
    public String method;

}