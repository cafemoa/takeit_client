package mkworld29.mobile.com.cafemoa.xmlEntity;

import org.simpleframework.xml.Attribute;

/**
 * Created by eunseo on 2017-12-12.
 */

public class ResHead{
    @Attribute(name="error")
    public int error;
    @Attribute(name="errormsg", required = false)
    public String errormsg;
}
