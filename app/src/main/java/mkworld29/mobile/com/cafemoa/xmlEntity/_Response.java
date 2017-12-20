package mkworld29.mobile.com.cafemoa.xmlEntity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by eunseo on 2017-12-12.
 */


@Root(name="response", strict=false)
public class _Response{
    @Element(name="data")
    public Data data;

    @Attribute(name="error")
    public int error;
    @Attribute(name="errormsg")
    public String errormsg;
    @Attribute(name="method")
    public String method;

}